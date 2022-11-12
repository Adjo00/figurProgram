package com.example.figurprogram.Figur;

import com.example.figurprogram.GUI;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Rektangel extends Rectangle implements KanTegnes {
    double rx1 = getX();
    double ry1 = getY();

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
        // Når figur lages, henter den fyllfarge, strokefarge fra colorpicker i gui.
        super(e.getX(), e.getY(), 0, 0);
        setFill(GUI.colorFill.getValue());
        setStroke(GUI.colorStroke.getValue());
        setOnMousePressed(this::treffFigur);
        setOnMouseDragged(event -> {
            GUI.info.setText("Figur: " + "Rektangel" + "\n"
                    + "Color: "+ GUI.colorFill.getValue()+"\n"
                    + "StrokeFarge: "+ GUI.colorStroke.getValue()+"\n"
                    + "StrokeBredde: "+getStrokeWidth()+"\n"
                    + "PosX: "+ getX()+"\n"
                    + "PosY: "+ getY()+"\n");
            if (!GUI.selectAktiv ) return;
            setX(event.getX() - rx1 /2);
            setY(event.getY() - ry1 /2);
            setOnMousePressed(ev -> {
                setFill(GUI.colorFill.getValue());
                setStroke(GUI.colorStroke.getValue());

            });
        });
    }
    public Rektangel() { super(0,0,0,0); }

    @Override
    public void dra(MouseEvent e) {
        // Håndterer drafunksjonen når man tegner rektangel
        if (GUI.selectAktiv) return;
        GUI.info.setText("Figur: " + "Rektangel" + "\n"
                + "Color: "+getFill()+"\n"
                + "StrokeFarge: "+getStroke()+"\n"
                + "StrokeBredde: "+getStrokeWidth()+"\n"
                + "PosX: "+ getX()+"\n"
                + "PosY: "+ getY()+"\n");
        double deltaX = e.getX() - rx1;
        double deltaY = e.getY() - ry1;
        // sjekker dersom rektangel som er teget i pane, er høyere opp enn startpunktet. (Ellers hadde det kun vært mulig å tegne nedover)
        if(deltaX < 0) {
            setX(e.getX());
            setWidth(-deltaX);
        } else {
            setX(rx1);
            setWidth(e.getX() - rx1);
        }
        if(deltaY < 0) {
            setY( e.getY() );
            setHeight(-deltaY);
        } else {
            setY(ry1);
            setHeight(e.getY() - ry1);
        }
    }
}


