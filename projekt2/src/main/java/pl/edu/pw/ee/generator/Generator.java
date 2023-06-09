package pl.edu.pw.ee.generator;

import java.util.Random;

import pl.edu.pw.ee.Graph;

public abstract class Generator {
    private Graph graph;
    private int columns;
    private int rows;
    private double fromWeight;
    private double toWeight;
    boolean success = true; // true if no exceptions were thrown

    public Generator( int columns, int rows, double fromWeight, double toWeight ) throws IllegalArgumentException {

        if( fromWeight < 0 || toWeight < 0 ) {
            success = false;
            throw new IllegalArgumentException("Minimalna waga lub maksymalna waga mniejsza od 0:");
        }
        if( toWeight < fromWeight ) {
            success = false;
            throw new IllegalArgumentException("Minimalna waga jest większa niż maksymalna waga.");
        }
        if( columns <= 0 || rows <= 0 ) {
            success = false;
            throw new IllegalArgumentException("Liczba kolumn lub rzędów mniejsza lub równa 0");
        }

        this.columns = columns;
        this.rows = rows;
        this.fromWeight = fromWeight;
        this.toWeight = toWeight;
    }

    public Graph getGraph() { return graph; }
    public int getColumns() { return columns; }
    public int getRows() { return rows; }
    public double getFromWeight() { return fromWeight; }
    public double getToWeight() { return toWeight; }

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
