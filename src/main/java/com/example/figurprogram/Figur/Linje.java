package com.example.figurprogram.Figur;

import com.example.figurprogram.GUI;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Linje extends Line implements KanTegnes {

    public Linje(MouseEvent e) {
        super(e.getX(), e.getY(), e.getX(), e.getY());
        setFill(GUI.colorFill.getValue());
        setStroke(GUI.colorStroke.getValue());
    }
    public Linje() {
        super(0,0,0,0);
    }
    @Override
    public void dra(MouseEvent e) {
        setEndX(e.getX());
        setEndY(e.getY());
    }
}
