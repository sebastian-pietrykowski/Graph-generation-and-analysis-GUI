/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.edu.pw.ee;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Paweł
 */

public class determinedPathsWindow {
    @FXML
    private Label pathNumberLabel;

    @FXML
    private AnchorPane pathsPane;

    public void setText( String text ) {
        this.pathNumberLabel.setText(text);
    }

    public void addLabel() {
        this.pathsPane.getChildren().add(new Label("Ala ma 2 koty"));
    }
}
