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

#endif
