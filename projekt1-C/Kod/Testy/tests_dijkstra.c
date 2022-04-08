#include <stdlib.h>

#include "tests_dijkstra.h"
#include "tests_auxiliary_functions.h"

int test_Dijkstra() {

	int no_tests = 31;
	int no_tests_passed = 0;


	printf("Testing dijkstra.c\n");
	
	// Graph 1 - connected
	FILE * in1 = fopen( "graph1_test_dijkstra_connected.txt", "r" );
	graph_t graph1 = read_graph( in1 );

	// Test 1
	int * predecessors1 = dijkstra( graph1, 0 );
	int predecessors1_proper[] = { -1, 0, 1, 2, 3, 0, 5, 6, 7, 8, 5, 6, 7, 8, 13, 10, 11, 22, 13, 14, 15, 20, 21, 22, 23 };
	print_test_message( 1, "dijkstra (proper values)", compare_arrays( predecessors1, predecessors1_proper, graph1->no_vertexes ), &no_tests_passed );

	// Tests 2, 3
	int number_of_elements_in_path1 = 0;
	int * reversed_path1 = determine_path( &number_of_elements_in_path1, predecessors1, 0, 24 );
	print_test_message( 2, "determine_path (number of elements)", number_of_elements_in_path1 == 9, &no_tests_passed );
	int reversed_path1_proper[] = { 24, 23, 22, 21, 20, 15, 10, 5, 0 };
	print_test_message( 3, "determine_path (proper_values)", compare_arrays( reversed_path1, reversed_path1_proper, number_of_elements_in_path1 ), &no_tests_passed );

	fclose( in1 );
	free_graph( graph1 );
	free( predecessors1 );
	free( reversed_path1 );


	// Graph 2 - complete
	FILE * in2 = fopen( "graph2_test_dijkstra_complete.txt", "r" );
	graph_t graph2 = read_graph( in2 );

	// Test 4
	int * predecessors2 = dijkstra( graph2, 0 );
	print_test_message( 4, "dijkstra (proper values)",
		      	(
			predecessors2[0] == -1 && predecessors2[1] != -1 && predecessors2[2] != -1 &&
			predecessors2[3] != -1 && predecessors2[4] != -1 && predecessors2[5] != -1 &&
			predecessors2[6] == 1 && predecessors2[7] == 6 && predecessors2[8] != -1 &&
			predecessors2[9] != -1 && predecessors2[10] != -1 && predecessors2[11] != -1 &&
			predecessors2[12] == 7 && predecessors2[13] == 12 && predecessors2[14] != -1 &&
			predecessors2[15] != -1 && predecessors2[16] != -1 && predecessors2[17] != -1 &&
			predecessors2[18] == 13 && predecessors2[19] == 18 && predecessors2[20] != -1 &&
			predecessors2[21] != -1 && predecessors2[22] != -1 && predecessors2[23] != -1 &&
			predecessors2[24] == 19
			),
			&no_tests_passed );

	// Tests 5, 6
	int number_of_elements_in_path2 = 0;
	int * reversed_path2 = determine_path( &number_of_elements_in_path2, predecessors2, 0, 24 );
	print_test_message( 5, "determine_path (number of elements)", number_of_elements_in_path2 == 9, &no_tests_passed );
	int reversed_path2_proper[] = { 24, 19, 18, 13, 12, 7, 6, 1, 0 };
	print_test_message( 6, "determine_path (proper_values)", compare_arrays( reversed_path2, reversed_path2_proper, number_of_elements_in_path2 ), &no_tests_passed );

	fclose( in2 );
	free_graph( graph2 );
	free( predecessors2 );
	free( reversed_path2 );


	
	if( no_tests_passed == no_tests )
		printf("Passed\n");
	else printf("Failed\n");

	printf("--------------------------------------\n\n");

	return no_tests_passed == no_tests ? 1 : 0;
}

int compare_arrays( int * array1, int *array2, int length ) {
	for( int i = 0; i < length; i++ )
		if( array1[i] != array2[i] )
			return 0;
	return 1;
}
