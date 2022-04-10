#ifndef BFS_TEST_H
#define BFS_TEST_H

#include "bfs.h"
#include "graph.h"

int test_BFS(graph_t graph1, graph_t graph2, graph_t graph3);

void free_bfs_test(FIFO_t fifo, int *visited);

int bfs_test(graph_t graph, int start_vertex_number);

int *expected_visited_vertexes(graph_t graph);

int *expected_FIFO_put_vertexes(graph_t graph);

int *expected_FIFO_get_vertexes(graph_t graph);

void expected_bfs_make(graph_t graph);

#endif
