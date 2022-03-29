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
	int max_number_of_edges = -1;
	do {
		max_number_of_edges = rand() % number_of_vertices * 1.05;
	} while( max_number_of_edges != 0 );

	for( int vertex_from_counter = 0; vertex_from_counter < number_of_vertices; vertex_from_counter ) {
		
	}

}
