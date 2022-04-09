#include <getopt.h>
#include <stdio.h>
#include <stdlib.h>

#include "bfs.h"
#include "bfs_test.h"
#include "graph.h"
#include "graph_test.h"

graph_t graph1;
graph_t graph2;
graph_t graph3;

int main(int argc, char** argv) {
	FILE * in1 = NULL;
	FILE * in2 = NULL;
	FILE * in3 = NULL;
	if(argc == 4) {
    in1 = fopen(argv[1], "r");
  
    in2 = fopen(argv[2], "r");

    in3 = fopen(argv[3], "r");
	}
	fprintf(stdout,"\nTesting graph module:\n");
	fprintf(stdout,"\tTesting make_graph function: ");
    if (make_graph_testing(in1, in2, in3) == -1) {
        fprintf(stderr, "test not passed\n");
    }
	fprintf(stdout,"test passed\n");
        fprintf(stdout,"\tTesting read_graph function: ");
    if (read_graph_testing(in1, graph1, in2, graph2, in3, graph3) == -1) {
        fprintf(stderr, "test not passed\n");
   }
 //   free_graph(graph1);
   // free_graph(graph2);
   // free_graph(graph3);
    
	fprintf(stdout,"test passed\n");
	fprintf(stdout,"\tTesting does_have_all_edges function: ");
    if ((does_have_all_edges_testing(graph1, graph2)) == -1) {
        fprintf(stderr, "test not passed\n");
    }
	fprintf(stdout,"test passed\n");
	fprintf(stdout,"\nTesting BFS module:\n ");
    if (bfs_testing(graph1, graph2, graph3) == -1) {
        fprintf(stderr, "\ttest not passed\n");
    }
    free_graph(graph1);
    free_graph(graph2);
    free_graph(graph3);
    fclose(in1);
    fclose(in2);
    fclose(in3);
	fprintf(stdout,"\n\nAll tests passed\n");
	fprintf(stdout,"\n------------------------------------------------");
    return EXIT_SUCCESS;
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

int does_have_all_edges_testing(graph_t graph1, graph_t graph2) {
    if ((does_have_all_edges_test(graph1)) != 24) {
        return -1;
    }
 
    if ((does_have_all_edges_test(graph2)) != 90) {
        return -1;
    }
   
    return 0;
}

int bfs_testing(graph_t graph1, graph_t graph2, graph_t graph3) {
    expected_bfs_make(graph1);
    if (bfs_test(graph1, 0) == -1) {
        return -1;
    }

    
    expected_bfs_make(graph2);
    if (bfs_test(graph2, 0) == -1) {
        return -1;
    }

    expected_bfs_make(graph3);
    if (bfs_test(graph3, 0) == -1) {
        return -1;
    }

    return 0;
}