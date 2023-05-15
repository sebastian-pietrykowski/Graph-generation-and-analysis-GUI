package pl.edu.pw.ee;

public class Edge extends EdgeWithoutWeight {

    private double weight;

    public Edge(int fromVertex, int toVertex) {
        super( fromVertex, toVertex );
    }

    public Edge(int fromVertex, int toVertex, double weight) {
        super( fromVertex, toVertex );
        this.weight = weight;
    }

    public int getFromVertex() { return super.getFromVertex(); }
    public int getToVertex() { return super.getToVertex(); }
    public void setWeight( double weight ) {
        this.weight = weight;
    }
    public double getWeight() { return weight; }
}
