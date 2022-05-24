package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import static java.lang.Math.abs;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Graph {

    int columns, rows;
    private LinkedList<Edge> adjacencyList;

    public Graph(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        adjacencyList = new LinkedList<>();
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public int getNumberOfVertices() {
        return columns * rows;
    }

    public void addEdge(Edge edge) {
        this.getAdjacencyList().add(edge);
    }

    public void removeEdge(int index) {
        this.getAdjacencyList().remove(index);
    }

    public Edge getEdge(int index) {
        return this.getAdjacencyList().get(index);
    }

    public Edge getEdge(int startVertexNumber, int endVertexNumber) {
        for (int i = 0; i < this.getAdjacencyList().size(); i++) {
            if (this.getAdjacencyList().get(i).getFromVertex() == startVertexNumber
                    && this.getAdjacencyList().get(i).getToVertex() == endVertexNumber) {
                return this.getAdjacencyList().get(i);
            }
        }
        return null;
    }

    public int getNumberOfEdges() {
        return this.getAdjacencyList().size();
    }

    public static Graph readGraph(File file) throws IOException {

        int toVertex = 0;
        double weight;
        int temp;
        int lineNumber = 0;

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line = bufferedReader.readLine();

            String[] dimensions = line.trim().split("\\s+");

            Graph graph = new Graph(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));

            while ((line = bufferedReader.readLine()) != null) {
                temp = 1;

                String[] tokens = line.split(":| ");

                for (String token : tokens) {
                    if (token.trim().length() > 0) {

                        temp++;
                        if (temp % 2 == 0) {
                            try {
                                toVertex = Integer.parseInt(token);
                            } catch (NumberFormatException e) {

                            }
                            if (toVertex < 0) {
                                //TODO
                            }
                        } else {
                            weight = Double.parseDouble(token);
                            Edge edge = new Edge(lineNumber, toVertex, weight);
                            graph.addEdge(edge);
                        }

                    }
                }

                lineNumber++;
            }
            return graph;
        } catch (IOException e) {
            //  e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public void writeGraph(File file) {

    }

    public LinkedList<Edge> getAdjacencyList() {
        return adjacencyList;
    }

    public Set<Integer> potenialNeighbors(int vertex) {
        Set<Integer> potenialNeighborsSet = new HashSet<>();
        int neighbor;
        //neighbor to north
        if ((neighbor = vertex - this.getColumns()) > 0) {
            potenialNeighborsSet.add(neighbor);
        }
        //neighbor to south
        if ((neighbor = vertex + this.getColumns()) < this.getNumberOfVertices()) {
            potenialNeighborsSet.add(neighbor);
        }
        //neighbors to west and east
        //find number of row containing vertex
        int rowNumber = vertex / this.getColumns();

        //find first and last elements of row
        int startRowNumber = (rowNumber - 1) * this.getColumns();
        int endRowNumber = ((rowNumber) * this.getColumns()) - 1;

        //check if neighbor to east can exist
        if (startRowNumber < vertex) {
            potenialNeighborsSet.add(vertex - 1);
        }

        //check if neighbor to west can exist
        if (vertex < endRowNumber) {
            potenialNeighborsSet.add(vertex + 1);
        }

        return potenialNeighborsSet;

    }

    public int getMaxPossibleNumberOfEdges() {
        /* We multiply by 4 because there are only four vertices that are "corners" of the graph that have a maximum of 2 connections to other vertices,
    *
    *  then we add a number that is the product of "(this.getColumns() -2 )" , becuase this is the number of vertices in the first columns that are not corner vertices,
    *  and "2" because we also consult the last column,
    *  and "3" because this vertices can be connected to up to three other vertices,
    *
    *  then we add a number that is the product of "(this.getRows() -2 )" ,because this is the number of vertives in the first row that are not corner vertices ,
    *  and "2" because we also consult the last row,
    *  and "3" beacuse this vertices can be connected to up to three other vertices,
    *
    *  then we add a number that is the product of "(this.getRows() - 2 ) * ( this.getColumns() - 2 )" because this is the number of vertices that
       are not the most distant vertices from the center of the graph - lying on its outer sides
    *  and "4" because this vertices can be connected to up to four other vertices.
         */
        if( columns == 1) return rows;
        if( rows == 1 ) return columns;
        return (4 * 2 + (this.getColumns() - 2) * 2 * 3 + (this.getRows() - 2) * 2 * 3 + ((this.getRows() - 2) * (2 - this.getColumns())) * 4);
    }

    public boolean isEdgeSituatedProperly(Edge edge) {

        //checking if it is a vertival connection
        if (this.getColumns() == abs(edge.getFromVertex() - edge.getToVertex())) {
            return true;
        }
        //checking if it is a horizontal connection
        if (abs(edge.getFromVertex() - edge.getToVertex()) == 1) {
            return true;
        }

        return false;
    }

    public void printEdges() {
        for( Edge e: adjacencyList) {
            System.out.println("from: " + e.getFromVertex() + ", to: " + e.getToVertex() + ", weight: " + e.getWeight());
        }
    }
}
