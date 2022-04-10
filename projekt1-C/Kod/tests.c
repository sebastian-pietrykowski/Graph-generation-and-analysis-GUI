#include <stdlib.h>
#include <time.h>

#include "graph.h"
#include "tests_bfs.h"
#include "tests_data_structures.h"
#include "tests_dijkstra.h"
#include "tests_generator.h"
#include "tests_graph.h"

graph_t graph1;
graph_t graph2;
graph_t graph3;

int main() {
    srand(time(NULL));

    if (!test_PQ())
	 exit(1);
    if (!test_Set()) 
	 exit(1);

    if (!test_generate_complete_graph()) 
	 exit(1);
    if (!test_generate_connected_graph()) 
	 exit(1);
    if (!test_generate_random_graph()) 
	 exit(1);

    if (!test_Dijkstra()) 
	 exit(1);
    if (!test_Graph()) 
	 exit(1);
    if (!test_BFS(graph1, graph2, graph3)) 
	 exit(1);

    free_graph(graph1);
    free_graph(graph2);
    free_graph(graph3);
    return 0;
}

int test_Graph(void) {
    FILE* in1 = fopen("Graph_test_files/test1mygraph.txt", "r");
    FILE* in2 = fopen("Graph_test_files/test2mygraph.txt", "r");
    FILE* in3 = fopen("Graph_test_files/test3mygraph.txt", "r");
    fprintf(stdout, "Testing graph.c\n");
    if (in1 == NULL || in2 == NULL || in3 == NULL) {
        fprintf(stderr, "File loaded unsuccessfully in function test_Graph\n");
        exit(EXIT_FAILURE);
    }
    fprintf(stdout, "\tTest 1 - make_graph: ");
    if (make_graph_testing(in1, in2, in3) == -1) {
        fprintf(stderr, "not passed\n");
        fclose(in1);
        fclose(in2);
        fclose(in3);
        return 0;
    }
    fprintf(stdout, "passed\n");
    fprintf(stdout, "\tTest 2 - read_graph: ");
    if (read_graph_testing(in1, graph1, in2, graph2, in3, graph3) == -1) {
        fprintf(stderr, "not passed\n");
        fclose(in1);
        fclose(in2);
        fclose(in3);
        free_graph(graph1);
        free_graph(graph2);
        free_graph(graph3);
        return 0;
    }
    fprintf(stdout, "passed\n");
    fprintf(stdout, "\tTest 3 - does_have_all_edges: ");
    if ((does_have_all_edges_testing(graph1, graph2)) == -1) {
        fprintf(stderr, "not passed\n");
        fclose(in1);
        fclose(in2);
        fclose(in3);
        free_graph(graph1);
        free_graph(graph2);
        free_graph(graph3);
        return 0;
    }
    fprintf(stdout, "passed\n");
    fprintf(stdout, "\n--------------------------------------\n\n");
    fclose(in1);
    fclose(in2);
    fclose(in3);

    return 1;
}

int make_graph_testing(FILE* in1, FILE* in2, FILE* in3) {
    graph1 = make_graph(3, 3);

    if (graph1 == NULL) {
        return -1;
    }

    graph2 = make_graph(7, 4);
    if (graph2 == NULL) {
        return -1;
    }

    graph3 = make_graph(6, 6);
    if (graph3 == NULL) {
        return -1;
    }
    return 0;
}

int read_graph_testing(FILE* in1, graph_t graph1, FILE* in2, graph_t graph2, FILE* in3, graph_t graph3) {
    make_expected_values(graph1);
    if ((read_graph_test(in1, graph1)) == NULL) {
        return -1;
    }

    make_expected_values(graph2);
    if ((read_graph_test(in2, graph2)) == NULL) {
        return -1;
    }

    make_expected_values(graph3);
    if ((read_graph_test(in3, graph3)) == NULL) {
        return -1;
    }

    return 0;
}

