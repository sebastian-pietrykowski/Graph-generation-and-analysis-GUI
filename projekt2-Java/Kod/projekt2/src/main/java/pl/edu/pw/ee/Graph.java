package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
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
    }

    public void removeEdge(int index) {
    }

    public Edge getEdge(int index) {
        return null;
    }

    public Edge getEdge( int startVertexNumber, int endVertexNumber ) {
        return null;
    }

    public int getNumberOfEdges() {
        return 1;
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
                            } catch (Exception e) {

                            }
                            if (toVertex < 0) {
                                //TODO
                            }
                        } else {
                            weight = Double.parseDouble(token);
                            Edge edge = new Edge(lineNumber, toVertex, weight);
                            graph.adjacencyList.add(edge);
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
        return null;
    }

    public int getMaxPossibleNumberOfEdges() {
        return 1;
    }

    public boolean isEdgeSituatedProperly(Edge edge) {
        return true;
    }
}
