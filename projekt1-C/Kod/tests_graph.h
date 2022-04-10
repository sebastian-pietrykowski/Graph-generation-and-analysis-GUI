#ifndef GRAPH_TEST_H
#define GRAPH_TEST_H

int test_Graph(void);

int make_graph_testing(FILE *in1, FILE *in2, FILE *in3);

int read_graph_testing(FILE *in1, graph_t graph1, FILE *in2, graph_t graph2, FILE *in3, graph_t graph3);

int does_have_all_edges_testing(graph_t graph1, graph_t graph2);

int does_have_all_edges_test(graph_t graph);

graph_t read_graph_test(FILE *in, graph_t graph);

int *return_expected_vertexes(graph_t graph);

double *return_expected_weights(graph_t graph);

void make_expected_values(graph_t graph);

#endif

