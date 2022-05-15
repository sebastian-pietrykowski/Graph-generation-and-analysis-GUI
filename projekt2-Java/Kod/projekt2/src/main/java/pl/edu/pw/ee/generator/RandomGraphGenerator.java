package pl.edu.pw.ee.generator;

import java.util.HashSet;
import java.util.Set;

import pl.edu.pw.ee.Graph;

public class RandomGraphGenerator extends Generator {

    private Graph graph;
    private int columns;
    private int rows;
    private double fromWeight;
    private double toWeight;

    public RandomGraphGenerator(int columns, int rows, double fromWeight, double toWeight) {
        super(columns, rows, fromWeight, toWeight);
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
        
        graph = new Graph( columns, rows );

        //int maxPossibleEdges = graph.maxPossibleNumberOfEdges();
        int maxPossibleEdges = columns * rows;
        Set<Integer> remainingVertices = new HashSet<>();
        

        return graph;
    }
    
}
