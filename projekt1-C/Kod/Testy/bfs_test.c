#include <stdlib.h>

#include "bfs.h"
#include "bfs_test.h"
#include "graph.h"

FIFO_t fifo;
int* visited;

int* expected_visited;
int* expected_FIFO_put;
int* expected_FIFO_get;

void expected_bfs_make(graph_t graph) {
    expected_visited = expected_visited_vertexes(graph);

    expected_FIFO_put = expected_FIFO_put_vertexes(graph);

    expected_FIFO_get = expected_FIFO_get_vertexes(graph);
    
    if(expected_visited == NULL || expected_FIFO_put == NULL || expected_FIFO_get == NULL ) {
	fprintf(stdout," Failed to allocate memory for expected values, can not perform test\n");
	exit(EXIT_FAILURE);
	}
}

FIFO_t make_fifo(graph_t graph) {
    fifo = malloc(sizeof *fifo);
    if (fifo == NULL) {
        fprintf(stderr, "\tCan not allocate memory for fifo queue");
        exit(EXIT_FAILURE);
    }
    fifo->head = -1;
    fifo->back = -1;
    fifo->vertexes = malloc(graph->no_vertexes * sizeof *(fifo->vertexes));
    if (fifo->vertexes == NULL) {
        free(fifo);
        fprintf(stderr, "\tCan not allocate memory for vertexes in queue");
        exit(EXIT_FAILURE);
    }
    return fifo;
}

/* Adding vertex to FIFO queue */
void FIFO_put(FIFO_t fifo, int vertex) {
    if (fifo->head == -1) fifo->head = 0;
    fifo->back++;
    fifo->vertexes[fifo->back] = vertex;
}
int FIFO_get(FIFO_t fifo) {
    int removed_vertex;

    removed_vertex = fifo->vertexes[fifo->head];
    fifo->head++;

    return removed_vertex;
}

int is_FIFO_empty(FIFO_t fifo) {
    if (fifo->back >= fifo->head) /* If this condition is true, it means that there are vertexes in FIFO queue */
        return 1;
    else
        return 0;
}

int bfs_test(graph_t graph, int start_vertex_number) {
    FIFO_t fifo = NULL;
    fifo = make_fifo(graph);
    visited = malloc(graph->no_vertexes * sizeof(*visited)); /* Memory allocation for vertexes that have already been visited */
    if (visited == NULL) {
        free(fifo->vertexes);
        free(fifo);
        fprintf(stderr, "\tCan not allocate memory for visited array");
        exit(EXIT_FAILURE);
    }
    for (int i = 0; i < graph->no_vertexes; i++) {
        visited[i] = -1; /* Assignment of the value -1 means that no vertex has been visited yet */
    }

    visited[start_vertex_number] = 1; /* The value of one means that vertex has been visited */
    if (start_vertex_number != *expected_visited) {
        fprintf(stderr, "\tTesting bfs function: test not passed\n");
        return -1;
    }
    expected_visited++;
    
  FIFO_put(fifo,start_vertex_number);
	if(fifo->vertexes[fifo->back] != *expected_FIFO_put) {
		fprintf(stderr,"\tTesting FIFO_put function: test not passed\n");
	return -1;
  	}
	expected_FIFO_put++;
  while (is_FIFO_empty(fifo)) {          /* This loop will end when the queue is empty */
    int current_vertex = FIFO_get(fifo); /* Current_vertex is vertex that we have taken out the queue and we will look for his neighbours */
    if(current_vertex != *expected_FIFO_get) {
	fprintf(stderr,"\tTesting FIFO_get function: test not passed\n");
	return -1;
    }
	expected_FIFO_get++;
    for (int i = 0; i < graph->no_vertexes; i++) {
      /* If the first condition is true it means that there is an edge connecting current_vertex with another vertex, and weight of this edge is store in the adjacency matrix */
      /* Second condition prevents against adding vertexes to queue that have been already visited */
      if (graph->adj_mat[current_vertex][i] != -1 && visited[i] == -1) {
        FIFO_put(fifo, i); /* The vertex that is connected with the current_vertex is putted into the FIFO queue to check it's neighbours later */
      	if(	fifo->vertexes[fifo->back] != *expected_FIFO_put) {
	fprintf(stderr,"\tTesting FIFO_put function: test not passed\n");
		return -1;
	}
	expected_FIFO_put++;
	  visited[i] = 1;    /* Vertex that is putted into the queue becomes visited vertex */
      	if(i != *expected_visited) {
		fprintf(stderr,"\tTesting bfs function: test not passed [wrong visited array]");
		return -1;
	 }
	expected_visited++;
	}
    }
  }	if(graph->no_vertexes == 6*6) {
	fprintf(stdout,"\tTesting bfs function: test passed\n");
	fprintf(stdout,"\tTesting FIFO_put function: test passed\n");
	fprintf(stdout,"\tTesting FIFO_get function: test passed\n");
 	}
  for (int i = 0; i < graph->no_vertexes; i++) {
    if (visited[i] != 1) {
	free_bfs();
	return 0; /* inconsistent graph */
  } 
   }
   free_bfs();
  return 1; /* consistent graph */
 

}

