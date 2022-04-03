#include <stdio.h>
#include <limits.h>
#include "tests_data_structures.h"
#include "tests_auxiliary_functions.h"

int test_PQ() {
	
	int no_tests = 31;
	int no_tests_passed = 0;

	int taken_element = INT_MAX;

	printf("Testing PriorityQueue in data_structures.c:\n");
	
	
	PriorityQueue pq1 = make_PQ();

	// Test 1
	print_test_message( 1, "make_PQ (initializing object PQ)", pq1 != NULL, &no_tests_passed );


	PQ_put( pq1, 1, 0.5 );
	// pq1 = { (1,0.5) }
	
	// Tests 2, 3, 4
	test_PQ_put( 2, pq1, 1, 0, 1, 0.5, &no_tests_passed );


	PQ_put( pq1, 3, 1 );
	// pq1 = { (1,0.5), (3,1) }

	// Tests 5, 6, 7	
	test_PQ_put( 5, pq1, 2, 1, 3, 1, &no_tests_passed );
	

	PQ_put( pq1, 5, 1.5 );
	// pq1 = { (1,0.5), (3,1), (5,1.5) }
	
	// Tests 8, 9, 10
	test_PQ_put( 8, pq1, 3, 2, 5, 1.5, &no_tests_passed );


	PQ_put( pq1, 7, 2 );
	// pq1 = { (1,0.5), (3,1), (5,1.5), (7,2) }

	// Tests 11, 12, 13
	test_PQ_put( 11, pq1, 4, 3, 7, 2, &no_tests_passed );


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
	

	taken_element = PQ_get( pq1 ); // (3,1)
	// pq1 = { (5,1.5), (7,2) }
	
	// Test 18
	test_PQ_get_value_of_taken_element( 18, taken_element, 3, &no_tests_passed );

	// Test 19
	test_PQ_get_number_of_elements( 19, pq1, 2, &no_tests_passed );

	// Test 20
	print_test_message( 20, "PQ_get (remaining values in PQ)",
			pq1->vertexes[0] == 5 && pq1->vertexes[1] == 7, &no_tests_passed );
	// Test 21
	print_test_message( 21, "Pq_get (remaining distances in PQ)",
			pq1->distances[0] == 1.5 && pq1->distances[1] == 2, &no_tests_passed );
	
	
	taken_element = PQ_get( pq1 ); // (5,1.5)
	// pq1 = { (7,2) }
	
	// Test 22
	test_PQ_get_value_of_taken_element( 22, taken_element, 5, &no_tests_passed );

	// Test 23
	test_PQ_get_number_of_elements( 23, pq1, 1, &no_tests_passed );

	// Test 24
	print_test_message( 24, "PQ_get (remaining values in PQ)",
			pq1->vertexes[0] == 7, &no_tests_passed );
	// Test 25
	print_test_message( 25, "Pq_get (remaining distances in PQ)",
			pq1->distances[0] == 2, &no_tests_passed );


	taken_element = PQ_get( pq1 ); // (7,2)
	// pq1 = {  }
	
	// Test 26
	test_PQ_get_value_of_taken_element( 26, taken_element, 7, &no_tests_passed );

	// Test 27
	test_PQ_get_number_of_elements( 27, pq1, 0, &no_tests_passed );


	taken_element = PQ_get( pq1 );
	
	// Test 28
	test_PQ_get_value_of_taken_element( 28, taken_element, -1, &no_tests_passed );


	PQ_put( pq1, 4, 1.2 );
	// pq1 = { (4,1.2) }

	// Tests 29, 30, 31
	test_PQ_put( 29, pq1, 1, 0, 4, 1.2, &no_tests_passed );
	
	
	free_PQ( pq1);

	if( no_tests_passed == no_tests )
		printf("Passed\n");
	else printf("Failed\n");

	printf("--------------------------------------\n\n");

	return no_tests_passed == no_tests ? 1 : 0;
}

