#include <stdlib.h>

#include "../data_structures.h"
#include "tests_data_structures.h"


int main() {
	
	if( test_PQ() == 0 )
		exit(1);
	test_Set();

	return 0;
}
