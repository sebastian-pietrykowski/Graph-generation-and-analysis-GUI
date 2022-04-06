#include "dijkstra.h"
#include <float.h>
#include <stdlib.h>


int initiate_values_dijkstra( graph_t graph, int start_vertex_number, int ** predecessors, double ** distances ) {
	
	// initiate arrays
	*predecessors = malloc( sizeof(int) * graph->no_vertexes );
	*distances = malloc( sizeof(double) * graph->no_vertexes );
	
	// set all values in arrays to default
	for( int i = 0; i < graph->no_vertexes; i++ ) {
		(*predecessors)[i] = -1;
		(*distances)[i] = DBL_MAX;
	}
	// start condition
	(*distances)[ start_vertex_number ] = 0;
}

void relax( graph_t graph, PriorityQueue pq, int start_vertex_number, int vertex_from, int vertex_to,
	int ** predecessors, double ** distances ) {
	
	// if distances[vertex_to]=INIFINITY (it didn't find any path so far) or new path is shorter than current
	if( (*distances)[vertex_to] > (*distances)[vertex_from] + graph->adj_mat[vertex_from][vertex_to]) {
		(*distances)[vertex_to] = (*distances)[vertex_from] + graph->adj_mat[vertex_from][vertex_to];
		(*predecessors)[vertex_to] = vertex_from;
	}
}

int * dijkstra( graph_t graph, int start_vertex_number ) {
	
	// create arrays of predecessors and distances, initiate them
	int * predecessors;
	double * distances;
	initiate_values_dijkstra( graph, start_vertex_number, &predecessors, &distances );

	Set checked_vertexes = make_Set();
	PriorityQueue queue = make_PQ();

	// Fulfill queue with vertexes.
	PQ_put( queue, start_vertex_number, 0 );
	for( int i = 0; i < graph->no_vertexes; i++ )
		if( i != start_vertex_number )
			PQ_put( queue, i, DBL_MAX );
	
	while( queue->no_elements != 0 ) {
		int removed_element = PP_get( queue );   // index of vertex removed from queue
		int * neighbors = neighbors( graph, removed_element ); // array of indices of removed_element's neighbors
		Set_add( removed_element ); // add element removed from queue to array of removed elements
		
		// check if removed_element's neighbors were removed from queue, if not try to determine path to them
		for( int i = 0; i < sizeof(neighbors)/sizeof(neighbors[0]); i++ ) {
			int potential_neighbor = neighbors[i];
			if( !Set_is_element_in( Set set, potential_neighbor )
				relax( graph, start_vertex_number, potential_neighbor, removed_element, predecessors, distances );	

		}
		free( neighbors );
	}
	return predecessors;
}

int * determine_path( int * no_path_elements, int * predecessors, int start_vertex_number, int end_vertex_number ) {
	
	// set path to one element being end_vertex_number
	*no_path_elements = 1;
	int * path = malloc( 1 ); // reverted path
	path[0] = end_vertex_number;

	// determine path
	int element = end_vertex_number;
	while( (element = predecessors[element]) =! start_vertex_number || element == -1 ) {
		(*no_path_elements)++;
		path = realloc( path, sizeof(int) * (*no_path_elements) );
		path[(*no_path_elements)-1] = element;
	}

	// it didn't find path
	if( element == -1 ) {
		*no_path_elements = 0;
		return null;
	}
	// the last element to add - start_vertex_number
	else if( element == start_vertex_number ) {
		(*no_path_elements)++;
		path[(*no_path_elements)-1] = start_vertex_number;
		return path;
	}

	return null;
}

void print_path( int * path, int no_elements, int does_print_weights ) {
	
	/* Do untill it reaches the last element of path,
	 * path received from argument is reversed
	 * so it need to revert it. */
	while( --no_elements > 0 ) {

		// this is the last element
		if( no_elements == 0 )
			printf("%d\n", path[ no_elements ] );
		// not the last element, print weights
		else if( does_print_weights )
			printf("%d (%lf) -> ", path[ no_elements ] );
		// not the last element, don't print weights
		else
			printf("%d -> ", path[ no_elements ] );
	}
}