int test_Set() {

	int no_tests = 49;
	int no_tests_passed = 0;

	int taken_element = INT_MAX;

	printf("Testing Set in data_structures.c:\n");
	
	Set set1 = make_Set();
	// set1 = {}
	
	// Test 1
	print_test_message( 1, "make_Set (initializing object Set)", set1 != NULL, &no_tests_passed );

	// Test 2
	test_Set_is_empty_true( 2, set1, &no_tests_passed );

	// Test 3
	test_Set_is_element_in_false( 3, set1, 0, &no_tests_passed );

	// Test 4
	test_Set_is_element_in_false( 4, set1, 10, &no_tests_passed );


	Set_add( set1, 3 );
	// set1 = { 3 }

	// Tests 5, 6
	test_Set_add( 5, set1, 1, 0, 3, &no_tests_passed );

	// Test 7
	test_Set_is_element_in_true( 7, set1, 3, &no_tests_passed );

	// Test 8
	test_Set_is_empty_false( 8, set1, &no_tests_passed );


	Set_add( set1, 8 );
	// set1 = { 3, 8 }

	// Tests 9, 10
	test_Set_add( 9, set1, 2, 1, 8, &no_tests_passed );


	Set_add( set1, 11 );
	// set1 = { 3, 8, 11 }

	// Tests 11, 12
	test_Set_add( 11, set1, 3, 2, 11, &no_tests_passed );


	Set_add( set1, 7 );
	// set1 = { 3, 8, 11, 7 }

	// Tests 13, 14
	test_Set_add( 13, set1, 4, 3, 7, &no_tests_passed );
	
	// Test 15
	test_Set_is_element_in_true( 15, set1, 11, &no_tests_passed );

	// Test 16
	test_Set_is_empty_false( 16, set1, &no_tests_passed );


	Set_add( set1, 11 ); // set already contains it
	// set = { 3, 8, 11, 7 }

	// Test 17
	test_Set_add_number_of_elements( 17, set1, 4, &no_tests_passed );
	
	
	Set_remove( set1, 8 );
	// set1 = { 3, 11, 7 }
	
	// Test 18
	test_Set_remove_number_of_elements( 18, set1, 3, &no_tests_passed );

	// Test 19
	test_Set_is_element_in_true( 19, set1, 3, &no_tests_passed );

	// Test 20
	test_Set_is_element_in_true( 20, set1, 11, &no_tests_passed );

	// Test 21
	test_Set_is_element_in_true( 21, set1, 7, &no_tests_passed );

	// Test 22
	test_Set_is_element_in_false( 22, set1, 8, &no_tests_passed );

	
	Set_remove( set1, 3 );
	// set1 = { 11, 7 }
	
	// Test 23
	test_Set_remove_number_of_elements( 23, set1, 2, &no_tests_passed );

	// Test 24
	test_Set_is_element_in_true( 24, set1, 11, &no_tests_passed );

	// Test 25
	test_Set_is_element_in_true( 25, set1, 7, &no_tests_passed );

	// Test 26
	test_Set_is_element_in_false( 26, set1, 3, &no_tests_passed );

	// Test 27
	test_Set_is_element_in_false( 27, set1, 8, &no_tests_passed );


	Set_remove( set1, 7 );
	// set1 = { 11 }
	
	// Test 28
	test_Set_remove_number_of_elements( 28, set1, 1, &no_tests_passed );

	// Test 29
	test_Set_is_element_in_true( 29, set1, 11, &no_tests_passed );

	// Test 30
	test_Set_is_element_in_false( 30, set1, 7, &no_tests_passed );

	// Test 31
	test_Set_is_element_in_false( 31, set1, 3, &no_tests_passed );

	// Test 32
	test_Set_is_element_in_false( 32, set1, 8, &no_tests_passed );


	Set_remove( set1, 11 );
	// set1 = {  }
	
	// Test 33
	test_Set_remove_number_of_elements( 33, set1, 0, &no_tests_passed );

	// Test 34
	test_Set_is_element_in_false( 34, set1, 11, &no_tests_passed );

	// Test 35
	test_Set_is_element_in_true( 35, set1, 7, &no_tests_passed );

	// Test 36
	test_Set_is_element_in_false( 36, set1, 3, &no_tests_passed );

	// Test 37
	test_Set_is_element_in_false( 37, set1, 8, &no_tests_passed );
	
	// Test 38
	test_Set_is_empty_true( 38, set1, &no_tests_passed );	

	
	Set_add( set1, 15 );
	// set1 = { 15 }
	
	// Tests 39, 40
	test_Set_add( 39, set1, 1, 0, 15, &no_tests_passed );

	// Test 41
	test_Set_is_empty_false( 41, set1, &no_tests_passed );

	
	Set_add( set1, 16 );
	// set1 = { 15, 16 }
	
	// Test 42
	test_Set_add( 42, set1, 2, 1, 16, &no_tests_passed );
	
		
	taken_element = Set_pop( set1 );
	// set1 - contains 1 element: 15 or 16
	
	// Test 43
	print_test_message( 43, "Set_pop", taken_element == 15 || taken_element == 16, &no_tests_passed );

	// Test 44
	test_Set_pop_number_of_elements( 44, set1, 1, &no_tests_passed );

	taken_element = Set_pop( set1 );
	// set = { }
	
	// Test 45
	print_test_message( 45, "Set_pop", taken_element == 15 || taken_element == 16, &no_tests_passed );

	// Test 46
	test_Set_pop_number_of_elements( 46, set1, 0, &no_tests_passed );

	// Test 47
	test_Set_is_element_in_false( 47, set1, 15, &no_tests_passed );

	// Test 48
	test_Set_is_element_in_false( 48, set1, 16, &no_tests_passed );

	// Test 49
	test_Set_is_empty_true( 49, set1, &no_tests_passed );
	


	free_Set( set1);

	if( no_tests_passed == no_tests )
		printf("Passed\n");
	else printf("Failed\n");

	printf("--------------------------------------\n\n");

	return no_tests_passed == no_tests ? 1 : 0;
}
void test_PQ_get_number_of_elements( int number_of_current_test, PriorityQueue pq,
		int predicted_number_of_elements, int *no_tests_passed ) {

	print_test_message( number_of_current_test, "PQ_get (number of elements in PQ)",
			pq->no_elements == predicted_number_of_elements, no_tests_passed );
}

