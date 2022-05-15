package pl.edu.pw.ee.GeneratorTest;

import pl.edu.pw.ee.Edge;
import pl.edu.pw.ee.Graph;
import pl.edu.pw.ee.generator.Generator;

public class GeneratorTestAuxiliaryMethods {
    
    public static boolean checkEdgesSituation( Graph graph ) {
        for( Edge edge: graph.getAdjacencyList() )
            if( !graph.isEdgeProperlySituated(edge) )
                return false;
        return true;
    }

    public static boolean isNumberOfEdgesMax( Graph graph ) {
        return graph.getAdjacencyList().size() == graph.getMaxPossibleNumberOfEdges();
    }

    public static boolean areEdgesNotTheSame( Graph graph ) {
        return graph.getAdjacencyList().stream().distinct().count() == graph.getAdjacencyList().size();
    }

    public static boolean areEdgesWeightWithinRange( Generator generator, Graph graph ) {
        for( Edge edge: graph.getAdjacencyList() )
            if( edge.getWeight() < generator.getFromWeight()
                        || edge.getWeight() > generator.getToWeight() )
                return false;
        return true;   
    }

    public static boolean checkNumberOfVertices( Graph graph ) {
        return graph.getNumberOfEdges() == graph.getColumns() * graph.getRows();
    }
}
