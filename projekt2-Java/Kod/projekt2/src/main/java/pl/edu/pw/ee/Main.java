package pl.edu.pw.ee;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import pl.edu.pw.ee.generator.CompleteGraphGenerator;
import pl.edu.pw.ee.generator.ConnectedGraphGenerator;
import pl.edu.pw.ee.generator.Generator;
import pl.edu.pw.ee.generator.RandomGraphGenerator;

public class Main {

    public static void main(String[] args) throws IOException {

        /*
        File file = new File("C:\\Users\\Paweł\\Downloads\\mygraph3.txt");
        try {
            Graph.readGraph(file);
            BFS bfs = new BFS(Graph.readGraph(file), 0);
            System.out.println(bfs.checkConnectivty());
        } catch (IOException ex) {
            System.err.println("zle");
        }
        */

        /*
        Generator g1 = new CompleteGraphGenerator(3, 3, 0, 1);
        Graph graph1 = g1.generate();
        graph1.printEdges();
        
        System.out.println("------------------------------");
        
        
        Generator g2 = new RandomGraphGenerator(5, 5, 0, 1);
        Graph graph2 = g2.generate();
        graph2.printEdges();
        */
        /*
        Generator g3 = new ConnectedGraphGenerator(3, 3, 0, 1, 0);
        Graph graph3 = g3.generate();
        */
        /*
        Generator g = new CompleteGraphGenerator(2, 2, 0, 1);
        Graph gr = g.generate();
        //gr.printEdges();
        System.out.println( gr.getMaxPossibleNumberOfEdges());
        System.out.println(gr.getAdjacencyList().size());
        //System.out.println(new BFS(gr, 0).checkConnectivty());
        */

        //Generator gen6 = new ConnectedGraphGenerator(1, 15, 7813.98, 9999.999, 0);
        //Graph graph6 = gen6.generate();
        //System.out.println(graph6.getNumberOfEdges());
        //System.out.println(graph6.getMaxPossibleNumberOfEdges());
        //graph6.printEdges();
        //Dijkstra dijkstra = new Dijkstra(graph6, 0);
        
        //System.out.println( dijkstra.determineShortestPath(0, 3));
        //System.out.println(Arrays.toString(dijkstra.getPredecessors()));

        //Graph graph7 = Graph.readGraph(new File("testGraphs/Dijkstra/graph0_5x5_connected.txt"));
        //System.out.println(graph7.getColumns());
        //System.out.println(graph7.getAdjacencyList().size());
        //graph7.printEdges();
    }



}
