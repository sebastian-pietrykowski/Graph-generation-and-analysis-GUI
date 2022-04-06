#ifndef _TESTS_GENERATOR_H_
#define _TESTS_GENERATOR_H_

#include "../generator.h"


/* Method used for testing function generate_complete_graph().
 * Returns 1 if tests passed, 0 if not. */
int test_generate_complete_graph();


/* Method used for testing function generate_connected_graph().
 * Returns 1 if tests passed, 0 if not. */
int test_generate_connected_graph();

/* Method used for testing function generate_random_graph().
 * Returns 1 if tests passed, 0 if not. */
int test_generate_random_graph();



/* Tests if graph generated using generate_complete_graph()
 * function is complete. */
int test_generate_complete_graph_is_complete( int number_of_current_test,
		graph_t graph, int *no_tests_passed );

/* Tests if graph generated using function generate_complete_graph()
 * have at least 1 vertex. */
int test_generate_complete_graph_number_of_vertices( int number_of_current_test,
		graph_t graph, int *no_tests_passed );

/* Tests if all vertices in graph generated using generate_complete_graph()
 * function are situated properly. */
int test_generate_complete_graph_edges( int number_of_current_test,
		graph_t graph, int *no_tests_passed );

/* Tests if all vertices in graph generated using generate_complete_graph()
 * function have weights of edges in proper range (from_weight,to_weight). */
int test_generate_complete_graph_edges_weights( int number_of_current_test, graph_t graph,
		double from_weight, double to_weight, int *no_tests_passed );



/* Tests if graph generated using function generate_random_graph()
 * have at least 1 vertex. */
int test_generate_random_graph_number_of_vertices( int number_of_current_test,
		graph_t graph, int *no_tests_passed );

/* Tests if all vertices in graph generated using generate_random_graph()
 * function are situated properly. */
int test_generate_random_graph_edges( int number_of_current_test, graph_t graph,
		int *no_tests_passed );

/* Tests if all vertices in graph generated using generate_random_graph()
 * function have weights of edges in proper range (from_weight,to_weight). */
int test_generate_random_graph_edges_weights( int number_of_current_test, graph_t graph,
		double from_weight, double to_weight, int *no_tests_passed );


/* Function used to check if all vertices in graph are properly situated.
 * Returns 1 if all is correct, 0 if there is an error related to edge. */
int check_edges( graph_t graph );

/* Function used for testing if all edges in graph have edges
 * with weights in proper range (from_weight, to_weight). */
int check_edges_weights( graph_t graph, double from_weight, double to_weight );


#endif
