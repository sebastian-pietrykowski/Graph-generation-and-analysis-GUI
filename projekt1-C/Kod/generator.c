#include <time.h>

#include "generator.h"
#include "data_structures.h"

graph_t generate_complete_graph( int columns, int rows, double from_weight, double to_wieght ) {

}

graph_t generate_connected_graph( int columns, int rows, double from_weight, double to_weight ) {

	graph_t = make_graph();
	int number_of_vertices = columns * rows;
	
	int *visited = malloc( sizeof(int) * number_of_vertices );
	for(int i = 0; i < number_of_vertices; i++ )
		visited[i] = 0;
	

	int vertex_from = 0;
	visited[vertex_from] = 1;

	add_edge_to_neighbor( vertex_from, graph, visited );
	


	Set potential_start_vertices = make_Set();
	for( int i = 0; i < number_of_vertices; i++ )
		if( visited[i] == 1 )
			Set_add( potential_start_vertices, i );

	int did_add = 0;

	while( !did_add || !Set_is_empty( potential_start_vertices ) ) {
		vertex_from = Set_pop( potential_start_vertices );
		did_add = add_edge_to_neighbor( vertex_from, graph, visited );
	}
	free( potential_start_vertices );



	free( potential_neighbors );
	

	return graph;
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
		int number_of_potential_neighbors = 0;
		int *potential_neighbors = potential_neighbors( graph, vertex_from_counter, &number_of_potential_neighbors );
		
		// add potenital neighbors to set
		Set remaining_neighbors = make_Set();
		for( int i = 0; i < number_of_potential_neighbors; i++ )
			Set_add( remaining_neighbors, potential_neighbors[i] );

		// try to create edge untill there are remeining_neighbors
		try_to_create_edge( vertex_from, remaining_neighbors, graph );

		free( remaining_neighbors );
		free( potential_neighbors );
	}
	return graph;
}


int add_edge_to_neighbor( int vertex_from, graph_t graph, int * visited ) {

	int number_of_potenital_neighbors = 0;
	int *potential_neighbors = potential_neighbors( graph, vertex_from, number_of_potential_neighbors );

	if( number_of_potential_neighbors > 0 ) {
		srand(time(NULL));
		int next_vertex_number = rand() % number_of_potential_neighbors;

		graph->adj_mat[start_graph_vertex][next_vertex_number] = rand() % (to_weight-from_weight+1) + from_weight;
		visited[next_vertex_number] = 1;
		return 1;
	}
	free( potential_neighbors );
	return 0;
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
