package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import static java.lang.Math.abs;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import pl.edu.pw.ee.MyExceptions.IllegalVertexException;
import pl.edu.pw.ee.MyExceptions.IllegalWeightException;

public class Graph {

    int columns, rows;
    private LinkedList<Edge> adjacencyList;

    public Graph() {
        adjacencyList = new LinkedList<>();
    }

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

    public int getNumberOfEdges() {
        return this.getAdjacencyList().size();
    }

    public LinkedList<Edge> getAdjacencyList() {
        return adjacencyList;
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

    public boolean containsEdge(int startVertexNumber, int endVertexNumber) {
        for (int i = 0; i < this.getAdjacencyList().size(); i++) {
            if (this.getAdjacencyList().get(i).getFromVertex() == startVertexNumber
                    && this.getAdjacencyList().get(i).getToVertex() == endVertexNumber) {
                return true;
            }
        }
        return false;
    }

    public static Graph readGraph(File file) throws IOException, IllegalVertexException, IllegalWeightException {

        int toVertex = 0;
        double weight;
        int temp;
        int lineNumber = 0;

        try {
            Graph graph;
            try ( BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line = bufferedReader.readLine();
                String[] dimensions = line.trim().split("\\s+");
                graph = new Graph(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
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
                                    throw new NumberFormatException();
                                }
                                if (toVertex < 0) {
                                    throw new IllegalVertexException(lineNumber + 2);
                                }
                            } else {
                                weight = Double.parseDouble(token);
                                if (weight < 0) {
                                    throw new IllegalWeightException(lineNumber + 2);
                                }
                                Edge edge = new Edge(lineNumber, toVertex, weight);
                                graph.addEdge(edge);
                            }

                        }
                    }
                    lineNumber++;
                }
            }
            return graph;
        } catch (IOException | NumberFormatException e) {
            return null;
        }
    }

    public void writeGraph(File file) throws IOException {
        try ( FileWriter fileWriter = new FileWriter(file)) {

            fileWriter.write(String.valueOf(this.getColumns()));
            fileWriter.write(" ");
            fileWriter.write(String.valueOf(this.getRows()));
            fileWriter.write("\n\t ");

            for (int i = 0; i < this.getNumberOfVertices(); i++) {
                for (Edge edge : this.adjacencyList) {
                    if (edge.getFromVertex() == i) {
                        fileWriter.write(String.valueOf(edge.getToVertex()));
                        fileWriter.write(":");
                        fileWriter.write(String.valueOf(edge.getWeight()));
                        fileWriter.write(" ");
                    }
                }
                fileWriter.write("\n\t ");
            }
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public Set<Integer> potenialNeighbors(int vertex) {
        Set<Integer> potenialNeighborsSet = new HashSet<>();
        int neighbor;
        //neighbor to north
        if ((neighbor = vertex - this.getColumns()) >= 0) {
            potenialNeighborsSet.add(neighbor);
        }
        //neighbor to south
        if ((neighbor = vertex + this.getColumns()) < this.getNumberOfVertices()) {
            potenialNeighborsSet.add(neighbor);
        }
        //neighbors to west and east
        //find number of row containing vertex
        int rowNumber = vertex / this.getColumns() + 1;

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

    public Set<Integer> getNeighbors(int vertex) {
        Set<Integer> neighborsSet = new HashSet<>();
        int neighbor;
        //neighbor to north
        if ((neighbor = vertex - this.getColumns()) >= 0) {
            if (this.containsEdge(vertex, neighbor)) {
                neighborsSet.add(neighbor);
            }
        }
        //neighbor to south
        if ((neighbor = vertex + this.getColumns()) < this.getNumberOfVertices()) {
            if (this.containsEdge(vertex, neighbor)) {
                neighborsSet.add(neighbor);
            }
        }
        //neighbors to west and east
        //find number of row containing vertex
        int rowNumber = vertex / this.getColumns() + 1;

        //find first and last elements of row
        int startRowNumber = (rowNumber - 1) * this.getColumns();
        int endRowNumber = ((rowNumber) * this.getColumns()) - 1;

        //check if neighbor to east can exist
        if (startRowNumber < vertex) {
            if (this.containsEdge(vertex, vertex - 1)) {
                neighborsSet.add(vertex - 1);
            }
        }

        //check if neighbor to west can exist
        if (vertex < endRowNumber) {
            if (this.containsEdge(vertex, vertex + 1)) {
                neighborsSet.add(vertex + 1);
            }
        }

        return neighborsSet;

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
        return (4 * 2 + (this.getColumns() - 2) * 2 * 3 + (this.getRows() - 2) * 2 * 3 + ((this.getRows() - 2) * (this.getColumns() - 2) * 4));
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

    public double getMaxWeight() {
        return adjacencyList.stream().mapToDouble( e -> e.getWeight() ).max().getAsDouble();
    }

    public void printEdges() {
        for (Edge e : adjacencyList) {
            System.out.println("from: " + e.getFromVertex() + ", to: " + e.getToVertex() + ", weight: " + e.getWeight());
        }
    }
}
