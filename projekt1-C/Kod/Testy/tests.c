#include <stdlib.h>
#include <time.h>

#include "tests_data_structures.h"
#include "tests_generator.h"


int main() {
	
	srand(time(NULL));

	// Testing data_structures.c
	if( test_PQ() == 0 )
		exit(1);
	if( test_Set() == 0 )
		exit(1);

	test_generate_random_graph();
	
	return 0;
}
