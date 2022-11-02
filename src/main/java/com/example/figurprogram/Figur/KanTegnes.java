package com.example.figurprogram.Figur;

import com.example.figurprogram.GUI;
import javafx.scene.input.MouseEvent;

// Interface, eller metoder som brukes i alle figurklassene som bruker "implements KanTegnes"
// Alle klasser som implementerer dette, må ha slike metoder
public interface KanTegnes {
    // Tar seg av drafunksjonen
    void dra(MouseEvent e);
// brukes for å hente ut informasjon om figuren som klikkes på
    default String navn() {
        return "ingennavn";
    }
    // brukes når en figur blir trykket på
    default void treffFigur(MouseEvent e) {
        GUI.velgFigur(this);
    }
}

