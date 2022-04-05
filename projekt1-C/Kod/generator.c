#include <time.h>
#include <stdlib.h>
#include <unistd.h>

#include "generator.h"

graph_t generate_complete_graph( int columns, int rows, double from_weight, double to_weight ) {
	
	graph_t graph = make_graph( columns, rows ); 
	int number_of_vertices = columns * rows;

	// add edges horizontally
	for( int vertical_counter = 0; vertical_counter < number_of_vertices; vertical_counter++ )
		for( int horizontal_counter = 1; horizontal_counter < number_of_vertices; horizontal_counter++ ) {
			
			int direction = rand() % 2; /* direction of edge:
						       0 - from lesser to greater,
						       1 - from greater to lesser */
			int vertex1 = vertical_counter * graph->columns + horizontal_counter - 1;
			int vertex2 = vertex1 + 1;
			double wage = ( (rand()/RAND_MAX) * (to_weight-from_weight) ) + from_weight;
			
			if( direction == 0 )
				graph->adj_mat[vertex1][vertex2] = wage;
			else
				graph->adj_mat[vertex2][vertex2] = wage;

		}
	// add edges vertically
	for( int horizontal_counter = 0; horizontal_counter < number_of_vertices; horizontal_counter++ )
		for( int vertical_counter = 1; vertical_counter < number_of_vertices; vertical_counter++ ) {
			
			int direction = rand() % 2; /* direction of edge:
						       0 - from lesser to greater,
						       1 - from greater to lesser */
			int vertex1 = (vertical_counter-1) * graph->columns + horizontal_counter;
			int vertex2 = vertical_counter * graph->columns + horizontal_counter;
			double wage = ( (rand()/RAND_MAX) * (to_weight-from_weight) ) + from_weight;
			
			if( direction == 0 )
				graph->adj_mat[vertex1][vertex2] = wage;
			else
				graph->adj_mat[vertex2][vertex2] = wage;
		}
}

graph_t generate_connected_graph( int columns, int rows, double from_weight, double to_weight ) {

	graph_t graph = make_graph(columns, rows);
	int number_of_vertices = columns * rows;
	
	// create array to mark visited vertices
	int *visited = malloc( sizeof(int) * number_of_vertices );
	for(int i = 0; i < number_of_vertices; i++ )
		visited[i] = 0;
	
	// select first vertex and create edge to any neighboring vertex
	int vertex_from = 0;
	visited[vertex_from] = 1;
	add_edge_to_neighbor( vertex_from, graph, visited, from_weight, to_weight );
	

	/* Until all vertices are marked as visited add next edges.
	 * Chose random vertex from visited vertices and add edge to one
	 * of its unvisited neighbors, if there are no unvisited neighbor
	 * vertices, try to do it with the next from already visited vertex. */
	while( are_all_vertices_visited( visited, graph->no_vertexes ) != 0) {
		
		Set potential_start_vertices = make_Set();
		for( int index = 0; index < number_of_vertices; index++ )
			if( visited[index] == 1 )
				Set_add( potential_start_vertices, index );
		int did_add = 0;
		while( !did_add || !Set_is_empty( potential_start_vertices ) ) {
			vertex_from = Set_pop( potential_start_vertices );
			did_add = add_edge_to_neighbor( vertex_from, graph, visited, from_weight, to_weight );
		}
		free( potential_start_vertices );
	}
	
	return graph;
}

graph_t generate_random_graph( int columns, int rows, double from_weight, double to_weight ) {
	
	graph_t graph = make_graph( columns, rows );

	int number_of_vertices = columns * rows;

	// generate random number of generated edges in range <1, number_of_vertices>
	int max_number_of_edges = (rand() % number_of_vertices) + 1;
	printf("Number of edges: %d\n", max_number_of_edges);


	for( int vertex_from_counter = 0; vertex_from_counter < max_number_of_edges; vertex_from_counter++ ) {
		
		// choose random vertex
		int vertex_from = rand() % number_of_vertices;

		// download array of vertices where edges can lead
		int number_of_potential_neighbors = 0;
		int *potential_neighbors_array = potential_neighbors( graph, vertex_from, &number_of_potential_neighbors );
		
		printf("Edge from vertex %d\n", vertex_from);
		for( int i = 0; i < number_of_potential_neighbors; i++ )
			printf(" %d", potential_neighbors_array[i] );
		printf("\n");

		// add potenital neighbors to set
		Set remaining_neighbors = make_Set();
		for( int i = 0; i < number_of_potential_neighbors; i++ )
			Set_add( remaining_neighbors, potential_neighbors_array[i] );

		// try to create edge untill there are no remaining_neighbors
		try_to_create_random_edge( vertex_from, remaining_neighbors, graph, from_weight, to_weight );

		free( remaining_neighbors );
		free( potential_neighbors_array );
	}
	return graph;
}


int add_edge_to_neighbor( int vertex_from, graph_t graph, int * visited,
		double from_weight, double to_weight ) {

	int number_of_potential_neighbors = 0;

	int *potential_neighbors_array = potential_neighbors( graph, vertex_from, &number_of_potential_neighbors );

	if( number_of_potential_neighbors > 0 ) {
		int next_vertex_number = rand() % number_of_potential_neighbors;

		graph->adj_mat[vertex_from][next_vertex_number] = (rand()/RAND_MAX) * (to_weight-from_weight) + from_weight;
		visited[next_vertex_number] = 1;
		return 1;
	}
	free( potential_neighbors_array );
	return 0;
}

int are_all_vertices_visited( int * visited, int number_of_vertices ) {
	
	for( int i = 0; i < number_of_vertices; i++ )
		if( visited[i] == 0 )
			return 0;
	return 1;
}

void try_to_create_random_edge( int vertex_from, Set remaining_neighbors,
		graph_t graph, double from_weight, double to_weight ) {
	printf("trying to create edge\n");		
	int did_create_edge = 0;
	while( !did_create_edge && !Set_is_empty( remaining_neighbors ) ) {

		printf("Set_is_empty: %d\n", Set_is_empty( remaining_neighbors ) );
		// get random vertex from remaining_neighbors
		int vertex_to = Set_pop( remaining_neighbors );

		// create edge only if it doesn't exit and reversed edge doesn't exist
		if( graph->adj_mat[ vertex_from ][ vertex_to ] == -1 
				&& graph->adj_mat[ vertex_to ][ vertex_from ] == -1 ) {

			graph->adj_mat[ vertex_from ][ vertex_to ] = from_weight + ( (rand()/RAND_MAX) * (to_weight-from_weight) );
			did_create_edge = 1;
			printf("created edge: %d -> %d\n", vertex_from, vertex_to);
		}
		printf("+\n");
	}
	printf("\n");	
}
