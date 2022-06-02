/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.edu.pw.ee;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author Paweł
 */

public class determinedPathsWindow {
    @FXML
    private Label pathNumberLabel;

    public void setText( String text ) {
        this.pathNumberLabel.setText(text);
    }
}
