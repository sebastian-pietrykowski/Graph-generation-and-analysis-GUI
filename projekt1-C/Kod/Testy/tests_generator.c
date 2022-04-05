#include "tests_generator.h"
#include "tests_auxiliary_functions.h"


int test_generate_complete_graph() {
}

int test_generate_connected_graph() {
}

int test_generate_random_graph() {

	graph_t graph1 = generate_random_graph(5, 5, 0, 1);

	print_graph( graph1 );	


	free_graph( graph1 );
	return 0;
}
