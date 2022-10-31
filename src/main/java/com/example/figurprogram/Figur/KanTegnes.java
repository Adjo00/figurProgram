package com.example.figurprogram.Figur;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public interface KanTegnes {
    void dra(MouseEvent e);
    boolean treffFigur(double x, double y);
}

