package pl.edu.pw.ee;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * It is used to find the shortest possible path between 2 vertices.
 */
public class Dijkstra {
    
    private Graph graph;
    private int startVertexNumber; // number of vertex to start searching with
    private int[] predecessors; /* value in array corresponding to index being number of vertex
                                   is previous vertex leading to this vertex, where where the path to
                                   this vertex is the shortest among all posible ones */

    private double[] distances; /* value in array corresponding to index being number of vertex
                                   is the shortest combined distance of path from
                                   startVertexNumber to this vertex */
    private Set<Integer> checkedVerticesSet = new HashSet<>();
     PriorityQueue<VertexWithDistance> uncheckedVerticesPQ = new PriorityQueue<>();

    public Dijkstra( Graph graph, int startVertexNumber ) {
        this.graph = graph;
        this.startVertexNumber = startVertexNumber;
    }
    
    /**
     * Initiates values according to Dijkstra's Algorithm.
     */
    private void initiateValues() {
        for( int vertex = 0; vertex < graph.getNumberOfVertices(); vertex++ ) {
            distances[vertex] = Double.POSITIVE_INFINITY;
            predecessors[vertex] = -1;
        }
        distances[startVertexNumber] = 0;
    }

    /**
     * If path leading from vertex <code>startVertexNumber</code> to <code>fromVertex</code> and by one
     * edge to <code>toVertex</code> is shorter than current shortest path leading to vertex
     * <code>toVertex</code>, then set it as new shortest path to this vertex.
     * @param fromVertex vertex where edge beggins
     * @param toVertex vertex where edge ends
     */
    private void relax( int fromVertex, int toVertex ) {

        double potenitalNewDistance = distances[fromVertex] + graph.getEdge(fromVertex, toVertex).getWeight();
        if( distances[toVertex] > potenitalNewDistance ) {
            distances[toVertex] = potenitalNewDistance;
            predecessors[toVertex] = fromVertex;
        }
    }

    /**
     * Function responsible for performing Dijkstra's algorithm. <p>
     * This is the only method to perform before getting the results.<p>
     * 
     * It sets values in array <code>predecessors</code> so that value corresponding to index being vertex number
     * 
     * 
     */
    private void dijkstra() {
        /*
        – funkcja odpowiedzialna za wykonanie algorytmu Dijkstry; ustawia
        wartości w tablicy predecessors[] tak, aby wartość tablicy o danym indeksie równoważnemu
        numerowi wierzchołka odpowiadała numerowi wierzchołka będącego jego poprzednikiem;
        ustawia wartości w tablicy distances tak, aby wartość tablicy o danym indeksie równoważnemu
        numerowi wierzchołka odpowiadała łącznej najkrótszej odległości od wierzchołka
        start; wywołuje metody initiateValues i relax
        */
    }


    class VertexWithDistance implements Comparable<VertexWithDistance> {
        private int vertex;
        private double distance;
        
        VertexWithDistance( int vertex, double distance ) {
            this.vertex = vertex;
            this.distance = distance;
        }
    
        @Override
        public int compareTo(VertexWithDistance that) {
            return Double.compare( this.distance, that.distance );
        }
    
        @Override
        public String toString(){
            return "v: " + vertex + ", d: " + distance;
    
        }
    
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            long temp;
            temp = Double.doubleToLongBits(distance);
            result = prime * result + (int) (temp ^ (temp >>> 32));
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
            VertexWithDistance other = (VertexWithDistance) obj;
            if (Double.doubleToLongBits(distance) != Double.doubleToLongBits(other.distance))
                return false;
            return true;
        }
    }
}


