#include <float.h>
#include <stdlib.h>
#include <stdio.h>

#include "data_structures.h"

int PQ_get( PriorityQueue pq ) {
	
	if( pq->no_elements < 1 )
		return -1;
	else if( pq->no_elements == 1 )
		return pq->vertexes[0];
	else {   
	// find vertex from many
		int index = -1;
		double min_distance = DBL_MAX;
		
		// find vertex
		for( int i = 0; i < pq->no_elements; i++ )
			if( pq->vertexes[i] < min_distance ) {
				index = i;
				min_distance = pq->distances[i];
			}
		// if chosen element has distance negative or equal 0, then return -1 and print error message
		if( min_distance <= 0 ) {
			fprintf(stderr, "Element taken off from PriorityQueue has distance negative or equal 0: %lf\n", min_distance );
			return -1;
		}
		
		// change size of arrays in struct
		int element = pq->vertexes[index];
		pq->no_elements--;
		pq->vertexes = realloc( pq->vertexes, sizeof(int) * pq->no_elements );
		pq->distances = realloc( pq->distances, sizeof(double) * pq->no_elements );

		return element;
	}
}


void PQ_put( PriorityQueue pq, int vertex, double distance ) {

	// initialize PriorityQueue pq if necessary and add elements
	if( pq == NULL ) {
		pq = malloc( sizeof( PriorityQueue ) );
		pq->no_elements = 1;
		
		pq->vertexes = malloc( sizeof(int) * 1 );
		pq->vertexes[0] = vertex;
		
		pq->distances = malloc( sizeof(double) * 1 );
		pq->distances[0] = distance;
	}
	// increase size of arrays and add another elements
	else {
		pq->no_elements++;
		
		pq->vertexes = realloc( pq->vertexes, sizeof(int) * pq-> no_elements );
		pq->vertexes[0] = vertex;

		pq->distances = realloc( pq->distances, sizeof(double) * pq->no_elements );
		pq->distances[0] = distance;
	}
}

void free_PQ( PriorityQueue pq ) {
	feee( pq->vertexes );
	free( pq->distances );
	free( pq );
}



int Set_is_element_in( Set set, int element ) {
	
	int n = set->no_elements;
	while( n-- > 0 )
		if( set->elements[n] == element )
			return 1;
	return 0;
}

int Set_is_empty( Set set ) {
	return set->no_elements == 0 ? 1 : 0;
}

void Set_add( Set set, int element ) {

	// initialize Set set if necessary and add element
	if( set == NULL ) {
		set = malloc( sizeof( Set ) );
		set->no_elements = 1;
		
		set->elements = malloc( sizeof(int) * 1 );
		set->elements[0] = element;
	}
	// check if element is already in set
	else if ( Set_is_element_in( set, element) ) {
		fprintf( stderr, "data_structures.c: Element being tried to add to set is already in it." );
	}
	// increase size of array and add another element
	else {
		set->no_elements++;
		
		set->elements = realloc( set->elements, sizeof(int) * set-> no_elements );
		set->elements[0] = element;
	}
}

void Set_remove( Set set, int element ) {

	// try to find element to remove, if found shift elements by 1 index and reduce size of array
	int did_find = 0;
	for( int i = 0; i < set->no_elements; i++ ) {
		if( did_find )
			set->elements[i-1] = set->elements[i];
		else if( set->elements[i] == element )
			did_find = 1;
	}
	if( did_find ) {
		set->no_elements--;
		set->elements = realloc( set->elements, sizeof set->elements[0] * set->no_elements );
	}
	else
		fprintf(stderr, "data_structures.c: Element being tried to removed isn't in set.");
}

void free_Set( Set set ) {
	free( set->elements );
	free( set );
}

