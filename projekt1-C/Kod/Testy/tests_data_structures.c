#include <stdio.h>
#include "tests_data_structures.h"
#include "tests_auxiliary_functions.h"

int test_PQ() {
	
	int no_tests = 10;
	int no_tests_passed = 0;

	printf("Testing PriorityQueue in data_structures.c:\n");

	// Tests 1-
	PriorityQueue pq1 = make_PQ();
	PQ_put( pq1, 1, 0.5 );

	// Test 1
	printf("\tTest 1: ");
	if( pq1 != NULL ) {
		no_tests_passed++;
		printf("passed\n");
	}
	else
		printf("failed\n");

	// Test 2
	print_test_message( 2, pq1->no_elements == 1, &no_tests_passed );

	
	printf("\n");
	if( no_tests_passed == no_tests )
		printf("All passed\n");
	else printf("Not passed\n");

	printf("\n--------------------------------------\n\n");

	return no_tests_passed == no_tests ? 1 : 0;
}

int test_Set() {
	
}
