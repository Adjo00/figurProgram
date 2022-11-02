package com.example.figurprogram.Figur;

import com.example.figurprogram.GUI;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Sirkel extends Circle implements KanTegnes {

    @Override
    public String navn() {
        return "Sirkel" + "\n"
                + "Stroke" + getStroke() + "\n"
                + "ColorFill" + getFill() + "\n"
                + "StrokeWidth: " + getStrokeWidth() + "\n"
                + "PosX: " + getCenterX() + "\n"
                + "PosY: " + getCenterY() + "\n"
                + "Radius: " + getRadius() + "\n"
                + "Area: " + Math.PI * getRadius() * getRadius();
    }
    public Sirkel(MouseEvent e) {
        super(e.getX(), e.getY(), 0);
        setFill(GUI.colorFill.getValue());
        setStroke(GUI.colorStroke.getValue());
        setOnMousePressed(this::treffFigur);
        setOnMouseDragged(event -> {
            GUI.info.setText("Stroke" + getStroke() + "\n"
                    + "ColorFill" + getFill() + "\n"
                    + "StrokeWidth: " + getStrokeWidth() + "\n"
                    + "PosX: " + getCenterX() + "\n"
                    + "PosY: " + getCenterY() + "\n"
                    + "Radius: " + getRadius() + "\n"
                    + "Area: " + Math.PI * getRadius() * getRadius());
            if (!GUI.selectAktiv) return;
            setCenterX(event.getX());
            setCenterY(event.getY());
            setOnMousePressed(ev -> {
                setFill(GUI.colorFill.getValue());
                setStroke(GUI.colorStroke.getValue());
            });
            navn();
        });
    }
    @Override
    public void dra(MouseEvent e) {
        if (GUI.selectAktiv) return;
        setRadius(
                Math.sqrt(( (e.getX() - getCenterX()) * (e.getX() - getCenterX()))   +
                        (( e.getY() - getCenterY()) * (e.getY() - getCenterY()) )));
        GUI.info.setText("Stroke" + getStroke() + "\n"
                + "ColorFill" + getFill() + "\n"
                + "StrokeWidth: " + getStrokeWidth() + "\n"
                + "PosX: " + getCenterX() + "\n"
                + "PosY: " + getCenterY() + "\n"
                + "Radius: " + getRadius() + "\n"
                + "Areal: " + Math.PI * getRadius() * getRadius());
    }
}
