
#include "tests_dijkstra.h"
#include "tests_auxiliary_functions.h"

int test_Dijkstra() {

	int no_tests = 31;
	int no_tests_passed = 0;


	printf("Testing dijkstra.c\n");
	
	FILE * in1 = fopen( "graph1_test_dijkstra.txt", "r" );
	graph_t graph1 = read_graph( in1 );

	int * predecessors1 = dijkstra( graph1, 0 );
	int number_of_elements_in_path1 = 0;
	int * reversed_path1 = determine_path( &number_of_elements_in_path1, predecessors1, 0, 24 );
	
	
	
	if( no_tests_passed == no_tests )
		printf("Passed\n");
	else printf("Failed\n");

	printf("--------------------------------------\n\n");

	return no_tests_passed == no_tests ? 1 : 0;
}
