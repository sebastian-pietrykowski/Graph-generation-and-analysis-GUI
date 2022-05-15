package pl.edu.pw.ee;

public class Graph {
    int columns, rows;
        Graph( int columns, int rows ) {
            this.columns = columns;
            this.rows = rows;
        }
        int getNumberOfVertices() {return 1;}
        int getColumns() {return columns;}
        int getRows() {return rows;}
        void addEdge( Edge e ) {}
}
