package com.example.figurprogram.Figur;

import com.example.figurprogram.GUI;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Rektangel extends Rectangle implements KanTegnes {
    double r1x = getX();
    double r1y = getY();

    //Gir info om valgt figur
    @Override
    public String navn() {
        return "Figur: " + "Rektangel" + "\n"
                + "Color: "+getFill()+"\n"
                + "Stroke: "+getStroke()+"\n"
                + "StrokeWidth: "+getStrokeWidth()+"\n"
                + "PosX: "+ getX()+"\n"
                + "PosY: "+ getY()+"\n";
    }

    public Rektangel(MouseEvent e) {
        super(e.getX(), e.getY(), 0, 0);
        setFill(GUI.colorFill.getValue());
        setStroke(GUI.colorStroke.getValue());
        setOnMousePressed(this::treffFigur);
        setOnMouseDragged(event -> {
            GUI.info.setText("Figur: " + "Rektangel" + "\n"
                    + "Color: "+ GUI.colorFill.getValue()+"\n"
                    + "Stroke: "+ GUI.colorStroke.getValue()+"\n"
                    + "StrokeWidth: "+getStrokeWidth()+"\n"
                    + "PosX: "+ getX()+"\n"
                    + "PosY: "+ getY()+"\n");
            if (!GUI.selectAktiv ) return;
            setX(event.getX() - r1x/2);
            setY(event.getY() - r1y/2);
            setOnMousePressed(ev -> {
                setFill(GUI.colorFill.getValue());
                setStroke(GUI.colorStroke.getValue());

            });
        });
    }
    public Rektangel() { super(0,0,0,0); }

    @Override
    public void dra(MouseEvent e) {
        if (GUI.selectAktiv) return;
        GUI.info.setText("Figur: " + "Rektangel" + "\n"
                + "Color: "+getFill()+"\n"
                + "Stroke: "+getStroke()+"\n"
                + "StrokeWidth: "+getStrokeWidth()+"\n"
                + "PosX: "+ getX()+"\n"
                + "PosY: "+ getY()+"\n");
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


