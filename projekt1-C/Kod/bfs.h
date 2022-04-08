#ifndef BFS_H
#define BFS_H

#include "graph.h"

typedef struct {
  int * vertexes; /* Array of vertexes in queue */
  int head; /* The variable head is the index of the vertex in the adjacency matrix for which neighbors will be searched for */
  int back; /* The variable back is the index of the last vertex in the FIFO queque */
}* FIFO_t;

int FIFO_get(FIFO_t fifo); /* Function responsible for removing the vertex from FIFO queue in order to find his neighbors */

void FIFO_put(FIFO_t fifo, int vertex); /* Function responsible for putting the vertex in FIFO queue */

int bfs(graph_t graph, int start_vertex_number); /* Main function of the bfs algorithm */

void free_bfs( void ); /* Memory clean up */

FIFO_t make_fifo (graph_t graph); /* Initialization of the FIFO_t structure */

int is_FIFO_empty(FIFO_t fifo); /* Function checks if there are vertexes in the queue that are wating to be checked for having neighbors */

#endif
