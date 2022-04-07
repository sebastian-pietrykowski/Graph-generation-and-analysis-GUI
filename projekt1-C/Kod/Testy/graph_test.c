#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "graph.h"
#include "graph_test.h"
#define MAXLINE 256

int * expected_vertexes;
double * expected_weights;

int does_have_all_edges_test(graph_t graph) {
    int temp = 0;
    for (int i = 0; i < graph->no_vertexes; i++) {
        for (int j = 0; j < graph->no_vertexes; j++) {
            if (graph->adj_mat[i][j] != -1) {
                temp++;
            }
        }
    }
    int expected = ((4 * 2 + (graph->columns - 2) * 2 * 3 + (graph->rows - 2) * 2 * 3 + ((graph->rows - 2) * (graph->columns - 2)) * 4));
        if (temp == expected )
            return temp;
        else
            return 0;
    
}
void expected_make ( graph_t graph) {
 
            expected_vertexes = expected_values_v(graph);
         expected_weights = expected_values_w(graph);
	if(expected_vertexes == NULL || expected_weights == NULL) {
		fprintf(stdout,"Failed to allocate memory for excepted values, can not perform test\n");
		exit(EXIT_FAILURE);
	}

}
 
graph_t test_read_graph(FILE* in, graph_t graph) {
	
	
    int columns, rows;
    if ((fscanf(in, "%d %d", &(rows), &(columns)) != 2)) {
        fprintf(stderr, "Error, can not read the dimensions of the graph");
        return NULL;
    }
    /*
      graph = make_graph(columns, rows);
      if(graph == NULL) {
        exit(EXIT_FAILURE);
      }
    */

    char line[MAXLINE];
    char delim[3] = " :"; /* delimiter */
    double weight;
    int to_vertex;
    int from_vertex;
/*We start the iteration from -1 because the vertex and weight data start on the second line of the file */
for (from_vertex = -1; from_vertex < graph -> no_vertexes; from_vertex++) {
    while (fgets(line, MAXLINE, in ) != NULL) {
        char * token = strtok(line, delim); /* breaking line into a series of tokens */
        int temp = 1;

        while (token != NULL) {
            if (!isspace( * token)) {
                /* We are only interested in numbers */
                temp++;

                if (temp % 2 == 0) {
                    /* Thanks to the condition if we can alternately enter vertexes and weights into the array of subsequent tokens */
                    to_vertex = atoi(token);
                    if (to_vertex != * expected_vertexes) {
                        fprintf(stderr, "cos zle??");
                        exit(EXIT_FAILURE);
                    }
                    expected_vertexes++;
                    if (to_vertex < 0) {
                        fprintf(stderr, "Error, invalid vertex number, line number : %d\n", from_vertex + 2);
                        return NULL;
                    }
                } else {
                    weight = atof(token);
                    if (weight != * expected_weights) {
                        fprintf(stderr, "cos zl, moze tu?e");
                        exit(EXIT_FAILURE);
                    }
                    expected_weights++;
                    graph -> adj_mat[from_vertex][to_vertex] = weight;
                }
                token = strtok(NULL, delim);
            } else
                token = strtok(NULL, delim); /* If the token is not a number, download another */
        }
        break;
    }
}



return graph;
}


int* expected_values_v(graph_t graph) {
  if (graph->no_vertexes == 3 * 3) {
    static int expected_vertexes[] = {1, 3, 0, 4, 2, 1, 5, 0, 4, 6, 1, 3,
                                      5, 7, 2, 4, 8, 3, 7, 6, 4, 8, 5, 7};
    return expected_vertexes;
  }
  if (graph->no_vertexes == 7 * 4) {
    static int expected_vertexes[] = {
        1,  4,  5,  2,  0,  6,  3,  1,  7,  2,  8,  0,  5,  1,  9,  6,  4,  10,
        7,  2,  5,  6,  3,  11, 4,  12, 9,  13, 5,  8,  10, 14, 6,  9,  11, 15,
        10, 7,  13, 16, 8,  17, 9,  12, 14, 10, 15, 18, 13, 19, 11, 14, 20, 17,
        12, 21, 13, 16, 18, 17, 22, 14, 19, 18, 15, 23, 24, 21, 16, 17, 20, 25,
        22, 21, 23, 18, 26, 27, 22, 19, 20, 25, 21, 24, 26, 27, 22, 25, 26, 23};
    return expected_vertexes;
  }
  if (graph->no_vertexes == 6 * 6) {
    static int expected_vertexes[] = {1,  6,  1,  3,  10, 6,  10, 6,
                                      18, 24, 21, 17, 22, 24, 29, 24};
    return expected_vertexes;
  }
	return NULL;
}

