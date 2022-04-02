#include <time.h>

#include "generator.h"
#include "data_structures.h"

graph_t generate_complete_graph( int columns, int rows, double from_weight, double to_wieght ) {

}

graph_t generate_connected_graph( int columns, int rows, double from_weight, double to_weight ) {

}


graph_t generate_random_graph( int columns, int rows, double from_weight, double to_weight ) {
	
	graph_t graph = make_graph();

	int number_of_vertices = columns * rows;

	srand( time( NULL ) );

	// generate random number of generated edges in range <1, number_of_vertices>
	int max_number_of_edges = (rand() % number_of_vertices) + 1;


	for( int vertex_from_counter = 0; vertex_from_counter < max_number_of_edges; vertex_from_counter++ ) {
		
		// choose random vertex
		int vertex_from = rand() % number_of_vertices;

		// download array of vertices where edges can lead
		int *potential_neighbors = potential_neighbors( graph, vertex_from_counter );
		
		// add potenital neighbors to set
		Set remaining_neighbors = make_Set();
		for( int i = 0; i < 4; i++ )
			if( potential_neighbors[i] != -1 )
				Set_add( remaining_neighbors, potential_neighbors[i] );

		// try to create edge untill there are remeining_neighbors
		try_to_create_edge( remaining_neighbors );

		free( remaining_neighbors );
		free( potential_neighbors );
	}
	return graph;
}

void try_to_create_random_edge( int vertex_from, Set remaining_neighbors, Graph graph ) {
		
	int did_create_edge = 0;
	while( !did_create_edge || Set_is_empty( remaining_neighbors ) ) {

		// get random vertex from remaining_neighbors
		int vertex_to = Set_pop( remaining_neighbors );

		// create edge only if it doesn't exit and reversed edge doesn't exist
		if( graph->adj_mat[ vertex_from ][ vertex_ to ] == -1 && graph->adj_mat[ vertex_to ][ vertex_from ] == -1 ) {
			graph->adj_mat[ vertex_from ][ vertex_to ] = from_weight + ( rand() % (to_weight-from_weight+1) );
			did_create_edge = 1;
		}
	}	
}
