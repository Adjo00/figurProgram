package com.example.figurprogram.Figur;

import com.example.figurprogram.GUI;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Sirkel extends Circle implements KanTegnes {

    @Override
    public String navn() {
        return "Sirkel";
    }
    public Sirkel(MouseEvent e) {
        super(e.getX(), e.getY(), 0);
        setFill(GUI.colorFill.getValue());
        setStroke(GUI.colorStroke.getValue());
        setOnMousePressed(this::treffFigur);
        setOnMouseDragged(event -> {
            if (!GUI.selectAktiv) return;
            setCenterX(event.getX());
            setCenterY(event.getY());
        });
    }
    @Override
    public void dra(MouseEvent e) {
        if (GUI.selectAktiv) return;
        setRadius(
                Math.sqrt(( (e.getX() - getCenterX()) * (e.getX() - getCenterX()))   +
                        (( e.getY() - getCenterY()) * (e.getY() - getCenterY()) )));
    }

}
