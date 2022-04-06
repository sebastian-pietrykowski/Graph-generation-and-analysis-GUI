#include <ctype.h> /* isspace function */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "graph.h"

#define MAXLINE 256

graph_t make_graph(int columns, int rows) {
 graph_t graph = malloc(sizeof *graph); /* initialization of graph structure */
 if( graph == NULL ) {
  fprintf(stderr,"Can not allocate memory for graph");
  return NULL;
 }
  graph->columns = columns;

  graph->rows = rows;

  graph->no_vertexes = columns * rows;

  graph->adj_mat = malloc(graph->no_vertexes * sizeof *(graph->adj_mat)); /* memory allocation for an array of pointers */
  if( graph->adj_mat == NULL ) {
    free(graph);
    fprintf(stderr,"Can not allocate memory for adjacency matrix");
    return NULL;
  }
  for (int i = 0; i < graph->no_vertexes; i++) {
    graph->adj_mat[i] = malloc(graph->no_vertexes * sizeof **graph->adj_mat); /* memory allocation for the array the pointer points to */
    }
  for(int i = 0 ; i < graph->no_vertexes; i++) {
    if(graph->adj_mat[i] == NULL) {
     free(graph->adj_mat);
     free(graph);
     fprintf(stderr,"Can not allocate memory record for adjacency matrix");
     return NULL;
   } 
  }
 for(int i = 0 ; i < graph->no_vertexes; i++) { 
  for (int j = 0; j < graph->no_vertexes; j++) {
      graph->adj_mat[i][j] = -1; /* Filling the matrix with -1, so that by default a vertex is not connected to any another */
    }
  }

  return graph;
}

graph_t read_graph(FILE* in, graph_t graph) {
  int columns, rows;
  if ((fscanf(in, "%d %d", &(rows), &(columns)) != 2)) {
    fprintf(stderr,"Error, can not read the dimensions of the graph");
    return NULL;
  }

  graph = make_graph(columns, rows);
  if(graph == NULL) {
    exit(EXIT_FAILURE);
  }
  char line[MAXLINE];
  char delim[3] = " :"; /* delimiter */
  double weight;
  int to_vertex;
  int from_vertex;

  /*We start the iteration from -1 because the vertex and weight data start on the second line of the file */
  for (from_vertex = -1; from_vertex < graph->no_vertexes; from_vertex++) {
    while (fgets(line, MAXLINE, in) != NULL) {
      char* token = strtok(line, delim); /* breaking line into a series of tokens */
      int temp = 1;

      while (token != NULL) {
        if (!isspace(*token)) { /* We are only interested in numbers */
          temp++;

          if (temp % 2 == 0) { /* Thanks to the condition if we can alternately enter vertexes and weights into the array of subsequent tokens */
            to_vertex = atoi(token);
            if (to_vertex < 0) {
              fprintf(stderr, "Error, invalid vertex number, line number : %d\n", from_vertex + 2);
 	      return NULL;
            }
          } else {
            weight = atof(token);
            graph->adj_mat[from_vertex][to_vertex] = weight;
          }
          token = strtok(NULL, delim);
        } else
          token = strtok(NULL, delim); /* If the token is not a number, download another */
      }
      break;
    }
  }

  return graph;
}

int does_have_all_edges(graph_t graph) {
  int temp = 0;
  for (int i = 0; i < graph->no_vertexes; i++) {
    for (int j = 0; j < graph->no_vertexes; j++) {
      if (graph->adj_mat[i][j] != -1) {
        temp++;
      }
    }
  }
  /* We multiply by 4 because there are only four vertices that are "corners" of the graph that have a maximum of 2 connections to other vertices,
   *
   *  then we add a number that is the product of "(graph->columns -2 )" ( becuase this is the number of vertices in the first columns that are not corner vertices ) ,
   *  and "2" ( because we also consult the last column ) ,
   *  and "3" ( because this vertices can be connected to up to three other vertices )
   *
   *  then we add a number that is the product of "(graph->rows -2 )" (because this is the number of vertives in the first row that are not corner vertices ),
   *  and "2" ( because we also consult the last row ),
   *  and "3" (beacuse this vertices can be connected to up to three other vertices )
   *
   *  then we add a number that is the product of "(graph->rows - 2 ) * ( graph->columns - 2 )" because this is the number of vertices that are not the most distant vertices from the center of the graph - lying on its outer sides
   *  and "4" ( because this vertices can be connected to up to four other vertices
   */
  if (temp != (4 * 2 + (graph->columns - 2) * 2 * 3 + (graph->rows - 2) * 2 * 3 + ((graph->rows - 2) * (graph->columns - 2)) * 4))
    return 0; /* Graph does not have all possible edges */
  else
    return 1;
}

