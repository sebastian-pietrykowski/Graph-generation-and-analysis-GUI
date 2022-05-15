package pl.edu.pw.ee.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import pl.edu.pw.ee.Edge;
import pl.edu.pw.ee.Graph;

public class RandomGraphGenerator extends CompleteGraphGenerator implements Generator {

    private Graph graph;
    private int columns;
    private int rows;
    private double fromWeight;
    private double toWeight;

    public RandomGraphGenerator( int columns, int rows, double fromWeight, double toWeight ) {
        super( columns, rows, fromWeight, toWeight );
        this.columns = columns;
        this.rows = rows;
        this.fromWeight = fromWeight;
        this.toWeight = toWeight;
    }

    /**
     * Generates graph according to the mode 3: with with random occurance of edges
     * and random weights in the range of (<code>fromWeight</code>,<code>toWeight</code>).
     * 
     * @return generated graph
     */
    @Override
    public Graph generate() {

        // get graph with all possible edges
        graph = super.generate();

        // remove random number of edges
        int maxPossibleEdges = graph.maxPossibleNumberOfEdges();
        Random random = new Random();
        int numberOfEdgesToRemove = random.nextInt(maxPossibleEdges);

        while( numberOfEdgesToRemove > 0 ) {
            int indexOfEdgeToDelete = random.nextInt( graph.getNumberOfEdges() );
            graph.removeEdge( indexOfEdgeToDelete );
        }
        return graph;
    }
}
