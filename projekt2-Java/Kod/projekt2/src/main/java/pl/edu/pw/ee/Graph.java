package pl.edu.pw.ee;

import java.io.File;
import java.util.LinkedList;
import java.util.Set;

public class Graph {
    int columns, rows;
    private LinkedList<Edge> adjacencyList;

    public Graph( int columns, int rows ) {
        this.columns = columns;
        this.rows = rows;
        adjacencyList = new LinkedList<>();
    }

    public int getColumns() {return columns;}
    public int getRows() {return rows;}
    public int getNumberOfVertices() {return 1;}
    
    public void addEdge( Edge edge ) {}
    public void removeEdge( int index ) {}
    public Edge getEdge( int fromVertex, int toVertex ) {return null;}
    public int getNumberOfEdges() {return 1;}

    public LinkedList<Edge> getAdjacencyList(){
        return adjacencyList;}
    public Set<Integer> potenialNeighbors( int vertex ) {return null;}

    public int getMaxPossibleNumberOfEdges() {return 1;}
    public boolean isEdgeSituatedProperly( Edge edge ) {return true;}

    public static Graph readGraph( File file ) { return null; }
}
