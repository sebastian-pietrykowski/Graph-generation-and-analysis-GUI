#ifndef DIJKSTRA_H
#define DIJKSTRA_H

#include "graph.h"

typedef struct {

	int * vertexes;   // array of vertexes added to queue
	double * distances;   /* contains distances to given vertexes, every element of array
				 corresponds with element with the same index from array vertexes[] */
	int no_elements;   // number of vertexes stored in array vertexes[]

} * PriorityQueue;

// Contains array storing vertexes which increases its size when necessary.
typedef struct {

	int * vertexes;   // array of vertexes
	int no_elements;   // number of vertexes stored in array vertexes[]

} * Set;

// Removes from PriorityQueue pp vertex with the shortest distance, returns it.
int PP_get( PriorityQueue pp );

/* Adds to PriorityQueue pp vertex with given number (vertex) and distance.
 * If PriorityQueue pp is NULL, then initialize it. */
void PP_put( PriorityQueue pp, int vertex, double distance );


/* Adds to Set set vertex with given number (vertex).
 * If Set set is NULL, then initialize it. */
void Set_add( Set set, int vertex );

// Returns 1 if element is present in set, 0 if not.
int Set_is_element_in( Set set, int vertex );


//Initializes arrays predecessors[] and distances[], following the Dijkstra's algorithm.
int initiate_values_dijkstra( graph_t graph, int start_vertex_number, int ** predecessors, double ** distances );

/* If specific conditions are true, then add vertex1 to arrays
 * predecessors[] and distances[], following the Dijkstra's algorithm. 
 *
 * vertex1 - next vertex being checked
 * vertex2 - potential predecessor of vertex1 */
void relax( graph_t graph, PriorityQueue pq, int start_vertex_number, int vertex1, int vertex2, int ** predecessors, double ** distances );

/* Function responsible for performing Dijkstra's algorithm.
 * Returns array where element with given index representing number of praticular vertex
 * stores number of vertex beeing its predecessor. */
int * dijkstra( graph_t graph, int start_vertex_number );

/* Given array of predecessors[] received from dijkstra(...) function,
 * returns reversed path from vertex of index start_vertex_number to vertex of index end_vertex_number.
 * If path is not determined, it returns null. */
int * determine_path( int * no_path_elements, int * predecessors, int start_vertex_number, int end_vertex_number );

/* Given reversed path received from determine path(...) functiom, prints the path..
 * 
 * If does_print_wages is set to 1, then print weights of edges the path passes,
 * if it is set to 0, then doesn't print these weights. */
void print_path( int * reversed_path, int no_elements, int does_print_weights );


// Remove PriorityQueue pp from memory.
void free_PP( PriorityQueue pp );

// Remove Set set from memory.
void free_Set( Set set );

#endif

