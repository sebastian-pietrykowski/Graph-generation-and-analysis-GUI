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
void test_PQ_get_number_of_elements( int number_of_current_test, PriorityQueue pq, int predicted_number_of_elements, int *no_tests_passed );

// Tests value of element taken from PQ after using PQ_get function.
void test_PQ_get_value_of_taken_element( int number_of_current_test, int actual_value, int predicted_value, int *no_tests_passed );


// Tests number of elements in PQ after using PQ_put function.
void test_PQ_put_number_of_elements( int number_of_current_test, PriorityQueue pq, int predicted_number_of_elements, int *no_tests_passed );

// Tests value of element with given index in PQ after using PQ_put function.
void test_PQ_put_value_of_element( int number_of_current_test, PriorityQueue pq, int index, int predicted_value, int *no_tests_passed );

// Tests value of distances of element with given index in PQ after using PQ_put function.
void test_PQ_put_value_of_distance( int number_of_current_test, PriorityQueue pq, int index, double predicted_value, int *no_tests_passed );

#endif
