package com.example.figurprogram.Figur;

import com.example.figurprogram.GUI;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public class Linje extends Line implements KanTegnes {
    @Override
    public String navn() {
        return "Linje";
    }
    public Linje(MouseEvent e) {
        super(e.getX(), e.getY(), e.getX(), e.getY());
        setStrokeWidth(GUI.linjeSlider.getValue());
        setStroke(GUI.colorFill.getValue());
        setOnMousePressed(this::treffFigur);
        setOnMouseDragged(event -> {
            if (!GUI.selectAktiv) return;
            setEndX(event.getX());
            setEndY(event.getY());
            setOnMousePressed(ev -> {
                setFill(GUI.colorFill.getValue());
                setStroke(GUI.colorStroke.getValue());
            });
        });
    }
    public Linje() {
        super(0,0,0,0);
    }
    @Override
    public void dra(MouseEvent e) {
        if (GUI.selectAktiv) return;
        setEndX(e.getX());
        setEndY(e.getY());
    }
}
