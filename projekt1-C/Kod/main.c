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
"/projekt1 [ -i input-file | [ [-o output-file] [ [-c columns] [-r rows] [-f from-weight]"
"[-t to-weight] ] ] ] [-m1|2|3] [-s start-vertex-number -e end-vertex-number] [-n 0|1] [-p 0|1]\n"
"\n"
"If \"input-file\" is given then\n"
"\treads graph from \"input-file\"\n"
"\taccepts only graph according to chosen mode in \"-m\", default: 3\n"
"If \"output-file\" is given then\n"
"\tgenerates graph and saves it to \"output-file\"\n"
"\tgenerates graph:\n"
"\t\taccording to chosen mode in \"-m\", default: 3\n"
"\t\tabout numer of columns \"columns\", default: 5\n"
"\t\tabout number of rows \"rows\", default: 5\n"
"\t\t\"columns\" and \"rows\" must be > 1\n"
"\t\tabout edges with weights in range(\"from_weight\",\"to_weight\")\n"
"\t\t\"from_weight\" must be > 0 and \"to_weight\" must be > 0\n"
"\n"
"Reading graph - modes:\n"
"\t1. accepts only graph with all possible edges between vertices neighboring vertically and horizontally\n"
"\t2. accepts only connected graph\n"
"\t3. accepts any graph\n"
"Generating graph - modes:\n"
"\t1. generates graph with all possible edges between vertices neighboring vertically and horizontally\n"
"\t2. generates connected graph\n"
"\t3. generates random graph\n"
"\n"
"If \"-n\" is given then\n"
"\tif \"-n\" is 1 then check connectivity of graph and print information\n"
"\tif \"-n\" is 0 then don't check connectivity\n"
"\tdefault: 0\n"
"\n"
"If \"start_vertex_number\" and \"end_vertex_number\" are given then\n"
"\tfind and print the shortest path from vertex with number \"start_vertex_number\" to vertex with number \"end_vertex_number\"\n"
"\tif \"-p\" is given then\n"
"\t\tif \"-p\" is 1 then print weights of edges in the shortest path\n"
"\t\tif \"-p\" is 0 then dont' print weights of edges in the shortest path\n"
"\t\tdefault: 1\n";

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


