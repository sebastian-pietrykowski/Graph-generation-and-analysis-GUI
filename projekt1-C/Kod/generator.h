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

/* Possibly creates edge from vertex with number vertex_from
 * to one of vertices in Set remaining_neighbors */
void try_to_create_random_edge( int vertex_from, Set remaining_neighbors, Graph graph );

#endif