void free_bfs() {
  free(fifo->vertexes);
  free(fifo);
  free(visited);
}






int* expected_visited_vertexes(graph_t graph) {
    if (graph->no_vertexes == 3 * 3) {
        static int expected_visited[] = {
            0, 1, 3, 2, 4, 6, 5, 7, 8};

        return expected_visited;
    }
    if (graph->no_vertexes == 7 * 4) {
        static int expected_visited[] = {
            0,  1,  4,  2,  5,  8,  3,
            6,  9,  12, 7,  10, 13, 16,
            11, 14, 17, 20, 15, 18, 21,
            24, 19, 22, 25, 23, 26, 27};
        return expected_visited;
    }
    if (graph->no_vertexes == 6 * 6) {
        static int expected_visited[] = {
            0, 1, 6, 2, 7, 12, 3, 18, 24, 25, 30};
        return expected_visited;
    }
	return NULL;
}
int* expected_FIFO_put_vertexes(graph_t graph) {
    if (graph->no_vertexes == 3 * 3) {
        static int expected_FIFO_put[] = {
            0, 1, 3, 2, 4, 6, 5, 7, 8};
        return expected_FIFO_put;
    }
    if (graph->no_vertexes == 7 * 4) {
        static int expected_FIFO_put[] = {
            0,  1,  4,  2,  5,  8,  3,
            6,  9,  12, 7,  10, 13, 16,
            11, 14, 17, 20, 15, 18, 21,
            24, 19, 22, 25, 23, 26, 27};
        return expected_FIFO_put;
    }
    if (graph->no_vertexes == 6 * 6) {
        static int expected_FIFO_put[] = {
            0, 1, 6, 2, 7, 12, 3, 18, 24, 25, 30};
        return expected_FIFO_put;
    }
	return NULL;
}
int* expected_FIFO_get_vertexes(graph_t graph) {
    if (graph->no_vertexes == 3 * 3) {
        static int expected_FIFO_get[] = {
            0, 1, 3, 2, 4, 6, 5, 7, 8};
        return expected_FIFO_get;
    }
    if (graph->no_vertexes == 7 * 4) {
        static int expected_FIFO_get[] = {
            0,  1,  4,  2,  5,  8,  3,
            6,  9,  12, 7,  10, 13, 16,
            11, 14, 17, 20, 15, 18, 21,
            24, 19, 22, 25, 23, 26, 27};
        return expected_FIFO_get;
    }
    if (graph->no_vertexes == 6 * 6) {
        static int expected_FIFO_get[] = {
            0, 1, 6, 2, 7, 12, 3, 18, 24, 25, 30};
        return expected_FIFO_get;
    }
	return NULL;
}
