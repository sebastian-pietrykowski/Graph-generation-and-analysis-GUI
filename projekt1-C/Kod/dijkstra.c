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
		pp->vertexes = realloc( pp->vertexes, sizeof(int) * pp->no_vertexes );
		pp->distances = realloc( pp->distances, sizeof(double) * pp->no_vertexes );

		return element;
	}
}

void PP_put( PriorityQueue pp, int vertex, double distance ) {

	// initialize PriorityQueue pp if necessary and add elements
	if( pp == NULL ) {
		pp = malloc( sizeof( PriorityQueue ) );
		pp->no_vertexes = 1;
		
		pp->vertexes = malloc( sizeof(int) * 1 );
		pp->(vertexes[0]) = vertex;
		
		pp->distances = malloc( sizeof(double) * 1 );
		pp->(distances[0]) = distance;
	}
	// increase size of arrays and add another elements
	else {
		pp->no_elements++;
		
		pp->vertexes = realloc( pp->vertexes, sizeof(int) * pp-> no_vertexes );
		pp->(vertexes[0]) = vertex;

		pp->distances = realloc( pp->distances, sizeof(double) * pp->no_vertexes );
		pp->(distances[0]) = distance;
	}
}
