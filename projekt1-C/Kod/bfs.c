#include <stdlib.h>

#include "bfs.h"
#include "graph.h"

FIFO_t fifo;
int* visited;

FIFO_t make_fifo(graph_t graph) {
  fifo = malloc(sizeof *fifo);
  if(fifo == NULL) {
   fprintf(stderr,"Can not allocate memory for fifo queue");
   exit(EXIT_FAILURE);
  }
  fifo->head = -1;
  fifo->back = -1;
  fifo->vertexes = malloc(graph->no_vertexes * sizeof *(fifo->vertexes));
  if(fifo->vertexes == NULL) {
   free(fifo);
   fprintf(stderr,"Can not allocate memory for vertexes in queue");
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

int bfs(graph_t graph, int start_vertex_number) {
  FIFO_t fifo = NULL;
  fifo = make_fifo(graph);
  visited = malloc(graph->no_vertexes * sizeof(*visited)); /* Memory allocation for vertexes that have already been visited */
  if(visited == NULL) {
   free(fifo->vertexes);
   free(fifo);
   fprintf(stderr,"Can not allocate memory for visited array");
   exit(EXIT_FAILURE);
  }
  for (int i = 0; i < graph->no_vertexes; i++) {
    visited[i] = -1; /* Assignment of the value -1 means that no vertex has been visited yet */
  }

  visited[start_vertex_number] = 1;    /* The value of one means that vertex has been visited */
  FIFO_put(fifo, start_vertex_number); /* We put starting vertex at the beginning of the FIFO queue */

  while (is_FIFO_empty(fifo)) {          /* This loop will end when the queue is empty */
    int current_vertex = FIFO_get(fifo); /* Current_vertex is vertex that we have taken out the queue and we will look for his neighbours */
    for (int i = 0; i < graph->no_vertexes; i++) {
      /* If the first condition is true it means that there is an edge connecting current_vertex with another vertex, and weight of this edge is store in the adjacency matrix */
      /* Second condition prevents against adding vertexes to queue that have been already visited */
      if (graph->adj_mat[current_vertex][i] != -1 && visited[i] == -1) {
        FIFO_put(fifo, i); /* The vertex that is connected with the current_vertex is putted into the FIFO queue to check it's neighbours later */
        visited[i] = 1;    /* Vertex that is putted into the queue becomes visited vertex */
      }
    }
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



