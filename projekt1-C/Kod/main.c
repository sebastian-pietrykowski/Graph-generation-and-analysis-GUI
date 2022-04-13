#include <getopt.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

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
"\t\t \"from_weight\" defaults to 0, \"to_weight\" defaults to 1\n"
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
"If \"start_vertex_number\" or \"end_vertex_number\" are given then\n"
"\tfind and print the shortest path from vertex with number \"start_vertex_number\" to vertex with number \"end_vertex_number\"\n"
"\t\"start_vertex_number\" defaults to 0\n"
"\t\"end_vertex_number\" defaults to the last vertex in graph\n"
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
	int do_find_path = 0;
	int start_vertex_number = 0;
	int end_vertex_number = columns * rows - 1;
	int do_check_connectivity = 0;
	int do_print_weights = 1;
	
	graph_t graph;
	
    while ((opt = getopt(argc, argv, "i:o:c:r:f:t:m:s:e:n:p:")) != -1) {
		switch (opt) {
			case 'i':
				inp = optarg;
				break;
			case 'o':
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
				do_find_path = 1;
				break;
			case 'e':
				end_vertex_number = atoi(optarg);
				do_find_path = 1;
				break;
			case 'n':
				do_check_connectivity = atoi (optarg);
				break;
			case 'p':
				do_print_weights = atoi(optarg);
				break;
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

		if( graph == NULL ) {
			fprintf(stderr,"Error in reading graph\n");
			exit(EXIT_FAILURE);
		}

		if( mode == 1) {
			if( !does_have_all_edges(graph) ) {
				fprintf(stderr,"Loaded graph is not complete. According to mode 1 program stops running\n\n");
				fclose(in);
				free_graph(graph);
				return 0;
			}
			else
				fprintf(stdout,"Loaded graph is complete. According to mode 1 program continues running\n\n");
		}
		else if( mode == 2 ) {
			 if( !bfs( graph, start_vertex_number )  ) {
				fprintf(stderr,"Loaded graph is not connected. According to mode 2 program stops running\n\n");
				fclose(in);
				free_graph(graph);
				return 0;
			}
			else
				fprintf(stdout,"Loaded graph is connected. According to mode 2 program continues running\n\n");
		}
		else {
			// mode 3
			fprintf(stdout,"According to mode 3 program doesn't check loaded graph\n\n");
		}

		if (do_check_connectivity) { 
			if (bfs( graph, start_vertex_number))
				fprintf(stdout, "Graph is connected\n\n");
			else
				fprintf(stdout, "Graph is not connected\n\n");
		}

		if( do_find_path ) { // Find the shortest path
			
			find_path_dijkstra( graph, start_vertex_number, end_vertex_number, do_print_weights );
		}
		
		
		fclose(in);
		free_graph(graph); 
		exit(EXIT_SUCCESS);
	}
	
	else if (ouf != NULL) {
		
		FILE *out = fopen(ouf, "w");
		if (out == NULL) {
			fprintf(stderr, "%s: can not write to file: %s\n\n", argv[0], ouf);
			exit(EXIT_FAILURE);
		}
		srand(time(NULL));

		if (mode == 1) {
		       fprintf(stdout,"According to mode 1, generating complete graph to file %s\n\n", ouf );	
			graph = generate_complete_graph( columns, rows, from_weight, to_weight);
			if(graph == NULL) {
				fprintf(stderr, "Error in generating graph\n");
				exit(1);
			}
			if ( !does_have_all_edges( graph ) ) {
				fprintf(stderr, "Error, generated graph does not have all the edges\n");
				fclose(out);
				free_graph(graph);
				exit(EXIT_FAILURE);
			}
		}
		else if (mode == 2) { 
			fprintf(stdout,"According to mode 2, generating connected graph to file %s\n\n", ouf );
			graph = generate_connected_graph( start_vertex_number, columns, rows, from_weight, to_weight);
			if(graph == NULL) {
				fprintf(stderr, "Error in generating graph\n");
				exit(1);
			}
			if ( !bfs( graph, start_vertex_number) ) {
				fprintf(stderr, "Error, graph is not connected\n");
				fclose(out);
				free_graph(graph);
				exit(EXIT_FAILURE);
			}
		}
		else {
			// mode 3
			fprintf(stdout,"According to mode 3, generating random graph to file %s\n\n", ouf );
			graph = generate_random_graph(columns,rows, from_weight, to_weight);
			if(graph == NULL) {
				fprintf(stderr, "Error in generating graph\n");
				exit(1);
			}
		}

		if(write_graph( graph, out ))
			exit(EXIT_FAILURE);
		
		if (do_check_connectivity) { /* Check connectivity */
			if (bfs( graph, start_vertex_number))
				fprintf(stdout, "Graph is connected\n\n");
			else
				fprintf(stdout, "Graph is not connected\n\n");
		}

		if( do_find_path ) { // Find the shortest path
			find_path_dijkstra( graph, start_vertex_number, end_vertex_number, do_print_weights );
		}
		fclose(out);
		free_graph(graph);
		exit(EXIT_SUCCESS);
	}
	else {
		fprintf( stderr, "Tou didn't attach input or output file" );
		fprintf( stderr, "%s", usage );
		exit(EXIT_FAILURE);
	}
}


