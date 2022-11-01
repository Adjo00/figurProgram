package com.example.figurprogram.Figur;

import com.example.figurprogram.GUI;
import javafx.scene.input.MouseEvent;

public interface KanTegnes {
    void dra(MouseEvent e);

    default String navn() {
        return "ingennavn";
    }
    default void treffFigur(MouseEvent e) {
        GUI.velgFigur(this);
    }
}

