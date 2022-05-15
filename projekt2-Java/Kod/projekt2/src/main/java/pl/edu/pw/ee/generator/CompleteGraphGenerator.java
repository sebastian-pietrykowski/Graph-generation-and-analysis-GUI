package pl.edu.pw.ee.generator;

import pl.edu.pw.ee.Edge;
import pl.edu.pw.ee.Graph;

public class CompleteGraphGenerator extends AbstractGenerator implements Generator {

    private Graph graph;
    private int columns;
    private int rows;
    private double fromWeight;
    private double toWeight;

    public CompleteGraphGenerator( int columns, int rows, double fromWeight, double toWeight ) {
        super( columns, rows, fromWeight, toWeight );
        this.columns = columns;
        this.rows = rows;
        this.fromWeight = fromWeight;
        this.toWeight = toWeight;
    }

    /**
     * Generates graph according to the mode 1: with all possible edges (between
     * vertices neighboring vertically or horizontally) and random weights in the range
     * of (<code>fromWeight</code>,<code>toWeight</code>).
     * 
     * @return generated graph
     */
    @Override
    public Graph generate() {
        
        graph = new Graph( columns, rows );

        // add edges horizontally
        for( int verticalCounter = 0; verticalCounter < graph.getRows(); verticalCounter++)
            for( int horizontalCounter = 1; horizontalCounter < graph.getColumns(); horizontalCounter++ ) {
                int vertexFrom = verticalCounter * graph.getColumns() + horizontalCounter - 1;
                int vertexTo = vertexFrom + 1;

                graph.addEdge( new Edge(vertexFrom, vertexTo, super.generateEdgeWeight()));
                graph.addEdge( new Edge(vertexTo, vertexFrom, super.generateEdgeWeight()));
            }
        // add edges vertically
        for( int horizontalCounter = 0; horizontalCounter < graph.getColumns(); horizontalCounter++ )
            for( int verticalCounter = 1; verticalCounter < graph.getRows(); verticalCounter++ ) {
                int vertexFrom = (verticalCounter-1) * graph.getColumns() + horizontalCounter;
                int vertexTo = verticalCounter * graph.getColumns() + horizontalCounter;

                graph.addEdge( new Edge(vertexFrom, vertexTo, super.generateEdgeWeight()) );
                graph.addEdge( new Edge(vertexTo, vertexFrom, super.generateEdgeWeight()) );
            }
        return graph;
    }
    
}
