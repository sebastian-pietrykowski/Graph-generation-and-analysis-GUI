#ifndef DIJKSTRA_H
#define DIJKSTRA_H

#include "graph.h"

typedef struct {

	int * vertexes;   // array of vertexes added to queue
	double * distances;   /* contains distances to given vertexes, every element of array
				 corresponds with element with the same index from array vertexes[] */
	int no_elements;   // number of vertexes stored in array vertexes[]

} * PriorityQueue;

// contains array storing vertexes which changes its size when necessary
typedef struct {

	int * vertexes;   // array of vertexes
	int no_elements;   // number of vertexes stored in array vertexes[]

} * Set;

int PP_get( PriorityQueue pp );
void PP_put( PriorityQueue pp, int vertex, double distance );

void Set_add( Set set, int vertex );

int initiate_values_dijkstra( graph_t graph, int start_vertex_number, int ** predecessors, int ** distances );
void relax( graph_t graph, int start_vertex_number, int vertex1, int vertex2, int ** predecessors, int ** distances );
int * dijkstra( graph_t graph, int start_vertex_number );
void print_path( int * predecessors, int start_vertex_number, int end_vertex_number );

void free_PP( PriorityQueue pp );
void free_Set( Set set );

#endif

