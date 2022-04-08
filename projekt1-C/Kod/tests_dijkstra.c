#include <stdlib.h>

#include "tests_dijkstra.h"
#include "tests_auxiliary_functions.h"

int test_Dijkstra() {

	int no_tests = 15;
	int no_tests_passed = 0;


	printf("Testing dijkstra.c\n");
	
	// Graph 1 - connected
	FILE * in1 = fopen( "Graphs/graph1_test_dijkstra_connected.txt", "r" );
	if( in1 == NULL ) {
		fprintf( stderr, "File loaded unsuccessfully in function test_Dijkstra.\n");
		exit(1);
	}
	graph_t graph1 = read_graph( in1 );

	// Test 1
	int * predecessors1 = dijkstra( graph1, 0 );
	int predecessors1_proper[] = { -1, 0, 1, 2, 3, 0, 5, 6, 7, 8, 5, 6, 7, 8, 13, 10, 11, 22, 13, 14, 15, 20, 21, 22, 23 };
	print_test_message( 1, "dijkstra (proper values) (connected graph)", compare_arrays( predecessors1, predecessors1_proper, graph1->no_vertexes ), &no_tests_passed );

	// Tests 2, 3
	int number_of_elements_in_path1 = 0;
	int * reversed_path1 = determine_path( graph1, &number_of_elements_in_path1, predecessors1, 0, 24 );
	print_test_message( 2, "determine_path (number of elements) (connected graph)", number_of_elements_in_path1 == 9, &no_tests_passed );
	int reversed_path1_proper[] = { 24, 23, 22, 21, 20, 15, 10, 5, 0 };
	print_test_message( 3, "determine_path (proper_values) (connected graph)", compare_arrays( reversed_path1, reversed_path1_proper, number_of_elements_in_path1 ), &no_tests_passed );

	fclose( in1 );
	free_graph( graph1 );
	free( predecessors1 );
	free( reversed_path1 );


	// Graph 2 - complete
	FILE * in2 = fopen( "Graphs/graph2_test_dijkstra_complete.txt", "r" );
	if( in2 == NULL ) {
		fprintf( stderr, "File loaded unsuccessfully in function test_Dijkstra.\n");
		exit(1);
	}
	graph_t graph2 = read_graph( in2 );

	// Test 4
	int * predecessors2 = dijkstra( graph2, 0 );
	print_test_message( 4, "dijkstra (proper values) (complete graph)",
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
	int * reversed_path2 = determine_path( graph2, &number_of_elements_in_path2, predecessors2, 0, 24 );
	print_test_message( 5, "determine_path (number of elements) (complete graph)", number_of_elements_in_path2 == 9, &no_tests_passed );
	int reversed_path2_proper[] = { 24, 19, 18, 13, 12, 7, 6, 1, 0 };
	print_test_message( 6, "determine_path (proper_values) (complete graph)", compare_arrays( reversed_path2, reversed_path2_proper, number_of_elements_in_path2 ), &no_tests_passed );

	fclose( in2 );
	free_graph( graph2 );
	free( predecessors2 );
	free( reversed_path2 );


	// Graph 3 - connected with cycle
	FILE * in3 = fopen( "Graphs/graph3_test_dijkstra_connected_with_cycle.txt", "r" );
	if( in3 == NULL ) {
		fprintf( stderr, "File loaded unsuccessfully in function test_Dijkstra.\n");
		exit(1);
	}
	graph_t graph3 = read_graph( in3 );

	// Test 7
	int * predecessors3 = dijkstra( graph3, 0 );
	int predecessors3_proper[] = { -1, 6, 1, 2, 3, 0, 5, 2, 7, 8, 5, 10, 11, 8, 9, 10, 11, 12, 13, 14, 15, 20, 21, 22, 23 };
	print_test_message( 7, "dijkstra (proper values) (connected graph with cycle)", compare_arrays( predecessors3, predecessors3_proper, graph3->no_vertexes ), &no_tests_passed );

	// Tests 8, 9
	int number_of_elements_in_path3 = 0;
	int * reversed_path3 = determine_path( graph3, &number_of_elements_in_path3, predecessors3, 0, 24 );
	print_test_message( 8, "determine_path (number of elements) (connected graph witb cycle)", number_of_elements_in_path3 == 9, &no_tests_passed );
	int reversed_path3_proper[] = { 24, 23, 22, 21, 20, 15, 10, 5, 0 };
	print_test_message( 9, "determine_path (proper_values) (connected graph with cycle)", compare_arrays( reversed_path3, reversed_path3_proper, number_of_elements_in_path3 ), &no_tests_passed );

	fclose( in3 );
	free_graph( graph3 );
	free( predecessors3 );
	free( reversed_path3 );


	// Graph 4 - 1x8
	FILE * in4 = fopen( "Graphs/graph4_test_dijkstra_1_column.txt", "r" );
	if( in4 == NULL ) {
		fprintf( stderr, "File loaded unsuccessfully in function test_Dijkstra.\n");
		exit(1);
	}
	graph_t graph4 = read_graph( in4 );

	// Test 10
	int * predecessors4 = dijkstra( graph4, 0 );
	int predecessors4_proper[] = { -1, 0, 1, 2, 3, 4, 5, 6 };
	print_test_message( 10, "dijkstra (proper values) (graph 1x8)", compare_arrays( predecessors4, predecessors4_proper, graph4->no_vertexes ), &no_tests_passed );

	// Tests 11, 12
	int number_of_elements_in_path4 = 0;
	int * reversed_path4 = determine_path( graph4, &number_of_elements_in_path4, predecessors4, 0, 7 );
	print_test_message( 11, "determine_path (number of elements) (graph 1x8)", number_of_elements_in_path4 == 8, &no_tests_passed );
	int reversed_path4_proper[] = { 7, 6, 5, 4, 3, 2, 1, 0 };
	print_test_message( 12, "determine_path (proper_values) (graph 1x8)", compare_arrays( reversed_path4, reversed_path4_proper, number_of_elements_in_path4 ), &no_tests_passed );

	fclose( in4 );
	free_graph( graph4 );
	free( predecessors4 );
	free( reversed_path4 );


	// Graph 5 - unconnected
	FILE * in5 = fopen( "Graphs/graph5_test_dijkstra_unconnected.txt", "r" );
	if( in5 == NULL ) {
		fprintf( stderr, "File loaded unsuccessfully in function test_Dijkstra.\n");
		exit(1);
	}
	graph_t graph5 = read_graph( in5 );

	// Test 13
	int * predecessors5 = dijkstra( graph5, 0 );
	int predecessors5_proper[] = { -1, 0, 1, 2, 3, 0, 5, 6, 7, 8, 5, 6, 7, 8, -1, 10, 11, 22, 13, -1, 15, 20, 21, 22, 23 };
	print_test_message( 13, "dijkstra (proper values) (unconnected graph)", compare_arrays( predecessors5, predecessors5_proper, graph5->no_vertexes ), &no_tests_passed );

	// Tests 14, 15
	int number_of_elements_in_path5 = 0;
	int * reversed_path5 = determine_path( graph5, &number_of_elements_in_path5, predecessors5, 0, 19 );
	print_test_message( 14, "determine_path (number of elements) (unconnected graph)", number_of_elements_in_path5 == 0, &no_tests_passed );
	print_test_message( 15, "determine_path (proper_values) (unconnected graph)", reversed_path5 == NULL, &no_tests_passed );

	fclose( in5 );
	free_graph( graph5 );
	free( predecessors5 );


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
