package com.example.figurprogram.Figur;

import com.example.figurprogram.GUI;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Rektangel extends Rectangle implements KanTegnes {


    double r1x = getX();
    double r1y = getY();

    public Rektangel(MouseEvent e) {
        super(e.getX(), e.getY(), 0, 0);
        setFill(GUI.colorFill.getValue());
        setStroke(GUI.colorStroke.getValue());
    }

    public Rektangel() { super(0,0,0,0); }

    @Override
    public void dra(MouseEvent e) {
        double deltaX = e.getX() - r1x;
        double deltaY = e.getY() - r1y;
        if(deltaX < 0) {
            setX(e.getX());
            setWidth(-deltaX);
        } else {
            setX(r1x);
            setWidth(e.getX() - r1x);
        }
        if(deltaY < 0) {
            setY( e.getY() );
            setHeight(-deltaY);
        } else {
            setY(r1y);
            setHeight(e.getY() - r1y);
        }
    }
}