double* expected_values_w(graph_t graph) {
  if (graph->no_vertexes == 3 * 3) {
    static double expected_weights[] = {
        0.8864916775696521,  0.2187532451857941,  0.2637754478952221,
        0.6445273453144537,  0.4630166785185348,  0.8650384424149676,
        0.42932761976709255, 0.5702072705027322,  0.86456124269257,
        0.8961825862332892,  0.9452864187437506,  0.8961825862332892,
        0.9299058855442358,  0.8961825862332892,  0.5956443807073741,
        0.31509645530519625, 0.40326574227480094, 0.7910000224849713,
        0.7017066711437372,  0.9338390704123928,  0.797053444490967,
        0.7191822139832875,  0.7500681437013168,  0.5486221194511974};
    return expected_weights;
  }
  if (graph->no_vertexes == 7 * 4) {
    static double expected_weights[] = {
        0.8864916775696521,  0.2187532451857941,  0.2637754478952221,
        0.6445273453144537,  0.4630166785185348,  0.8650384424149676,
        0.42932761976709255, 0.6024952385895536,  0.5702072705027322,
        0.86456124269257,    0.9452864187437506,  0.8961825862332892,
        0.9299058855442358,  0.5956443807073741,  0.31509645530519625,
        0.40326574227480094, 0.44925728962449873, 0.7910000224849713,
        0.7017066711437372,  0.20056970253149548, 0.3551383541997829,
        0.9338390704123928,  0.797053444490967,   0.7191822139832875,
        0.7500681437013168,  0.5486221194511974,  0.25413610146892474,
        0.8647843756083231,  0.8896910556803207,  0.4952122733888106,
        0.40183865613683645, 0.5997502519024634,  0.5800735782304424,
        0.7796297161425758,  0.3769093717781341,  0.3166804339669712,
        0.14817882621967496, 0.8363991936747263,  0.5380334165340379,
        0.8450927265651617,  0.5238810833905587,  0.5983997022381085,
        0.7870744571266874,  0.738310558943156,   0.45746700405234864,
        0.8801737147065481,  0.6153113201667844,  0.2663754517229303,
        0.22588495147495308, 0.9069409600272764,  0.7381164412958352,
        0.5723418590602954,  0.1541384547533948,  0.3985282545552262,
        0.29468967639003735, 0.7576872377752496,  0.4858285745038984,
        0.28762266137392745, 0.6264588252010738,  0.6628790185051667,
        0.9203623808816617,  0.8394013782615275,  0.27514794195197545,
        0.6976948178131532,  0.4893608558927002,  0.5604145612239925,
        0.8901867253885717,  0.561967244435089,   0.35835658210649646,
        0.8438726714274797,  0.3311114339467634,  0.7968809594947989,
        0.9281943906422196,  0.6354858042070723,  0.33441278736675584,
        0.43027465583738667, 0.3746522679684584,  0.8914256412658524,
        0.8708278171237049,  0.4478162295166256,  0.35178269705930043,
        0.2054048551310126,  0.6830700124292063,  0.3148089827888376,
        0.5449034876557145,  0.2104213229517653,  0.8159939689806697,
        0.4989269533310492,  0.44272335750313074, 0.4353604625664018
    };
    return expected_weights;
  }
  if (graph->no_vertexes == 6 * 6) {
    static double expected_weights[] = {
        0.106248, 0.940403, 0.032190,
        0.856341, 0.776824, 0.691529,
        0.146817, 0.130497, 0.512511,
	0.683106, 0.117001, 0.445197,
        0.103091, 0.959431, 0.098592,
        0.972594
    };
    return expected_weights;
  }
	return NULL;
}