void test_PQ_get_value_of_taken_element( int number_of_current_test, int actual_value,
		int predicted_value, int *no_tests_passed ) {

	print_test_message( number_of_current_test, "PQ_get (value of taken element)",
			actual_value == predicted_value, no_tests_passed );
}



void test_PQ_put_number_of_elements( int number_of_current_test, PriorityQueue pq,
		int predicted_number_of_elements, int *no_tests_passed ) {

	print_test_message( number_of_current_test, "PQ_put (number of elements in PQ)",
			pq->no_elements == predicted_number_of_elements, no_tests_passed );
}

void test_PQ_put_value_of_element( int number_of_current_test, PriorityQueue pq, int index,
		int predicted_value, int *no_tests_passed ) {

	print_test_message( number_of_current_test, "PQ_put (value of element with given index)",
			pq->vertexes[index] == predicted_value, no_tests_passed );
}

void test_PQ_put_value_of_distance( int number_of_current_test, PriorityQueue pq, int index,
		double predicted_value, int *no_tests_passed ) {

	print_test_message( number_of_current_test, "PQ_put (value of element's distance)",
			pq->distances[index] == predicted_value, no_tests_passed );
}

void test_PQ_put( int number_of_current_test, PriorityQueue pq, int predicted_number_of_elements,
	       int index, int predicted_vertex_value, double predicted_distance_value, int * no_tests_passed ) {

	test_PQ_put_number_of_elements( number_of_current_test++, pq, predicted_number_of_elements, no_tests_passed );

	test_PQ_put_value_of_element( number_of_current_test++, pq, index, predicted_vertex_value, no_tests_passed );
	
	test_PQ_put_value_of_distance( number_of_current_test++, pq, index, predicted_distance_value, no_tests_passed );
}



void test_Set_is_element_in_true( int number_of_current_test, Set set, int element, int * no_tests_passed ) {
	print_test_message( number_of_current_test, "Set_is_element_in",
			Set_is_element_in( set, element ), no_tests_passed );
}

void test_Set_is_element_in_false( int number_of_current_test, Set set, int element, int * no_tests_passed ) {
	print_test_message( number_of_current_test, "Set_is_element_in",
			!Set_is_element_in( set, element ), no_tests_passed );
}


void test_Set_is_empty_true( int number_of_current_test, Set set, int * no_tests_passed ) {
	print_test_message( number_of_current_test, "Set_is_empty",
			Set_is_empty( set ), no_tests_passed );
}

void test_Set_is_empty_false( int number_of_current_test, Set set, int * no_tests_passed ) {
	print_test_message( number_of_current_test, "Set_is_empty",
			!Set_is_empty( set ), no_tests_passed );
}


void test_Set_add_number_of_elements( int number_of_current_test, Set set, int number_of_elements,
		int * no_tests_passed ) {
	print_test_message( number_of_current_test, "Set_add (number of elements)",
			set->no_elements == number_of_elements, no_tests_passed );
}

void test_Set_add_value_of_element( int number_of_current_test, Set set, int index,
		int element, int * no_tests_passed ) {

	print_test_message( number_of_current_test, "Set_add (value in array elements)",
			set->elements[index] == element, no_tests_passed );
}

void test_Set_add( int number_of_current_test, Set set, int number_of_elements,
		int index, int element, int * no_tests_passed ) {
	
	test_Set_add_number_of_elements( number_of_current_test, set, number_of_elements, no_tests_passed );
	test_Set_add_value_of_element( number_of_current_test, set, index, element, no_tests_passed );
}


void test_Set_remove_number_of_elements( int number_of_current_test, Set set, int number_of_elements,
		int * no_tests_passed ) {
	print_test_message( number_of_current_test, "Set_remove (number of elements)",
			set->no_elements == number_of_elements, no_tests_passed );
}

void test_Set_pop_number_of_elements( int number_of_current_test, Set set, int number_of_elements,
		int * no_tests_passed ) {
	print_test_message( number_of_current_test, "Set_pop (number of elements)",
			set->no_elements == number_of_elements, no_tests_passed );
}
