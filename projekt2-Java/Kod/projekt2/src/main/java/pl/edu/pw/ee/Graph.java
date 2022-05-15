package pl.edu.pw.ee;

import java.util.LinkedList;

public class Graph {
    int columns, rows;
    private LinkedList<Edge> adjList[];
        public Graph( int columns, int rows ) {
            this.columns = columns;
            this.rows = rows;
        }
        public int getNumberOfVertices() {return 1;}
        public int getColumns() {return columns;}
        public int getRows() {return rows;}
        public void addEdge( Edge e ) {}
}
