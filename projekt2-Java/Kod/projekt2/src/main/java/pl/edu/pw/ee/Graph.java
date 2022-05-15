package pl.edu.pw.ee;

import java.util.LinkedList;
import java.util.Set;

public class Graph {
    int columns, rows;
    private LinkedList<Edge> adjList[];

    public Graph( int columns, int rows ) {
        this.columns = columns;
        this.rows = rows;
    }

    public int getColumns() {return columns;}
    public int getRows() {return rows;}
    public int getNumberOfVertices() {return 1;}
    
    public void addEdge( Edge edge ) {}
    public void removeEdge( int index ) {}
    public Edge getEdge( int index ) {return null;}
    public int getNumberOfEdges() {return 1;}

    public Set<Integer> potenialNeighbors( int vertex ) {return null;}

    public int maxPossibleNumberOfEdges() {return 1;}
}
