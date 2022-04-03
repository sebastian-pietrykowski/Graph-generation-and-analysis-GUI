#include <float.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#include "data_structures.h"

PriorityQueue make_PQ() {

	PriorityQueue pq = malloc( sizeof *pq );
	
	pq->no_elements = 0;	
	pq->vertexes = malloc( 0 );
	pq->distances = malloc( 0 );

	return pq;
}

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
			if( pq->distances[i] < min_distance ) {
				index = i;
				min_distance = pq->distances[i];
			}
		// if chosen element has distance negative or equal 0, then return -1 and print error message
		if( min_distance <= 0 ) {
			fprintf(stderr, "Element taken off from PriorityQueue has distance negative or equal 0: %lf\n", min_distance );
			return -1;
		}
		
		int element = pq->vertexes[index];

		// shift elements by 1 index
		for( int i = index + 1; i < pq->no_elements; i++ ) {
			pq->vertexes[i-1] = pq->vertexes[i];
			pq->distances[i-1] = pq->distances[i];
		}
		
		// change size of arrays in struct
		pq->no_elements--;
		pq->vertexes = realloc( pq->vertexes, sizeof(int) * pq->no_elements );
		pq->distances = realloc( pq->distances, sizeof(double) * pq->no_elements );

		return element;
	}
}


void PQ_put( PriorityQueue pq, int vertex, double distance ) {

	// initialize PriorityQueue pq if necessary and add elements
	if( pq == NULL ) {
		fprintf(stderr, "To use PriorityQueue you first need to reassign function make_PQ() to it.\n");
	}
	// increase size of arrays and add another elements
	else {
		int index = pq->no_elements;
		pq->no_elements++;
		
		pq->vertexes = realloc( pq->vertexes, sizeof(int) * pq->no_elements );
		pq->vertexes[index] = vertex;

		pq->distances = realloc( pq->distances, sizeof(double) * pq->no_elements );
		pq->distances[index] = distance;
	}
}

void free_PQ( PriorityQueue pq ) {
	free( pq->vertexes );
	free( pq->distances );
	free( pq );
}




Set make_set() {	
	Set set = malloc( sizeof( Set ) );
	set->no_elements = 0;
		
	set->elements = malloc( 0 );
	
	return set;
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
		fprintf(stderr, "To use Set you first need to reassign function make_Set() to it.\n");
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

	// try to find element to remove, if found then shift elements by 1 index and reduce size of array
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

int Set_pop( Set set ) {

	// There is no element to be taken from set
	if( set->no_elements < 1 ) {
		fprintf( stderr, "Set.c: Set_pop(...) - There is no element to be taken from Set\n" );
		exit(1);
		return 0;
	}
	// There is only one element in set
	else if( set->no_elements == 1 ) {
		int element = set->elements[0];
		set->elements = realloc( set->elements, 0 );
		set->no_elements--;
		return element;
	}
	// There is many elements in set
	else {
		srand( time( NULL ) );
		// random number from range <0,set-<no_elements>
		int taken_element_index = ( rand() % (set->no_elements ) );
		int taken_element_value = set->elements[ taken_element_index ];

		// shift elements by 1 index starting from taken element
		for( int index = taken_element_index + 1; index < set->no_elements; index++ ) {
			set->elements[ index-1 ] = set->elements[ index ];
		}
		set->no_elements--;
		set->elements = realloc( set->elements, sizeof set->elements[0] * set->no_elements );
		return taken_element_value;
	}
	return 0;
}

void free_Set( Set set ) {
	free( set->elements );
	free( set );
}

