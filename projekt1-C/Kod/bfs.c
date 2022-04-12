#include <stdlib.h>

#include "bfs.h"
#include "graph.h"


FIFO_t fifo;
int* visited;


FIFO_t make_fifo(graph_t graph) {
  
  fifo = malloc(sizeof *fifo);
  
  if(fifo == NULL) {
   fprintf(stderr,"Cannot allocate memory for fifo queue");
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


void FIFO_put(FIFO_t fifo, int vertex) {
  
  if(fifo->head == -1) 
    fifo->head = 0;
   
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
    return 0;
  else
    return 1;
}


int bfs(graph_t graph, int start_vertex_number) {

   if(start_vertex_number < 0 || start_vertex_number > graph->no_vertexes-1) {
             fprintf(stderr, "Inproper vertex in function bfs\n" );
             exit(EXIT_FAILURE);
     }

  FIFO_t fifo = NULL;

  fifo = make_fifo(graph);

  visited = malloc(graph->no_vertexes * sizeof(*visited));
  
  if(visited == NULL) {
   free(fifo->vertexes);
   free(fifo);
   fprintf(stderr,"Cannot allocate memory for visited array");
   exit(EXIT_FAILURE);
  }

  for (int i = 0; i < graph->no_vertexes; i++) {
    visited[i] = -1; 
  }

  visited[start_vertex_number] = 1;    /* The value of one means that vertex has been visited */
  FIFO_put(fifo, start_vertex_number);

  while (!is_FIFO_empty(fifo)) {      

    int current_vertex = FIFO_get(fifo); /* Current_vertex is vertex that we have taken out the queue and we will look for his neighbours */
   
    for (int i = 0; i < graph->no_vertexes; i++) { 
      /* Second condition prevents against adding vertexes to queue that have been already visited */
      
	if (graph->adj_mat[current_vertex][i] != -1 && visited[i] == -1) {
          FIFO_put(fifo, i); /* The vertex that is connected with the current_vertex is putted into the FIFO queue to check it's neighbours later */
          visited[i] = 1;    
       }
    }
  }
  for (int i = 0; i < graph->no_vertexes; i++) {
    if (visited[i] != 1) {
	    free_bfs();
	    return 0; 
    }
  }
  free_bfs();
  return 1; /* connected graph */
}


void free_bfs() {
  free(fifo->vertexes);
  free(fifo);
  free(visited);
}



