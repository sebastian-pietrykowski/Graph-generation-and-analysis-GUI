#ifndef GRAPH_H
#define GRAPH_H
#include <stdio.h>

typedef struct {
 int columns;
 int rows;
 int no_vertexes;
 double **adj_mat; /* adjacency matrix stores information about weights between individual vertives */
} * graph_t;

graph_t read_graph ( FILE * in ); /* Function responsible for reading the graph from the input file and creating adjacency matrix */

int does_have_all_edges ( graph_t graph ); /* Function responsible for checking if all the possible edges in the graph exist */

int is_connected ( graph_t graph );

int write_graph( graph_t graph , FILE * out );

void set_vertex( graph_t graph, int from_vertex, int to_vertex, double weight); /* Function responsible for filling in the adjacency matrix with weights */

graph_t make_graph( int columns, int rows); /* Function responsible for initialization graph structure and memory allocation for adj_mat */

int * neighbors( graph_t graph, int vertex);

void free_graph(graph_t graph);

#endif

