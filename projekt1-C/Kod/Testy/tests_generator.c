#include "tests_generator.h"
#include "tests_auxiliary_functions.h"


int test_generate_complete_graph() {
}

int test_generate_connected_graph() {
}

int test_generate_random_graph() {
	
	int number_of_tests = 15;
	int no_tests_passed = 0;

	printf("Testing generate_random_graph() in generator.c\n");

	// Graph 1
	graph_t graph1 = generate_random_graph(5, 5, 0, 1);
	//print_graph( graph1 );
		
	// Test 1
	test_generate_random_graph_number_of_vertices( 1, graph1, &no_tests_passed );
	
	// Test 2
	test_generate_random_graph_edges( 2, graph1, &no_tests_passed );

	// Test 3
	test_generate_random_graph_edges_weights( 3, graph1, 0, 1, &no_tests_passed );
	
	free_graph( graph1 );

	
	// Graph 2
	graph_t graph2 = generate_random_graph( 1, 8, 5, 8 );

	// Test 4
	test_generate_random_graph_number_of_vertices( 4, graph2, &no_tests_passed );
	
	// Test 5
	test_generate_random_graph_edges( 5, graph2, &no_tests_passed );

	// Test 6
	test_generate_random_graph_edges_weights( 6, graph2, 5, 8, &no_tests_passed );

	free_graph( graph2 );
	
	
	// Graph 3
	graph_t graph3 = generate_random_graph( 30, 20, 3, 15 );

	// Test 7
	test_generate_random_graph_number_of_vertices( 7, graph3, &no_tests_passed );
	
	// Test 8
	test_generate_random_graph_edges( 8, graph3, &no_tests_passed );

	// Test 9
	test_generate_random_graph_edges_weights( 9, graph3, 3, 15, &no_tests_passed );

	free_graph( graph3 );


	// Graph 4
	graph_t graph4 = generate_random_graph( 15, 40, 1, 10 );

	// Test 10
	test_generate_random_graph_number_of_vertices( 10, graph4, &no_tests_passed );
	
	// Test 11
	test_generate_random_graph_edges( 11, graph4, &no_tests_passed );

	// Test 12
	test_generate_random_graph_edges_weights( 12, graph4, 1, 10, &no_tests_passed );

	free_graph( graph4 );

	
	// Graph 5
	graph_t graph5 = generate_random_graph( -1, 5, 0, 1 );

	// Test 13
	print_test_message( 13, "generate_random_graph (negative number of vertices)",
			graph5 == NULL, &no_tests_passed );


	// Graph 6
	graph_t graph6 = generate_random_graph( 5, 5, 10, 5 );
	
	// Test 14
	print_test_message( 14, "generate_random_graph (minimal weight greater than maximum weight)",
			graph6 == NULL, &no_tests_passed );


	// Graph 7
	graph_t graph7 = generate_random_graph( 5, 5, -1, 1 );
	
	// Test 15
	print_test_message( 15, "generate_random_graph (negative weight)",
			graph7 == NULL, &no_tests_passed );


	if( number_of_tests == no_tests_passed )
		printf("Passed\n");
	else
		printf("Failed\n");

	printf("--------------------------------------\n\n");

	return number_of_tests == no_tests_passed;
}

int test_generate_random_graph_number_of_vertices( int number_of_current_test,
		graph_t graph, int *no_tests_passed ) {

	print_test_message( number_of_current_test,
			"generate_random_graph (number of vertices greater than 1)",
			graph->no_vertexes > 0, no_tests_passed );
}

int test_generate_random_graph_edges( int number_of_current_test,
		graph_t graph, int *no_tests_passed ) {

	print_test_message( number_of_current_test,
			"generate_random_graph (situation of edges)",
			check_edges(graph), no_tests_passed );
}

int test_generate_random_graph_edges_weights( int number_of_current_test, graph_t graph,
		double from_weight, double to_weight, int *no_tests_passed ) {

	print_test_message( number_of_current_test,
			"generate_random_graph (weights in proper range)",
			check_edges_weights( graph, from_weight, to_weight ), no_tests_passed );
}

int check_edges( graph_t graph ) {
	
	for( int vertex_from = 0; vertex_from < graph->no_vertexes; vertex_from++ )
		for( int vertex_to = 0; vertex_to < graph->no_vertexes; vertex_to++ )
			if( graph->adj_mat[ vertex_from ][ vertex_to ] != -1 ) {
				int is_neighbor_proper = 0;

				// neihgbor to north
				if( vertex_from - graph->columns == vertex_to ) {
					is_neighbor_proper = 1;
					continue;
				}
				// neihgbor to south
				else if( vertex_from + graph->columns == vertex_to ) {
					is_neighbor_proper = 1;
					continue;
				}

				// neighbors to west and east
				// find number of row containing vertex
				int row_number = vertex_from / graph->columns +1;
	
				// find first and last elements of row
				int start_row_number = (row_number-1) * graph->columns;
				int end_row_number = ( (row_number) * graph->columns ) - 1;

				// neighbor to east
				if( start_row_number <= vertex_to && start_row_number < vertex_from
						&& vertex_to < vertex_from ) {
					is_neighbor_proper = 1;
					continue;
				}
				// neighbor to west
				if( vertex_from < end_row_number && vertex_from < vertex_to
						&& vertex_to <= end_row_number )
					is_neighbor_proper = 1;
					continue;

				// if vertex_to is not to north, south, east or west it is inproper
				if( !is_neighbor_proper )
					return 0;
			}
	return 1;
}

int check_edges_weights( graph_t graph, double from_weight, double to_weight ) {
	for( int vertex_from = 0; vertex_from < graph->no_vertexes; vertex_from++ )
		for( int vertex_to = 0; vertex_to < graph->no_vertexes; vertex_to++ ) {
			// check if weight is in proper range (from_weight, to_weight)
			double weight = graph->adj_mat[ vertex_from ][ vertex_to ];
			if( (weight != -1) && ( (weight <= from_weight) || (weight >= to_weight) ) )
					return 0;
		}
	return 1;
}
