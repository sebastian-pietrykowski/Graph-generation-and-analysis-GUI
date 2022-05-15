package pl.edu.pw.ee;

import java.util.Random;

public class Generator {
    
    private Graph graph;
    private double fromWeight;
    private double toWeight;

    public Generator( int columns, int rows, double fromWeight, double toWeight ) {

        if( fromWeight < 0 || toWeight < 0 )
            throw new IllegalArgumentException("Minimalna waga lub maksymalna waga mniejsza od 0:");
        if( toWeight < fromWeight )
            throw new IllegalArgumentException("Minimalna waga jest większa niż maksymalna waga.");

        graph = new Graph( columns, rows );
        this.fromWeight = fromWeight;
        this.toWeight = toWeight;
    }

    /**
     * Generates graph according to the mode 2: with all possible edges (between
     * vertices neighboring vertically or horizontally) and random weights in the range
     * of (<code>fromWeight</code>,<code>toWeight</code>)
     * 
     * @return generated graph
     */
    public Graph generateCompleteGraph() {

        // add edges horizontally
        for( int verticalCounter = 0; verticalCounter < graph.getRows(); verticalCounter++)
            for( int horizontalCounter = 1; horizontalCounter < graph.getColumns(); horizontalCounter++ ) {
                int vertexFrom = verticalCounter * graph.getColumns() + horizontalCounter - 1;
                int vertexTo = vertexFrom + 1;

                graph.addEdge( new Edge(vertexFrom, vertexTo, generateEdgeWeight()));
                graph.addEdge( new Edge(vertexTo, vertexFrom, generateEdgeWeight()));
            }

        return graph;
    }

    private double generateEdgeWeight() {
        Random random = new Random();
        return fromWeight + random.nextDouble() * (toWeight - fromWeight) + Double.MIN_VALUE;
    }

    public static void main(String[] args) {
        Generator generator = new Generator(3, 3, 0, 1);
        generator.generateCompleteGraph();
        
    }
}
