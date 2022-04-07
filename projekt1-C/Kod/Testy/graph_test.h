#ifndef GRAPH_TEST_H
#define GRAPH_TEST_H

void print_test_message( int no_current_test, int condition);

int graph_test( FILE * in, FILE * out );

int make_graph_testing(FILE *in1,FILE *in2,FILE * in3);
int read_graph_testing(FILE * in1 ,graph_t graph1,FILE * in2,graph_t graph2,FILE * in3,graph_t graph3);
int does_have_all_edges_testing(graph_t graph1, graph_t graph2);

 int does_have_all_edges_test(graph_t graph); 
graph_t test_read_graph(FILE *in ,graph_t graph);
int * expected_values_v ( graph_t graph);
double * expected_values_w (graph_t graph);


void expected_make(graph_t graph);
#endif
