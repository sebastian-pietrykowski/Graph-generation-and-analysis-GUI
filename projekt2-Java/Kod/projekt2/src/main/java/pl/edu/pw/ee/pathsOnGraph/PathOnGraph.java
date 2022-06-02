package pl.edu.pw.ee.pathsOnGraph;

import java.util.ArrayList;

import pl.edu.pw.ee.Edge;
import pl.edu.pw.ee.Graph;

public class PathOnGraph {

    private int fromVertex;
    private int toVertex;
    private int[] path;
    private Graph graph;

    public PathOnGraph( int fromVertex, int toVertex, int[] path, Graph graph ) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.path = path;
        this.graph = graph;
    }

    public int getFromVertex() {
        return fromVertex;
    }

    public int getToVertex() {
        return toVertex;
    }

    public int[] getPath() {
        return path;
    }

    public String getPathToStringStandardVariant() {
        String pathToString = "";
        for( int vertexNumber = 0; vertexNumber < path.length-1; vertexNumber++)
            pathToString += vertexNumber + " -> ";
        pathToString += path[path.length-1];
        return pathToString;
    }

    public String getPathToStringExtendedVariant() {
        String pahtToString = "";
        for( int vertexNumber = 1; vertexNumber < path.length; vertexNumber++)
            pahtToString += path[vertexNumber-1] + " ("
                    + graph.getEdge(vertexNumber-1, vertexNumber).getWeight()
                    + ") -> ";
        pahtToString += path[path.length-1];
        return pahtToString;
    }
    
    public ArrayList<Edge> getEdgesWithoutWeight() {
        ArrayList<Edge> list = new ArrayList<>();
        for( int vertexIndex = 1; vertexIndex < path.length; vertexIndex++ )
            list.add(new Edge(path[vertexIndex-1], vertexIndex));
        return list;
    }
}
