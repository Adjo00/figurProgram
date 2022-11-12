package com.example.figurprogram.Figur;

import com.example.figurprogram.GUI;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Sirkel extends Circle implements KanTegnes {

    @Override
    public String navn() {
        return "Sirkel" + "\n"
                + "Stroke" + getStroke() + "\n"
                + "Fargefyll" + getFill() + "\n"
                + "Strokebredde: " + getStrokeWidth() + "\n"
                + "PosX: " + getCenterX() + "\n"
                + "PosY: " + getCenterY() + "\n"
                + "Radius: " + getRadius() + "\n"
                + "Areal: " + Math.PI * getRadius() * getRadius();
    }
    public Sirkel(MouseEvent e) {
        // Når figur lages, henter den fyllfarge, strokefarge fra colorpicker i gui.
        super(e.getX(), e.getY(), 0);
        setFill(GUI.colorFill.getValue());
        setStroke(GUI.colorStroke.getValue());
        setOnMousePressed(this::treffFigur);
        setOnMouseDragged(event -> {
            GUI.info.setText("Stroke" + getStroke() + "\n"
                    + "Fargefyll" + getFill() + "\n"
                    + "Strokebredde: " + getStrokeWidth() + "\n"
                    + "PosX: " + getCenterX() + "\n"
                    + "PosY: " + getCenterY() + "\n"
                    + "Radius: " + getRadius() + "\n"
                    + "Areal: " + Math.PI * getRadius() * getRadius());
            if (!GUI.selectAktiv) return;
            setCenterX(event.getX());
            setCenterY(event.getY());
            setOnMousePressed(ev -> {
                setFill(GUI.colorFill.getValue());
                setStroke(GUI.colorStroke.getValue());
            });
        });
    }
    @Override
    public void dra(MouseEvent e) {
        // Håndterer drafunksjonen når man tegner rektangel
        if (GUI.selectAktiv) return;
        setRadius(
                Math.sqrt(( (e.getX() - getCenterX()) * (e.getX() - getCenterX()))   +
                        (( e.getY() - getCenterY()) * (e.getY() - getCenterY()) )));
        GUI.info.setText(
                "Figur: Tekst" + "\n"
                + "Stroke" + getStroke() + "\n"
                + "Fargefyll" + getFill() + "\n"
                + "Strokebredde: " + getStrokeWidth() + "\n"
                + "PosX: " + getCenterX() + "\n"
                + "PosY: " + getCenterY() + "\n"
                + "Radius: " + getRadius() + "\n"
                + "Areal: " + Math.PI * getRadius() * getRadius());
    }
}
