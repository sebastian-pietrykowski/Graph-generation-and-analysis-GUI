package pl.edu.pw.ee;

public class Edge {

    int from;
    int to;
    double weight;

    public Edge(int from, int to ) {
        this.from = from;
        this.to = to;
    }
    public Edge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public void setWeight( double weight ) {

    }

    public double getWeight() { return weight; }
}
