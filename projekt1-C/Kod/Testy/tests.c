#include <stdlib.h>
#include <time.h>

#include "tests_data_structures.h"
#include "tests_generator.h"


int main() {
	
	srand(time(NULL));

	// Testing data_structures.c
	if( !test_PQ() )
		exit(1);
	if( !test_Set() )
		exit(1);

	if( !test_generate_complete_graph() )
		exit(1);	

	if( !test_generate_random_graph() )
		exit(1);


	test_generate_connected_graph();
	return 0;
}
