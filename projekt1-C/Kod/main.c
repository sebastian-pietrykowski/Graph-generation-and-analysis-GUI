#include <getopt.h>
#include <stdio.h>
#include <stdlib.h>

#include "bfs.h"
#include "data_structures.h"
#include "dijkstra.h"
#include "graph.h"
#include "generator.h"

char *usage =
"Usage:\n"
"/projekt1 [ -i input-file | [ [-o output-file] [ [-c columns] [-r rows] [-f from-weight] [-t to-weight] ] ] ] [-m1|2|3] [-s start-vertex-number -e end-vertex-number] [-n 0|1] [-p 0|1]";

int main(int argc, char **argv) {
	char *program_name = argv[0];
	int opt;
	
	char *inp = NULL;
	
	char *ouf = NULL;
	int columns = 5;
	int rows = 5;
	double from_weight = 0;
	double to_weight = 1;
	
	int mode = 3; /* By default, we generate a random graph */
	int start_vertex_number = 0;
	int end_vertex_number = columns * rows - 1;
	int check_connectivity = 0;
	int print_weights = 1;
	
	graph_t graph;
	
	if (argc == 1 ) {
		fprintf(stderr, usage, program_name);
		exit(EXIT_FAILURE);
	}
	
    while ((opt = getopt(argc, argv, "i:n:")) != -1) {
		switch (opt) {
			case 'i':
			inp = optarg;
			break;
			/*     case 'o':
				ouf = optarg;
				break;
				case 'c':
				columns = atoi(optarg);
				break;
				case 'r':
				rows = atoi(optarg);
				break;
				case 'f':
				from_weight = atof(optarg);
				break;
				case 't':
				to_weight = atof(optarg);
				break;
				case 'm':
				mode = atoi(optarg);
				break;
				case 's':
				start_vertex_number = atoi(optarg);
				break;
				case 'e':
				end_vertex_number = atoi(optarg);
			break; */
			case 'n':
			check_connectivity = atoi (optarg);
			break;
			/*    case 'p':
				print_weights = atoi(optarg);
			break; */
			default:
			fprintf(stderr, usage, program_name);
			exit(EXIT_FAILURE);
		}
	}
	
	
	if (inp != NULL) {
		FILE *in = fopen(inp, "r");
		if (in == NULL) {
			fprintf(stderr, "%s: can not read file: %s\n\n", argv[0], inp);
			exit(EXIT_FAILURE);
		}
		graph = read_graph( in );
		if (check_connectivity == 1) { /* Check connectivity */
			if (bfs( graph, start_vertex_number)) {
				fprintf(stdout, "Graph is consistent\n");
			} else
			fprintf(stdout, "Graph is inconsistent\n");
		}
		
		// int * dijkstra = dijkstra( graph, start_vertex_number );
		
		
		fclose(in);
		free_graph(graph);
		free_bfs();  
		exit(EXIT_SUCCESS);
	}
	
	else if (ouf != NULL) {
		FILE *out = fopen(ouf, "w");
		if (out == NULL) {
			fprintf(stderr, "%s: can not write to file: %s\n\n", argv[0], ouf);
			exit(EXIT_FAILURE);
		}
		if (mode == 1) { 
			//    graph = generate_complete_graph(columns,rows,from_weight, to_weight);
			if ((does_have_all_edges( graph )) == 0) {
				fprintf(stderr, "Error, generated graph does not have all the edges\n");
				fclose(out);
				free_graph(graph);
				exit(EXIT_FAILURE);
			}
		}
		if (mode == 2) { 
			//  graph = generate_connected_graph(columns,rows, from_weight, to_weight);
			if ((bfs( graph, start_vertex_number)) == 0) {
				fprintf(stdout, "Error, graph is inconsistent\n");
				fclose(out);
				free_graph(graph);
				free_bfs();
				exit(EXIT_FAILURE);
			}
			} else if (mode == 3) {
			//graph = generate_random_graph(columns,rows, from_weight, to_weight);
		}
		
		/* Tutaj juz dijkstra */
		fclose(out);
		free_graph(graph);
		free_bfs();
		exit(EXIT_SUCCESS);
	}
	else {
		fprintf( stderr, "Tou didn't attach input or output file" );
		fprintf( stderr, "%s", usage );
		exit(EXIT_FAILURE);
	}
}


