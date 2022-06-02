package pl.edu.pw.ee.pathsOnGraph;

public class PathOnGraphInfo {

    private PathOnGraph pathOnGraph;
    private int pathNumber;
    private boolean visibilityOnGraph;
    private boolean weightVisibilityOnGraph;
    private boolean markedToDelete;
    
    public PathOnGraphInfo( PathOnGraph pathOnGraph) {
        this.pathOnGraph = pathOnGraph;
        this.visibilityOnGraph = true;
        this.weightVisibilityOnGraph = false;
        this.markedToDelete = false;
    }

    public PathOnGraph getPathOnGraph() {
        return pathOnGraph;
    }

    public void setPathNumber( int pathNumber ) {
        this.pathNumber = pathNumber;
    }

    public int getPathNumber() {
        return pathNumber;
    }

    public boolean getVisibilityOnGraph() {
        return visibilityOnGraph;
    }

    public void setVisibilityOnGraph(boolean visibilityOnGraph) {
        this.visibilityOnGraph = visibilityOnGraph;
    }

    public boolean getWeightVisibilityOnGraph() {
        return weightVisibilityOnGraph;
    }

    public void setWeightVisibilityOnGraph(boolean weightVisibilityOnGraph) {
        this.weightVisibilityOnGraph = weightVisibilityOnGraph;
    }

    public boolean isMarkedToDelete() {
        return markedToDelete;
    }

    public void setMarkedToDelete(boolean markedToDelete) {
        this.markedToDelete = markedToDelete;
    }

    
}
