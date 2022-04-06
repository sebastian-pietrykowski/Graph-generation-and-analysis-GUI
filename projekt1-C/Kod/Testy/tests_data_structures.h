#ifndef _TESTS_DATA_STRUCTURES_H_
#define _TESTS_DATA_STRUCTURES_H_

#include "../data_structures.h"

/* Tests struct PriorityQueue and functions using it in data_structures.c file.
 * Returns 1 if all tests are passed, 0 if not. */
int test_PQ();

/* Tests struct Set and functions using it in data_structures.c file.
 * Returns 1 if all tests are passed, 0 if not. */
int test_Set();


// Tests number of elements in PQ after using PQ_get function.
void test_PQ_get_number_of_elements( int number_of_current_test, PriorityQueue pq,
		int predicted_number_of_elements, int *no_tests_passed );

// Tests value of element taken from PQ after using PQ_get function.
void test_PQ_get_value_of_taken_element( int number_of_current_test, int actual_value,
		int predicted_value, int *no_tests_passed );


/* Performs 3 tests: checks number of elements, value of element with given index
 * and its distance after using PQ_put function. */
void test_PQ_put( int number_of_current_test, PriorityQueue pq,
		int predicted_number_of_elements, int index, int predicted_vertex_value,
		double predicted_distance_value, int * no_tests_passed );

// Tests number of elements in PQ after using PQ_put function.
void test_PQ_put_number_of_elements( int number_of_current_test, PriorityQueue pq,
		int predicted_number_of_elements, int *no_tests_passed );

// Tests value of element with given index in PQ after using PQ_put function.
void test_PQ_put_value_of_element( int number_of_current_test, PriorityQueue pq,
		int index, int predicted_value, int *no_tests_passed );

// Tests value of distances of element with given index in PQ after using PQ_put function.
void test_PQ_put_value_of_distance( int number_of_current_test, PriorityQueue pq,
		int index, double predicted_value, int *no_tests_passed );


// Tests if element is present in Set using Set_is_element_in function.
void test_Set_is_element_in_true( int number_of_current_test, Set set, int element, int * no_tests_passed );

// Tests if element is absent in Set using Set_is_elemnent_in function.
void test_Set_is_element_in_false( int number_of_current_test, Set set, int element, int * no_tests_passed );

// Tests if set is empty using Set_is_empty function.
void test_Set_is_empty_true( int number_of_current_test, Set set, int * no_tests_passed );

// Tests if set is not empty using Set_is_empty function.
void test_Set_is_empty_false( int number_of_current_test, Set set, int * no_tests_passed );

// Tests number of elements in Set after using Set_is_empty_function.
void test_Set_add_number_of_elements( int number_of_current_test, Set set, int number_of_elements,
		int *no_tests_passed );

// Tests if in array set->elements element with given index is equal to element.
void test_Set_add_value_of_element( int number_of_current_test, Set set, int index,
		int element, int * no_tests_passed );

/* Performs 2 tests: tests number of elements in set and checks if in array
 * set->elements element with given index is equal to element.
 * It is used to test after using Set_add function. */
void test_Set_add( int number_of_current_test, Set set, int number_of_elements,
		int index, int element, int * no_tests_passed );

// Tests number of elements in Set after using function Set_remove.
void test_Set_remove_number_of_elements( int number_of_current_test, Set set, int number_of_elements,
		int * no_tests_passed );

// Tests number of elements in Set after using Set_pop function.
void test_Set_pop_number_of_elements( int number_of_current_test, Set set, int number_of_elements,
		int * no_tests_passed );

#endif
