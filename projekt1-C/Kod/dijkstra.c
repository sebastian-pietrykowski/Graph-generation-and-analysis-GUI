#include "dijkstra.h"
#include <float.h>
#include <stdlib.h>

int PP_get( PriorityQueue pp ) {
	
	if( pp->no_elements < 1 )
		return -1;
	else if( pp->no_elements == 1 )
		return pp->(vertexes[0]);
	else {   
	// find vertex from many
		int index = -1;
		double min_distance = DBL_MAX;
		
		// find vertex
		for( int i = 0; i < pp->no_elements; i++ )
			if( pp->(vertexes[i]) < min_distance ) {
				index = i;
				min_distance = pp->(distances[i]);
			}
		// if chosen element has distance negative or equal 0, then return -1 and print error message
		if( min_distance <= 0 ) {
			fprintf(stderr, "Element taken off from PriorityQueue has distance negative or equal 0: %lf\n", min_distance );
			return -1;
		}
		
		// change size of arrays in struct
		int element = pp->(vertexes[index]);
		pp->no_elements--;
		pp->vertexes = realloc( pp->vertexes, sizeof(int) * pp->no_elements );
		pp->distances = realloc( pp->distances, sizeof(double) * pp->no_elements );

		return element;
	}
}

void PP_put( PriorityQueue pp, int vertex, double distance ) {

	// initialize PriorityQueue pp if necessary and add elements
	if( pp == NULL ) {
		pp = malloc( sizeof( PriorityQueue ) );
		pp->no_elements = 1;
		
		pp->vertexes = malloc( sizeof(int) * 1 );
		pp->(vertexes[0]) = vertex;
		
		pp->distances = malloc( sizeof(double) * 1 );
		pp->(distances[0]) = distance;
	}
	// increase size of arrays and add another elements
	else {
		pp->no_elements++;
		
		pp->vertexes = realloc( pp->vertexes, sizeof(int) * pp-> no_elements );
		pp->(vertexes[0]) = vertex;

		pp->distances = realloc( pp->distances, sizeof(double) * pp->no_elements );
		pp->(distances[0]) = distance;
	}
}

void Set_add( Set set, int vertex ) {

	// initialize Set set if necessary and add element
	if( set == NULL ) {
		set = malloc( sizeof( Set ) );
		set->no_elements = 1;
		
		set->vertexes = malloc( sizeof(int) * 1 );
		set->(vertexes[0]) = vertex;
	}
	// increase size of array and add another element
	else {
		set->no_elements++;
		
		set->vertexes = realloc( set->vertexes, sizeof(int) * set-> no_elements );
		set->(vertexes[0]) = vertex;

		set->distances = realloc( set->distances, sizeof(double) * set->no_elements );
		set->(distances[0]) = distance;
	}
}

int Set_is_element_in( Set set, int vertex ) {
	
	int no_elements = pp->no_elements;
	while( no_elements-- > 0 )
		if( set->(vertexes[no_elements]) == vertex )
			return 1;
	return 0;

}

int initiate_values_dijkstra( graph_t graph, int start_vertex_number, int ** predecessors, double ** distances ) {
	
	// initiate arrays
	*predecessors = malloc( sizeof(int) * graph->no_vertexes );
	*distances = malloc( sizeof(double) * graph->no_vertexes );
	
	// set all values in arrays to default
	for( int i = 0; i < graph->no_vertexes; i++ ) {
		*(predecessors([i]) = -1;
		*(distances[i]) = DBL_MAX;
	}
	// start condition
	*(distances[ start_vertex_number ]) = 0;
}

void relax( graph_t graph, PriorityQueue pq, int start_vertex_number, int vertex1, int vertex2, int ** predecessors, double ** distances ) {
	
	// if distances[vertex1]=INIFINITY (it didn't find any path so far) or new path is shorter than current
	if( *(distances[vertex1]) > *(distances[vertex2]) + graph->adj_mat[vertex2 * graph->no_vertexes + vertex1]) {
		*(distances[vertex1]) = *(distances[vertex2]) + graph->adj_mat[vertex2 * graph->no_vertexes + vertex1];
		*(predecessors[index1]) = index2;

		// change predecessor in array inside PriorityQueue
		for( int i = 0; i < pq->no_elements; i++)
			if( pq->(vertex[i]) == index1 ) {
				pq->(predecessors[i]) = index2;
				break;
			}
	}
}

int * dijkstra( graph_t graph, int start_vertex_number ) {
	
	// create arrays of predecessors and distances, initiate them
	int * predecessors;
	double * distances;
	initiate_values_dijkstra( graph, start_vertex_number, &predecessors, &distances );

	Set checked_vertexes;
	PriorityQueue queue;

	/* Fulfill queue with vertexes.
	 *
	 * There are two arrays predecessors[] - the one in this function and the one in PriorityQueue - that's because
	 * elements of array in PriorityQueue are being removed, while elements in array in this function always exist. */
	PP_put( queue, start_vertex_number, 0 );
	for( int i = 0; i < graph->no_vertexes; i++ )
		if( i != start_vertex_number )
			PP_put( queue, i, DBL_MAX );
	
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
	return pedecessors;
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
	
	// do untill it reaches the last element of path, received path is reversed
	while( no_elements-- > 0 ) {

		// this is the last element
		if( no_elements == 0 )
			printf("%d\n", path[ no_elements ] );
		// not last element, print weights
		else if( does_print_weights )
			printf("%d (%lf) -> ", path[ no_elements ] );
		// not last element, don't print weights
		else
			printf("%d -> ", path[ no_elements ] );
	}
}

void free_PP( PriorityQueue pp ) {
	free( pp->vertexes );
	free( pp->distances );
	free( pp );
}

void free_Set( Set set ) {
	free( set->vertexes );
	free( set );
}
