package com.example.figurprogram.Figur;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Sirkel extends Circle implements KanTegnes {

    public Sirkel(MouseEvent e) {
        super(e.getX(), e.getY(), 0);
    }
    @Override
    public void dra(MouseEvent e) {
        setRadius(
                Math.sqrt(( (e.getX() - getCenterX()) * (e.getX() - getCenterX()) )   +
                        ( (e.getY() - getCenterY()) * (e.getY() - getCenterY()) ) ) );
    }
}