int write_graph(graph_t graph, FILE* out) {
  if (out == NULL) {
    fprintf(stderr, "Error, can't write to file\n");
    return 1;
  }
  fprintf(out, "%d %d", graph->rows, graph->columns);
  fprintf(out, "\n");
  for (int i = 0; i < graph->no_vertexes; i++) {
    fprintf(out, "\t ");
    for (int j = 0; j < graph->no_vertexes; j++) {
      if (graph->adj_mat[i][j] != -1) {
        fprintf(out, " %d :%lf ", j, graph->adj_mat[i][j]);
      }
    }
    fprintf(out, "\n");
  }
  return 0;
}

int* neighbors(graph_t graph, int vertex) {
  int iter = 0;
  int* neighbors = malloc(4 * sizeof(*neighbors)); /*  One vertex can be connected to up to four other vertices */
  for (int j = 0; j < graph->no_vertexes; j++) {
    if (graph->adj_mat[vertex][j] != -1) {
      neighbors[iter] = j;
      iter++;
    }
  }

  return neighbors;
}

int *potential_neighbors( graph_t graph, int vertex, int * number_of_vertices ) {
	
	int * potential_neighbors_array = malloc( sizeof(int) * 4 ); /* array of vertices to
								  whom edges may be created */
	*number_of_vertices = 0;
	int tmp = -1;

	// neighbor to north
	if( (tmp = vertex - graph->columns) > 0 )
		potential_neighbors_array[(*number_of_vertices)++] = tmp;
	
	// neighbor to south
	if( (tmp = vertex + graph->columns) < graph->no_vertexes )
		potential_neighbors_array[(*number_of_vertices)++] = tmp;
	
	// neighbors to west and east
	// find number of row containing vertex
	int row_number = vertex / graph->columns +1;
	
	// find first and last elements of row
	int start_row_number = (row_number-1) * graph->columns;
	int end_row_number = ( (row_number) * graph->columns ) - 1;

	// check if negihbor to east can exist
	if( start_row_number < vertex )
		potential_neighbors_array[(*number_of_vertices)++] = vertex - 1;
	// check if neighbor to west can exist
	if( vertex < end_row_number )
		potential_neighbors_array[(*number_of_vertices)++] = vertex + 1;

	// trim the size of array
	if( *number_of_vertices != 4 )
		potential_neighbors_array = realloc( potential_neighbors_array,
				sizeof(int) * (*number_of_vertices) );
	
	return potential_neighbors_array;
}

void print_graph( graph_t graph ) {
	for(int vertical_counter = 0; vertical_counter < graph->rows; vertical_counter++ ) {
		// print arrows up and down
		if( vertical_counter != 0 ) {
			printf(" ");
			for(int horizontal_counter = 0; horizontal_counter < graph->columns; horizontal_counter++ ) {
				int vertex1 = (vertical_counter-1) * graph->columns + horizontal_counter;
				int vertex2 = vertical_counter * graph->columns + horizontal_counter;

				int did_find_up = 0;
				int did_find_down = 0;
				if( graph->adj_mat[vertex1][vertex2] != -1 )
					did_find_down = 1;
				if( graph->adj_mat[vertex2][vertex1] != -1 )
					did_find_up = 1;

				if( !did_find_up && !did_find_down )
				       	printf("        ");
				else if( did_find_up && did_find_down )
					printf("/\\\\/    ");
				else if( did_find_up )
					printf(" /\\     ");
				else if( did_find_down )
					printf(" \\/     ");
			}
			printf("\n\n");
		}
		//print arraws to left and right
		for(int horizontal_counter = 0; horizontal_counter < graph->columns; horizontal_counter++ ) {
			
			if( horizontal_counter != 0 ) {
				int vertex1 = vertical_counter * graph->columns + horizontal_counter - 1;
				int vertex2 = vertex1 + 1;
					
				int did_find_right = 0;
				int did_find_left = 0;
				if( graph->adj_mat[vertex1][vertex2] != -1 )
					did_find_right = 1;
				if( graph->adj_mat[vertex2][vertex1] != -1 )
					did_find_left = 1;

				if( !did_find_left && !did_find_right )
				       	printf("    ");
				else if( did_find_left && did_find_right )
					printf("<-->");
				else if( did_find_right )
					printf("  ->");
				else if( did_find_left )
					printf("  <-");
			}	
			printf("%4d", vertical_counter*graph->columns + horizontal_counter );
		}
		printf("\n\n");
	}
}

void free_graph(graph_t graph) {
  for (int i = 0; i < graph->no_vertexes; i++) {
    free(graph->adj_mat[i]);
  }
  free(graph->adj_mat);
  free(graph);
}


