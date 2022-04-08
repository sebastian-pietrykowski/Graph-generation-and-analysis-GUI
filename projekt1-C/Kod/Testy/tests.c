#include <stdlib.h>

#include "../data_structures.h"
#include "tests_data_structures.h"


int main() {
	
	// Testing data_structures.c
	if( test_PQ() == 0 )
		exit(1);
	if( test_Set() == 0 )
		exit(1);

	
	return 0;
}
