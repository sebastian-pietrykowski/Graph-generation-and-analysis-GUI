package pl.edu.pw.ee.GeneratorTest;

import pl.edu.pw.ee.Edge;
import pl.edu.pw.ee.Graph;
import pl.edu.pw.ee.generator.Generator;

public class GeneratorTestAuxiliaryMethods {
    
    /**
     * Check if all the edges in <code>graph</code> are located properly - they may link only
     * vertices neighboring vertically or horizontally within range (0,columns*rows-1).
     * @param graph graph to be checked
     * @return <code>true</code> is all vertices are situated properly,
     *         <br><code>false</code> if not
     */
    public static boolean checkEdgesSituation( Graph graph ) {
        for( Edge edge: graph.getAdjacencyList() )
            if( !graph.isEdgeProperlySituated(edge) )
                return false;
        return true;
    }

    /**
     * Checks if <code>graph</code>'s number of edges is equal to the maximum one for this <code>graph</code>.
     * @param graph graph to be checked
     * @return <code>true</code> if number of edges is equal to the maximum one for this <code>graph</code>,
     *         <br><code>false</code> if not
     */
    public static boolean isNumberOfEdgesMax( Graph graph ) {
        return graph.getAdjacencyList().size() == graph.getMaxPossibleNumberOfEdges();
    }


    /**
     * Checks if there not exist multiple edges between the same vertices.
     * @param graph graph to be checked
     * @return <code>true</code> if there not exist multiple edges between the same vertices,
     *         <br><code>false</code> if there exist any multiple edges between the same vertices
     */
    public static boolean areEdgesNotTheSame( Graph graph ) {
        return distinctEdgesCount(graph) == graph.getAdjacencyList().size();
    }

    /**
     * Checks if all the edges in the <code>graph</code> are within range defined in <code>generator</code>.
     * @param generator generator to get values of <code>fromWeight</code> and <code>toWeight</code>
     * @param graph graph which edges are being checked
     * @return <code>true</code> if all the edges in the <code>graph</code> are within range defined in <code>generator</code>,
     *         <br><code>false</code> if any edge in the <code>graph</code> isn't within range defined in <code>generator</code>
     */
    public static boolean areEdgesWeightWithinRange( Generator generator, Graph graph ) {
        for( Edge edge: graph.getAdjacencyList() )
            if( edge.getWeight() < generator.getFromWeight()
                        || edge.getWeight() > generator.getToWeight() )
                return false;
        return true;   
    }

    /**
     * Checks if number of vertices in <code>graph</code> is equal to columns*rows.
     * @param graph graph to be checked
     * @return <code>true</code> is it is,
     *         <code>false</code> if not
     */
    public static boolean isNumberOfVerticesProper( Graph graph ) {
        return graph.getNumberOfVertices() == graph.getColumns() * graph.getRows();
    }
    
    /**
     * Returns number of edges with different <code>fromVertex</code> and <code>toVertex</code>.
     * @param graph graph to be checked
     * @return number of edges
     */
    private static int distinctEdgesCount( Graph graph ) {
        class EdgeWrapper{
            private final Edge edge;
            int fromVertex;
            int toVertex;
            EdgeWrapper( Edge edge ) {
                this.edge = edge;
                this.fromVertex = edge.getFromVertex();
                this.toVertex = edge.getToVertex();
            }
            public Edge unwrap() {
                return edge;
            }
            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + fromVertex;
                result = prime * result + toVertex;
                return result;
            }
            @Override
            public boolean equals(Object obj) {
                if (this == obj)
                    return true;
                if (obj == null)
                    return false;
                if (getClass() != obj.getClass())
                    return false;
                EdgeWrapper other = (EdgeWrapper) obj;
                if (fromVertex != other.fromVertex)
                    return false;
                if (toVertex != other.toVertex)
                    return false;
                return true;
            }
        }
        return (int)graph.getAdjacencyList().stream().map(EdgeWrapper::new).distinct().map(EdgeWrapper::unwrap).count();
    }
}
