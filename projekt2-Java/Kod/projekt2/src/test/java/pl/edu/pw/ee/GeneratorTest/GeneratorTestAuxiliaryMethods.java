package pl.edu.pw.ee.GeneratorTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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
        return distinctEdgesCount(graph) == graph.getAdjacencyList().size();
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
