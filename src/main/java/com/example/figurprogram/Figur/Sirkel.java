package com.example.figurprogram.Figur;

import com.example.figurprogram.GUI;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Sirkel extends Circle implements KanTegnes {

    public Sirkel(MouseEvent e) {
        super(e.getX(), e.getY(), 0);
        setFill(GUI.colorFill.getValue());
        setStroke(GUI.colorStroke.getValue());
    }
    @Override
    public void dra(MouseEvent e) {
        setRadius(
                Math.sqrt(( (e.getX() - getCenterX()) * (e.getX() - getCenterX()))   +
                        (( e.getY() - getCenterY()) * (e.getY() - getCenterY()) )));
    }

    @Override
    public boolean treffFigur(double x, double y) {

        return false;
    }
}
