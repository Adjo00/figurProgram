package com.example.figurprogram.Figur;

import com.example.figurprogram.GUI;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Tekst extends Text implements KanTegnes {
    public String inputTekst;
    @Override
    public String navn() {
        return "Tekst";
    }
    public Tekst(MouseEvent e, String inputTekst) {
        super(e.getX(), e.getY(), inputTekst);
        this.inputTekst = inputTekst;
        setFill(GUI.colorFill.getValue());
        setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, GUI.tekstSlider.getValue()));
        setOnMousePressed(this::treffFigur);
        setOnMouseDragged(event -> {
            if (!GUI.selectAktiv) return;
            setX(event.getX());
            setY(event.getY());
            setOnMousePressed(ev -> {
                setFill(GUI.colorFill.getValue());
                setStroke(GUI.colorStroke.getValue());
            });
        });
    }
    @Override
    public void dra(MouseEvent e) {
        if (GUI.selectAktiv) return;
        setX(e.getX());
        setY(e.getY());
    }
}