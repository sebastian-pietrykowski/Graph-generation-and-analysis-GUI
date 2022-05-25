package pl.edu.pw.ee;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * It is used to find the shortest possible path between 2 vertices.
 */
public class Dijkstra {
    
    private Graph graph;
    private int startVertexNumber; // number of vertex to start searching with
    private int[] predecessors; /* value in array corresponding to index being number of vertex
                                   is previous vertex leading to this vertex, where the path to
                                   this vertex is the shortest among all posible ones */

    private double[] distances; /* value in array corresponding to index being number of vertex
                                   is combined distance of the shortest path from
                                   startVertexNumber to this vertex */
    private Set<Integer> checkedVerticesSet = new HashSet<>();
    PriorityQueue<VertexWithDistance> uncheckedVerticesPQ = new PriorityQueue<>();
    boolean didPerformAlgorithm = false;

    public Dijkstra( Graph graph, int startVertexNumber ) {
        this.graph = graph;
        this.startVertexNumber = startVertexNumber;

    }
    
    public int[] getPredecessors() { return predecessors; }

    /**
     * Initiates values according to Dijkstra's Algorithm.
     */
    private void initiateValues() {
        predecessors = new int[graph.getNumberOfVertices()];
        distances = new double[graph.getNumberOfVertices()];

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
        System.out.println("relax - f: " + fromVertex + ", t: " + toVertex);
        double potenitalNewDistance = distances[fromVertex] + graph.getEdge(fromVertex, toVertex).getWeight();
        if( distances[toVertex] > potenitalNewDistance ) {
            System.out.println("relax wew - f: " + fromVertex + ", t: " + toVertex);
            distances[toVertex] = potenitalNewDistance;
            setDistanceToVertexInUncheckedVerticesSet( toVertex, potenitalNewDistance );
            predecessors[toVertex] = fromVertex;
        }
    }

    /**
     * Function responsible for performing Dijkstra's algorithm. <p>
     * This is the only method to perform before getting the results.<p>
     * 
     * It sets values in array <code>predecessors</code> so that value corresponding to
     * index being vertex number of vertex so that it is previous vertex leading to this vertex
     * (being index), where the path to this vertex is the shortest among all posible ones. <p>
     * 
     * It sets value in array <code>distances</code> corresponding to index being number
     * of vertex so that it is combined distance of the shortest path from startVertexNumber
     * to this vertex (being index).
     */
    public void dijkstra() {

        initiateValues();
        for( int vertex = 0; vertex < graph.getNumberOfVertices(); vertex++ )
            uncheckedVerticesPQ.add( new VertexWithDistance(vertex, distances[vertex]) );
        while( !uncheckedVerticesPQ.isEmpty() ) {
                int fromVertex = uncheckedVerticesPQ.remove().getVertex();
                checkedVerticesSet.add( fromVertex );
                for( int toVertex: graph.getNeighbors(fromVertex) )
                    relax(fromVertex, toVertex);
        }
    }

    private void setDistanceToVertexInUncheckedVerticesSet( int vertexNumber, double newDistace ) {
        for( VertexWithDistance v: uncheckedVerticesPQ )
            if( v.getVertex() == vertexNumber ) {
                v.setDistance(newDistace);
                break;
            }
    }

    /**
     * Returns array containing vertices, creating the shortest possible path from
     * vertex <code>startVertexNumber</code> to <code>endVertexNumber</code>.<p>
     * It is ready to be performed without using any methods from this class before it.
     * @param startVertexNumber vertex where path beggins
     * @param endVertexNumber vertex where path ends
     * @return array of vertices creating path if the path exists, <br>
     *         <code>null</code> if such path doesn't exist
     */
    public LinkedList<Integer> determineShortestPath ( int startVertexNumber, int endVertexNumber ) {
        LinkedList<Integer> path = new LinkedList<>();

        if (!didPerformAlgorithm)
            dijkstra();

        int element = endVertexNumber;
        path.add(endVertexNumber);
        while(true) {
            element = predecessors[element];
            if( element < 0 )
                return null;
            path.add(element);
            if( element == startVertexNumber ) {
                Collections.reverse(path);
                return path;
            }
        }
    }

    /**
     * Class used in priority queue.
     */
    class VertexWithDistance implements Comparable<VertexWithDistance> {
        private int vertex;
        private double distance;
        
        VertexWithDistance( int vertex, double distance ) {
            this.vertex = vertex;
            this.distance = distance;
        }
    
        public int getVertex() { return vertex; }
        public double getDistance() { return distance; }
        public void setDistance( double distance) { this.distance = distance; }

        @Override
        public int compareTo(VertexWithDistance that) {
            return Double.compare( this.distance, that.distance );
        }
    
        @Override
        public String toString() {
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


