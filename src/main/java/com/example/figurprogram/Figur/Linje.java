package com.example.figurprogram.Figur;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public class Linje extends Line implements KanTegnes {

    public Linje(MouseEvent e) {
        super(e.getX(), e.getY(), e.getX(), e.getY());
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
