#ifndef BFS_TEST_H
#define BFS_TEST_H

#include "graph.h"
#include "bfs.h"

int bfs_test(graph_t graph, int start_vertex_number);

int *expected_visited_vertexes(graph_t graph);
int *expected_FIFO_put_vertexes(graph_t graph);
int *expected_FIFO_get_vertexes(graph_t graph);

void expected_bfs_make(graph_t graph);


int bfs_testing(graph_t graph1,graph_t graph2,graph_t graph3);
#endif
