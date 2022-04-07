#ifndef _TESTS_DIJKSTRA_H_
#define _TESTS_DIJKSTRA_H_

#include "../dijkstra.h"
#include "../graph.h"
#include "../data_structures.h"

/* Function tests functions in dijkstra.c file.
 * Returns 1 if tests passed, 0 if not. */
int test_Dijkstra();


int compare_arrays( int * array1, int *array2, int length );

#endif
