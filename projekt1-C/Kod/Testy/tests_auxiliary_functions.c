#include <stdio.h>
#include "tests_auxiliary_functions.h"

void print_test_message( int number_of_current_test, char * name, int condition, int * no_tests_passed ) {
	
	printf("\tTest %d - %s: ", number_of_current_test, name );

	if( condition ) {
		printf("passed\n");
		*no_tests_passed++;
	}
	else printf("failed\n");
}

#endif
