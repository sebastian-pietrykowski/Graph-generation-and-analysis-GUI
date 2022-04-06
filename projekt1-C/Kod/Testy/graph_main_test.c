#include <getopt.h>
#include <stdio.h>
#include <stdlib.h>

#include "graph.h"
#include "graph_test.h"

graph_t graph1;
graph_t graph2;
graph_t graph3;

int main(int argc, char** argv) {
    FILE* in1 = fopen(argv[1], "r");

    FILE* in2 = fopen(argv[2], "r");

    FILE* in3 = fopen(argv[3], "r");

    if (make_graph_testing(in1, in2, in3) == 1) {
        fprintf(stderr, "Make graph test not approved\n");
    }

    if (read_graph_testing(in1, graph1, in2, graph2, in3, graph3) == 1) {
        fprintf(stderr, "zle read graph");
    }

    if ((does_have_all_edges_testing(graph1, graph2)) == 0) {
        fprintf(stderr, "not approves does have");
    }

    return EXIT_SUCCESS;
}

int make_graph_testing(FILE* in1, FILE* in2, FILE* in3) {
    graph1 = make_graph(3, 3);
    if (graph1 == NULL) {
        return 1;
    }

    graph2 = make_graph(7, 4);
    if (graph2 == NULL) {
        return 1;
    }

    graph3 = make_graph(6, 6);
    if (graph3 == NULL) {
        return 1;
    }
}

int read_graph_testing(FILE* in1, graph_t graph1, FILE* in2, graph_t graph2, FILE* in3, graph_t graph3) {
    if ((test_read_graph(in1, graph1)) == NULL) {
        return 1;
    }
    if ((test_read_graph(in2, graph2)) == NULL) {
        return 1;
    }
    if ((test_read_graph(in3, graph3)) == NULL) {
        return 1;
    }
}

int does_have_all_edges_testing(graph_t graph1, graph_t graph2) {
    if ((does_have_all_edges_test(graph1)) != 24) {
        return 0;
    }
    if ((does_have_all_edges_test(graph2)) != 90) {
        return 0;
    }
    return 1;
}


