package pl.edu.pw.ee.graphGraphics;

import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class ArrowWeightColorPicker {
    
    private double maxWeight;
    private LinearGradient linearGradient;
    private WritableImage gradientSnapshot;

    public ArrowWeightColorPicker() {
        createGradient();
    }

    public ArrowWeightColorPicker( double maxWeight ) {

        this.maxWeight = maxWeight;
        createGradient();
    }

    private void createGradient() {

        Pane gradientPane = new Pane();
        Stop[] stops = new Stop[] {
                    new Stop(0, Color.PURPLE),
                    new Stop(1/6., Color.BLUE),
                    new Stop(2/6., Color.CYAN),
                    new Stop(3/6., Color.LIME),
                    new Stop(4/6., Color.YELLOW),
                    new Stop(5/6., Color.DARKORANGE),
                    new Stop(1, Color.RED) };
        linearGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

        Rectangle background = new Rectangle(101, 1);
        background.setFill(linearGradient);
        gradientPane.getChildren().add(background);
  
        Scene tmpScene = new Scene(gradientPane);
        gradientSnapshot = tmpScene.snapshot(null);
    }

    public Color determineArrowColor( double weight ) {
        int weightFactor = (int) (weight/maxWeight *100);
        Color weightColor = gradientSnapshot.getPixelReader().getColor(weightFactor, 0);
        return weightColor;
    }

    public LinearGradient getGradient() { return linearGradient; }
}
