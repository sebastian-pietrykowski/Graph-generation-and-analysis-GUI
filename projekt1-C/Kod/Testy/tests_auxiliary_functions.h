#ifndef _TESTS_AUXILIARY_FUNCTIONS_
#define _TESTS_AUXILIARY_FUNCTIONS_

/* Function used to test program.
 * Prints message "Tests no_current_test - name: passed/failed".
 *
 * "no_current_test" - number of current test in testing block
 * "name" - name of test describing what is tested in current test
 * "no_tests_passed" - number increasing if test passes, it can be
 * used to check if all tests passed in the testing block
 *
 * In place of "condition" should be written actual test
 * - expression returning 0 - false, other integer - true,
 * for example:
 * - "number == 5" - test passes if number is equal to 5, fails
 *      if number is not equal to 5.
 * - "structure != NULL" - test passes if structure is not equal
 *      to NULL, fails if structure is equal to NULL */
void print_test_message( int number_of_current_test, char * name, int condition, int * no_tests_passed );


#endif
