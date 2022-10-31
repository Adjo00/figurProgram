package com.example.figurprogram.Figur;

import com.example.figurprogram.GUI;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Tekst extends Text implements KanTegnes {
    public String inputTekst;
    public Tekst(MouseEvent e, String inputTekst) {
        super(e.getX(), e.getY(), inputTekst);
        this.inputTekst = inputTekst;
        setFill(GUI.colorFill.getValue());
        setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, GUI.tekstSlider.getValue()));
    }
    @Override
    public void dra(MouseEvent e) {
        setX(e.getX());
        setY(e.getY());
    }

    @Override
    public boolean treffFigur(double x, double y) {
        return false;
    }


}