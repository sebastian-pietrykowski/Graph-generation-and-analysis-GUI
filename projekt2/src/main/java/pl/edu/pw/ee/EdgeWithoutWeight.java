package pl.edu.pw.ee;

public class EdgeWithoutWeight {
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + fromVertex;
        result = prime * result + toVertex;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EdgeWithoutWeight other = (EdgeWithoutWeight) obj;
        if (fromVertex != other.fromVertex)
            return false;
        if (toVertex != other.toVertex)
            return false;
        return true;
    }
    private int fromVertex;
    private int toVertex;

    public EdgeWithoutWeight(int fromVertex, int toVertex ) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
    }

    public int getFromVertex() { return fromVertex; }
    public int getToVertex() { return toVertex; }

}
