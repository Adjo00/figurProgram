package com.example.figurprogram.Figur;

import com.example.figurprogram.GUI;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public class Linje extends Line implements KanTegnes {
    //Gir info om valgt figur
    @Override
    public String navn() {
        return    "Figur: Linje" + "\n"
                + "Fargefyll: " + GUI.colorFill.getValue() + "\n"
                + "StrokeBredde: "+getStrokeWidth()+"\n"
                + "StartPosX: "+getStartX()+"\n"
                + "StartPosY: "+getStartY()+"\n"
                + "EndPosX: " + getEndX()+"\n"
                + "EndPosY: " + getEndY()+"\n";
    }
    public Linje(MouseEvent e) {
        // Når figur lages, henter den strokefarge fra colorpicker i gui og strokewith fra slider
        super(e.getX(), e.getY(), e.getX(), e.getY());
        setStrokeWidth(GUI.linjeSlider.getValue());
        setStroke(GUI.colorFill.getValue());
        setOnMousePressed(this::treffFigur);
        setOnMouseDragged(event -> {
            GUI.info.setText("Figur: " + "Linje" + "\n"
                    + "Fargefyll" + GUI.colorFill.getValue() + "\n"
                    + "Strokebredde: "+getStrokeWidth()+"\n"
                    + "StartPosX: "+getStartX()+"\n"
                    + "StartPosY: "+getStartY()+"\n"
                    + "EndPosX: " + getEndX()+"\n"
                    + "EndPosY: " + getEndY()+"\n");
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
        // Håndterer drafunksjonen når man tegner rektangel
        if (GUI.selectAktiv) return;
        setEndX(e.getX());
        setEndY(e.getY());
        GUI.info.setText("Figur: Linje" + "\n"
                + "Fargefyll" + GUI.colorFill.getValue() + "\n"
                + "StrokeBredde: "+getStrokeWidth()+"\n"
                + "StartPosX: "+getStartX()+"\n"
                + "StartPosY: "+getStartY()+"\n"
                + "EndPosX: " + getEndX()+"\n"
                + "EndPosY: " + getEndY()+"\n");
    }
}
