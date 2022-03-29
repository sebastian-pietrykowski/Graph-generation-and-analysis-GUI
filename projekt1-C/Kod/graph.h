#ifndef GRAPH_H
#define GRAPH_H
#include <stdio.h>

typedef struct {
  int columns;
  int rows;
  int no_vertexes;
  double **adj_mat; /* adjacency matrix stores information about weights between individual vertives */
} * graph_t;

graph_t read_graph(FILE *in); /* Function responsible for reading the graph from the input file and creating adjacency matrix */

int does_have_all_edges(graph_t graph); /* Function responsible for checking if all the possible edges in the graph exist */

int write_graph(graph_t graph, FILE *out); /* Writes the graph structure to the out file */

graph_t make_graph(int columns, int rows); /* Function responsible for initialization graph structure and memory allocation for adj_mat */

int *neighbors(graph_t graph, int vertex); /* Function returns an array of vertices for which there is an edge connecting them to the selected vertex */

int *potential_neighbors(graph_t graph, int vertex); /* Function returns an array of vertices for which there may
							be created edge connecting them to the selected vertex */

void free_graph(graph_t graph); /* Function responsible for freeing the allocated graph structure */

#endif

