package pl.edu.pw.ee.graphGraphics;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import pl.edu.pw.ee.Graph;

public class GraphPane extends GridPane {

    private Graph graph;
    private StackPane[][] cells;

    public void setCells( StackPane[][] cells ) {
        this.cells = cells;
    }

    public void setGraph( Graph graph ) { this.graph = graph; }

    public StackPane getVertexCell(int vertexNumber) {
        
        int rowInGraphNumber = vertexNumber / graph.getColumns();
        int columnInGraphNumber = vertexNumber % graph.getColumns();
        
        int rowInGridNumber = (rowInGraphNumber+1)*2-1;
        int columnInGridNumber = (columnInGraphNumber+1)*2-1;

        return cells[rowInGridNumber][columnInGridNumber];
    }

    public StackPane getEdgeCell(int vertex1Number, int vertex2Number) {
        
        int rowInGraphNumberVertex1 = vertex1Number / graph.getColumns();
        int columnInGraphNumberVertex1 = vertex1Number % graph.getColumns();
        
        int rowInGridNumberVertex1 = (rowInGraphNumberVertex1+1)*2-1;
        int columnInGridNumberVertex1 = (columnInGraphNumberVertex1+1)*2-1;

        int rowInGraphNumberVertex2 = vertex2Number / graph.getColumns();
        int columnInGraphNumberVertex2 = vertex2Number % graph.getColumns();
        
        int rowInGridNumberVertex2 = (rowInGraphNumberVertex2+1)*2-1;
        int columnInGridNumberVertex2 = (columnInGraphNumberVertex2+1)*2-1;

        int edgeRowInGridNumber = (rowInGridNumberVertex1+rowInGridNumberVertex2)/2;
        int edgeColumnInGridNumber = (columnInGridNumberVertex1+columnInGridNumberVertex2)/2;

        return cells[edgeRowInGridNumber][edgeColumnInGridNumber];
    }
}
