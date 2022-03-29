#include <time.h>

#include "generator.h"


graph_t generate_complete_graph( int columns, int rows, double from_weight, double to_wieght ) {

}

graph_t generate_connected_graph( int columns, int rows, double from_weight, double to_weight ) {

}


graph_t generate_random_graph( int columns, int rows, double from_weight, double to_weight ) {
	
	graph_t graph = make_graph();

	int number_of_vertices = columsn * rows;

	srand( time( NULL ) );

	// generate random number of generated edges
	int max_number_of_edges = 0;
	while( max_number_of_edges == 0 ) {
		max_number_of_edges = rand() % number_of_vertices;
	}

	for( int vertex_from_counter = 0; vertex_from_counter < max_number_of_edges; vertex_from_counter++ ) {
		
		// download array of vertices where edges can lead
		int *potential_neighbors = potential_neighbors( graph, vertex_from_counter );
		
		// find random vertex where current edge can lead
		int vertex_to = -1;
		while( vertex_to == -1 ) {

			int vertex_to_in_array = rand() % 4;
			vertex_to = potential_neighbors[ vertex_to_in_array ];
		}
		// add edge to graph
		graph->adj_mat[vertex_from_counter][vertex_to] = from_weight + ( rand() % (to_weight-from_weight+1) );
		
		free( potential_neighbors );
	}
}
