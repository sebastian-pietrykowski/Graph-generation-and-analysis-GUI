#ifndef _GENERATOR_H_
#define _GENERATOR_H_

#include "graph.h"

/* Returns graph about size columns x rows, generated according to mode 1:
 * with all possible edges (between vertexes  neighboring vertically or horizontally)
 * and random edges' weights from of range (from_weight, to_weight). */
graph_t generate_complete_graph( int columns, int rows, double from_weight, double to_wieght );


/* Returns graph about size columns x rows, generated according to mode 2:
 * connected and with random edges' weights from of range (from_weight, to_weight). */
graph_t generate_connected_graph( int columns, int rows, double from_weight, double to_weight );


/* Returns graph about size columns x rows, generated according to mode 3:
 * with random occurence of edges (connected or not connected)
 * and with random edges' weights from of range (from_weight, to_weight). */
graph_t generate_random_graph( int columns, int rows, double from_weight, double to_weight );


/* If random neighbor vertex of vertex with number "vertex_from" is unvisited
 * (marked in array visited as 0), then add edge leading from vertex "vertex_from" to it.
 * Function returns 1 if one edge is succesfully created, 0 if no edge is created. */
int add_edge_to_neighbor( int vertex_from, graph_t graph, int * visited );

/* Returns 1 if all vertices are marked as visited in array "visited" (marked as 1),
 * returns 0 if any of vertices is marked as unvisited (0 in array "visited" ) */
int are_all_vertices_visited( int * visited, int number_of_vertices ) {

/* Possibly creates edge from vertex with number vertex_from
 * to one of vertices in Set remaining_neighbors */
void try_to_create_random_edge( int vertex_from, Set remaining_neighbors, Graph graph );

#endif
