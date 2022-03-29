#ifndef _DATA_STRUCTURES_H_
#define _DATA_STRUCTURES_H_

typedef struct {

	int * vertexes;   // array of vertexes added to queue
	double * distances;   /* contains distances to given vertexes, every element of array
				 corresponds with element with the same index from array vertexes[] */
	int no_elements;   // number of vertexes stored in array vertexes[]

} * PriorityQueue;

// Contains array storing vertexes which increases its size when necessary.
typedef struct {

	int * elements;   // array of vertexes
	int no_elements;   // number of vertexes stored in array vertexes[]

} * Set;

PriorityQueue make_PQ();

// Removes from PriorityQueue pp vertex with the shortest distance, returns it.
int PQ_get( PriorityQueue pq );

/* Adds to PriorityQueue pp vertex with given number (vertex) and distance.
 * If PriorityQueue pp is NULL, then initialize it. */
void PQ_put( PriorityQueue pq, int vertex, double distance );

// Removes priority queue from memory.
void free_PQ( PriorityQueue pq );

// Returns 1 if element is present in set, 0 if not.
int Set_is_element_in( Set set, int element );

// Returns 1 if set is empty, 0 if set is not empty.
int Set_is_empty( Set set );

/* Adds to Set set element with given number.
 * Added elements must be unique integers.
 * If Set set is NULL, then initialize it. */
void Set_add( Set set, int element );

// Removes from set element with given number.
void Set_remove( Set set, int element );

// Removes set from memory.
void free_set( Set set );

#endif
