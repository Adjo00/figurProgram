package com.example.figurprogram.Figur;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Rektangel extends Rectangle implements KanTegnes {
    double minusX;
    double minusY;

    public Rektangel(MouseEvent e) {
        super(e.getX(), e.getY(), 0, 0);
    }

    public Rektangel() { super(0,0,0,0); }

    @Override
    public void dra(MouseEvent e) {
        setWidth(e.getX() - getX());
        setHeight(e.getY() - getY());
    }
}
