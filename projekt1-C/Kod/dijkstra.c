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

void relax( graph_t graph, int start_vertex_number, int vertex1, int vertex2, int ** predecessors, double ** distances ) {
	
	// if distances[vertex1]=INIFINITY (it didn't find any path so far) or new path is shorter than current
	if( *(distances[vertex1]) > *(distances[vertex2]) + graph->adj_mat[vertex2 * graph->no_vertexes + vertex1] ) {
		*(distances[vertex1]) = *(distances[vertex2]) + graph->adj_mat[vertex2 * graph->no_vertexes + vertex1];
		*(predecessors[index1]) = index2;
	}
}

