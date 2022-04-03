#include <stdio.h>
#include "tests_data_structures.h"
#include "tests_auxiliary_functions.h"

int test_PQ() {
	
	int no_tests = 10;
	int no_tests_passed = 0;

	printf("\nTesting PriorityQueue in data_structures.c:\n");

	// Test 1
	PriorityQueue pq1 = make_PQ();
	print_test_message( 1, "Initializing object PQ", pq1 != NULL, &no_tests_passed );

	// Test 2
	PQ_put( pq1, 1, 0.5 );	
	print_test_message( 2, "Adding element to PQ (number of elements)", pq1->no_elements == 1, &no_tests_passed );

	// Test 3
	print_test_message( 3, "Adding element to PQ (value of added element)", pq1->vertexes[0] == 1, &no_tests_passed );
	
	// Test 4
	print_test_message( 4, "Adding element to PQ (value of added element's distance", pq1->distances[0] == 0.5, &no_tests_passed );

	printf("\n");
	if( no_tests_passed == no_tests )
		printf("All passed\n");
	else printf("Not passed\n");

	printf("\n--------------------------------------\n\n");

	return no_tests_passed == no_tests ? 1 : 0;
}

int test_Set() {
	
}
