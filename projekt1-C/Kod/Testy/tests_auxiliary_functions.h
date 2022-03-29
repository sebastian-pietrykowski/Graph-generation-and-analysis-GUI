#ifndef _TESTS_AUXILIARY_FUNCTIONS_
#define _TESTS_AUXILIARY_FUNCTIONS_

/* Function used to test program.
 * Prints message "Tests no_current_test: passed/failed".
 * In place of "int condidtion" should be written actual test
 * - expression returning 0 - false, other integer - true.. */
void print_test_message( int no_current_test, int condition, int * no_tests_passed );


#endif
