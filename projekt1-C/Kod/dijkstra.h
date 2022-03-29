#ifndef DIJKSTRA_H
#define DIJKSTRA_H

#include "data_structures.h"
#include "graph.h"


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


#endif

