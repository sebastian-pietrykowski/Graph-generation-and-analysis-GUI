#include <stdlib.h>

#include "../data_structures.h"
#include "tests_data_structures.h"
#include "tests_dijkstra.h"

int main() {
	
	// Testing data_structures.c
	if( !test_PQ() )
		exit(1);
	if( !test_Set() )
		exit(1);

	if( !test_Dijkstra() )
		exit(1);
	
	return 0;
}
