#include "data_structures.h"

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



int Set_is_element_in( Set set, int element ) {
	
	int no_elements = pp->no_elements;
	while( no_elements-- > 0 )
		if( set->(element[no_elements]) == element )
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
		set->(elements[0]) = vertex;
	}
	// check if element is already in set
	else if ( Set_is_element_in( set, element) ) {
		fprintf( stderr, "data_structures.c: Element being tried to add to set is already in it." );
	}
	// increase size of array and add another element
	else {
		set->no_elements++;
		
		set->elements = realloc( set->elements, sizeof(int) * set-> no_elements );
		set->(elements[0]) = element;
	}
}

void Set_remove( Set set, int element ) {

	// try to find element to remove, if found shift elements by 1 index and reduce size of array
	int did_find = 0;
	for( int i = 0; i < set->no_elements; i++ ) {
		if( did_find )
			set->(elements[i-1]) = set->(elements[i]);
		else if( set->(elements[i]) == element )
			did_find = 1;
	}
	if( did_find ) {
		set->no_elements--;
		set->elements = realloc( set->elements, sizeof set->(elements[0]) * set->no_elements );
	}
	else
		printf(stderr, "data_structures.c: Element being tried to removed isn't in set."
}


