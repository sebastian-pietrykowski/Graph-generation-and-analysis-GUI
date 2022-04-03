#include <stdio.h>
#include <limits.h>
#include "tests_data_structures.h"
#include "tests_auxiliary_functions.h"

int test_PQ() {
	
	int no_tests = 10;
	int no_tests_passed = 0;

	int taken_element = INT_MAX;

	printf("\nTesting PriorityQueue in data_structures.c:\n");
	
	
	PriorityQueue pq1 = make_PQ();

	// Test 1
	print_test_message( 1, "make_PQ (initializing object PQ)", pq1 != NULL, &no_tests_passed );

	PQ_put( pq1, 1, 0.5 );
	// pq1 = { (1,0.5) }
	
	// Test 2
	test_PQ_put_number_of_elements( 2, pq1, 1, &no_tests_passed );

	// Test 3
	test_PQ_put_value_of_element( 3, pq1, 0, 1, &no_tests_passed );
	
	// Test 4
	test_PQ_put_value_of_distance( 4, pq1, 0, 0.5, &no_tests_passed );

	PQ_put( pq1, 3, 1 );
	// pq1 = { (1,0.5), (3,1) }
	
	// Test 5
	test_PQ_put_number_of_elements( 5, pq1, 2, &no_tests_passed );

	// Test 6
	test_PQ_put_value_of_element( 6, pq1, 1, 3, &no_tests_passed );
	
	// Test 7
	test_PQ_put_value_of_distance( 7, pq1, 1, 1, &no_tests_passed );
	
	PQ_put( pq1, 5, 1.5 );
	// pq1 = { (1,0.5), (3,1), (5,1.5) }
	
	// Test 8
	test_PQ_put_number_of_elements( 8, pq1, 3, &no_tests_passed );

	// Test 9
	test_PQ_put_value_of_element( 9, pq1, 2, 5, &no_tests_passed );
	
	// Test 10
	test_PQ_put_value_of_distance( 10, pq1, 2, 1.5, &no_tests_passed );


	PQ_put( pq1, 7, 2 );
	// pq1 = { (1,0.5), (3,1), (5,1.5), (7,2) }

	// Test 11
	test_PQ_put_number_of_elements( 11, pq1, 4, &no_tests_passed );

	// Test 12
	test_PQ_put_value_of_element( 12, pq1, 3, 7, &no_tests_passed );
	
	// Test 13
	test_PQ_put_value_of_distance( 13, pq1, 3, 2, &no_tests_passed );

	taken_element = PQ_get( pq1 ); // (1,0.5)
	// pq1 = { (3,1), (5,1.5), (7,2) }
	
	// Test 14
	test_PQ_get_value_of_taken_element( 14, taken_element, 1, &no_tests_passed );

	// Test 15
	test_PQ_get_number_of_elements( 15, pq1, 3, &no_tests_passed );

	// Test 16
	print_test_message( 16, "PQ_get (remaining values in PQ)",
			pq1->vertexes[0] == 3 && pq1->vertexes[1] == 5 && pq1->vertexes[2] == 7,
			&no_tests_passed );
	// Test 17
	print_test_message( 17, "Pq_get (remaining distances in PQ)",
			pq1->distances[0] == 1 && pq1->distances[1] == 1.5 && pq1->distances[2] == 2,
			&no_tests_passed );



	printf("\n");
	if( no_tests_passed == no_tests )
		printf("All passed\n");
	else printf("Not passed\n");

	printf("\n--------------------------------------\n\n");

	return no_tests_passed == no_tests ? 1 : 0;
}

int test_Set() {
	
}
void test_PQ_get_number_of_elements( int number_of_current_test, PriorityQueue pq, int predicted_number_of_elements, int *no_tests_passed ) {
	print_test_message( number_of_current_test, "PQ_get (number of elements in PQ)",
			pq->no_elements == predicted_number_of_elements, no_tests_passed );
}

void test_PQ_get_value_of_taken_element( int number_of_current_test, int actual_value, int predicted_value, int *no_tests_passed ) {
	print_test_message( number_of_current_test, "PQ_get (value of taken element)",
			actual_value == predicted_value, no_tests_passed );
}


void test_PQ_put_number_of_elements( int number_of_current_test, PriorityQueue pq, int predicted_number_of_elements, int *no_tests_passed ) {
	print_test_message( number_of_current_test, "PQ_put (number of elements in PQ)",
			pq->no_elements == predicted_number_of_elements, no_tests_passed );
}


void test_PQ_put_value_of_element( int number_of_current_test, PriorityQueue pq, int index, int predicted_value, int *no_tests_passed ) {
	print_test_message( number_of_current_test, "PQ_put (value of element with given index)",
			pq->vertexes[index] == predicted_value, no_tests_passed );
}

void test_PQ_put_value_of_distance( int number_of_current_test, PriorityQueue pq, int index, double predicted_value, int *no_tests_passed ) {
	print_test_message( number_of_current_test, "PQ_put (value of element's distance)",
			pq->distances[index] == predicted_value, no_tests_passed );
}

