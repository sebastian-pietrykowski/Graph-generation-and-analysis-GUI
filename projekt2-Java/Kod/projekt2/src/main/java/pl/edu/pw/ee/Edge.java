package pl.edu.pw.ee;

public class Edge {

    private int fromVertex;
    private int toVertex;
    private double weight;

    public Edge(int fromVertex, int toVertex ) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
    }
    public Edge(int fromVertex, int toVertex, double weight) {
        this( fromVertex, toVertex );
        this.weight = weight;
    }

    public int getFromVertex() { return fromVertex; }
    public int getToVertex() { return toVertex; }
    public void setWeight( double weight ) {

    }

    public double getWeight() { return weight; }
}
