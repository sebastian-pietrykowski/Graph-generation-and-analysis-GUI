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

	int * vertexes;   // array of vertexes
	int no_elements;   // number of vertexes stored in array vertexes[]

} * Set;

// Removes from PriorityQueue pp vertex with the shortest distance, returns it.
int PQ_get( PriorityQueue pq );

/* Adds to PriorityQueue pp vertex with given number (vertex) and distance.
 * If PriorityQueue pp is NULL, then initialize it. */
void PQ_put( PriorityQueue pq, int vertex, double distance );


/* Adds to Set set vertex with given number (vertex).
 * If Set set is NULL, then initialize it. */
void Set_add( Set set, int vertex );

// Returns 1 if element is present in set, 0 if not.
int Set_is_element_in( Set set, int vertex );

#endif
