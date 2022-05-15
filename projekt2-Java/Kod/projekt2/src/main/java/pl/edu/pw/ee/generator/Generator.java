package pl.edu.pw.ee.generator;

import java.util.Random;

import pl.edu.pw.ee.Graph;

public abstract class Generator {
    private Graph graph;
    private int columns;
    private int rows;
    private double fromWeight;
    private double toWeight;

    public Generator( int columns, int rows, double fromWeight, double toWeight ) {

        if( fromWeight < 0 || toWeight < 0 )
            throw new IllegalArgumentException("Minimalna waga lub maksymalna waga mniejsza od 0:");
        if( toWeight < fromWeight )
            throw new IllegalArgumentException("Minimalna waga jest większa niż maksymalna waga.");

        this.columns = columns;
        this.rows = rows;
        this.fromWeight = fromWeight;
        this.toWeight = toWeight;
    }

    protected Graph getGraph() { return graph; }
    protected int getColumns() { return columns; }
    protected int getRows() { return rows; }
    protected double getFromWeight() { return fromWeight; }
    protected double getToWeight() { return toWeight; }

    /**
     * Generates graph.
     * @return generated graph
     */
    public abstract Graph generate();

    /**
     * Generates random <code>Double</code> in range of (<code>fromWeight</code>,<code>toWeight</code>).
     * @return generated number
     */
    protected double generateEdgeWeight() {
        Random random = new Random();
        return fromWeight + random.nextDouble() * (toWeight - fromWeight) + Double.MIN_VALUE;
    }
}
