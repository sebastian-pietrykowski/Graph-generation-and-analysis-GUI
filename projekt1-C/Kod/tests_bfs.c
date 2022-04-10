#include <stdlib.h>

#include "bfs.h"
#include "graph.h"
#include "tests_bfs.h"

int test_BFS(graph_t graph1, graph_t graph2, graph_t graph3) {
    expected_bfs_make(graph1);
    if (bfs_test(graph1, 0) == -1) {
        return 0;
    }

    expected_bfs_make(graph2);
    if (bfs_test(graph2, 0) == -1) {
        return 0;
    }

    expected_bfs_make(graph3);
    if (bfs_test(graph3, 0) == -1) {
        return 0;
    }

    return 1;
}

int* expected_visited;
int* expected_FIFO_put;
int* expected_FIFO_get;

int bfs_test(graph_t graph, int start_vertex_number) {
    FIFO_t fifo = NULL;
    fifo = make_fifo(graph);
    int* visited = malloc(graph->no_vertexes * sizeof(*visited));
    if (visited == NULL) {
        free_bfs_test(fifo, visited);
        fprintf(stderr, "\tCannot allocate memory for visited array");
        exit(EXIT_FAILURE);
    }
    for (int i = 0; i < graph->no_vertexes; i++) {
        visited[i] = -1; /* Assignment of the value -1 means that no vertex has been visited yet */
    }

    visited[start_vertex_number] = 1; /* The value of one means that vertex has been visited */
    if (start_vertex_number != *expected_visited) {
        fprintf(stderr, "\tTesting bfs function: test not passed\n");
        free_bfs_test(fifo, visited);
        return -1;
    }
    expected_visited++;

    FIFO_put(fifo, start_vertex_number);
    if (fifo->vertexes[fifo->back] != *expected_FIFO_put) {
        fprintf(stderr, "\tTesting FIFO_put function: test not passed\n");
        free_bfs_test(fifo, visited);
        return -1;
    }
    expected_FIFO_put++;
    while (is_FIFO_empty(fifo)) {            /* This loop will end when the queue is empty */
        int current_vertex = FIFO_get(fifo); /* Current_vertex is vertex that we have taken out the queue and we will look for his neighbours */
        if (current_vertex != *expected_FIFO_get) {
            fprintf(stderr, "\tTesting FIFO_get function: test not passed\n");
            free_bfs_test(fifo, visited);
            return -1;
        }
        expected_FIFO_get++;
        for (int i = 0; i < graph->no_vertexes; i++) {
            /* Second condition prevents against adding vertexes to queue that have been already visited */
            if (graph->adj_mat[current_vertex][i] != -1 && visited[i] == -1) {
                FIFO_put(fifo, i); /* The vertex that is connected with the current_vertex is putted into the FIFO queue to check it's neighbours later */
                if (fifo->vertexes[fifo->back] != *expected_FIFO_put) {
                    fprintf(stderr, "\tTesting FIFO_put function: test not passed\n");
                    free_bfs_test(fifo, visited);
                    return -1;
                }
                expected_FIFO_put++;
                visited[i] = 1;
                if (i != *expected_visited) {
                    fprintf(stderr, "\tTesting bfs function: test not passed [wrong visited array]");
                    free_bfs_test(fifo, visited);
                    return -1;
                }
                expected_visited++;
            }
        }
    }
    if (graph->no_vertexes == 6 * 6) {
        fprintf(stdout, "Testing bfs.c\n");
        fprintf(stdout, "\tTest 1 - bfs: test passed\n");
        fprintf(stdout, "\tTest 2 - FIFO_put: test passed\n");
        fprintf(stdout, "\tTest 3 - FIFO_get: test passed\n");
        fprintf(stdout, "\n\n All tests passed\n");
    }

    for (int i = 0; i < graph->no_vertexes; i++) {
        if (visited[i] != 1) {
            free_bfs_test(fifo, visited);
            return 0; /* inconsistent graph */
        }
    }
    free_bfs_test(fifo, visited);
    return 1; /* consistent graph */
}

void free_bfs_test(FIFO_t fifo, int* visited) {
    free(fifo->vertexes);
    free(fifo);
    free(visited);
}

void expected_bfs_make(graph_t graph) {
    expected_visited = expected_visited_vertexes(graph);

    expected_FIFO_put = expected_FIFO_put_vertexes(graph);

    expected_FIFO_get = expected_FIFO_get_vertexes(graph);

    if (expected_visited == NULL || expected_FIFO_put == NULL || expected_FIFO_get == NULL) {
        fprintf(stdout, " Failed to allocate memory for expected values, can not perform test\n");
        exit(EXIT_FAILURE);
    }
}


int* expected_visited_vertexes(graph_t graph) {
    if (graph->no_vertexes == 3 * 3) {
        static int expected_visited[] = {
            0, 1, 3, 2, 4, 6, 5, 7, 8};

        return expected_visited;
    }
    if (graph->no_vertexes == 7 * 4) {
        static  int expected_visited[] = {
            0,  1,  4,  2,  5,  8,  3,
            6,  9,  12, 7,  10, 13, 16,
            11, 14, 17, 20, 15, 18, 21,
            24, 19, 22, 25, 23, 26, 27};
        return expected_visited;
    }
    if (graph->no_vertexes == 6 * 6) {
       static  int expected_visited[] = {
            0, 1, 6, 2, 7, 12, 3, 18, 24, 25, 30};
        return expected_visited;
    }
	return NULL;
}
int* expected_FIFO_put_vertexes(graph_t graph) {
    if (graph->no_vertexes == 3 * 3) {
       static  int expected_FIFO_put[] = {
            0, 1, 3, 2, 4, 6, 5, 7, 8};
        return expected_FIFO_put;
    }
    if (graph->no_vertexes == 7 * 4) {
        static int expected_FIFO_put[] = {
            0,  1,  4,  2,  5,  8,  3,
            6,  9,  12, 7,  10, 13, 16,
            11, 14, 17, 20, 15, 18, 21,
            24, 19, 22, 25, 23, 26, 27};
        return expected_FIFO_put;
    }
    if (graph->no_vertexes == 6 * 6) {
       static  int expected_FIFO_put[] = {
            0, 1, 6, 2, 7, 12, 3, 18, 24, 25, 30};
        return expected_FIFO_put;
    }
	return NULL;
}
int* expected_FIFO_get_vertexes(graph_t graph) {
    if (graph->no_vertexes == 3 * 3) {
        static int expected_FIFO_get[] = {
            0, 1, 3, 2, 4, 6, 5, 7, 8};
        return expected_FIFO_get;
    }
    if (graph->no_vertexes == 7 * 4) {
       static  int expected_FIFO_get[] = {
            0,  1,  4,  2,  5,  8,  3,
            6,  9,  12, 7,  10, 13, 16,
            11, 14, 17, 20, 15, 18, 21,
            24, 19, 22, 25, 23, 26, 27};
        return expected_FIFO_get;
    }
    if (graph->no_vertexes == 6 * 6) {
       static  int expected_FIFO_get[] = {
            0, 1, 6, 2, 7, 12, 3, 18, 24, 25, 30};
        return expected_FIFO_get;
    }
	return NULL;
}

