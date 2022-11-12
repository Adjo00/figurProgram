package com.example.figurprogram;

import com.example.figurprogram.Figur.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;

public class GUI extends Application {
    // Har definert og i noen tilfeller deklarert for å holde koden ryddig
    BorderPane borderPane = new BorderPane();
    GridPane gridPane = new GridPane();
    Pane pane = new Pane();
    GridPane fPane = new GridPane();
    RadioButton selectKnapp, sirkelKnapp, linjeKnapp, rektangelKnapp, tekstKnapp;
    ToggleGroup tg = new ToggleGroup();
    Button blankUtKnapp, flyttFremKnapp, flyttBakKnapp;
    public static TextArea info = new TextArea();
    TextArea guiFigurListe = new TextArea();
    TextField tekstFelt = new TextField();
    public static ColorPicker colorFill = new ColorPicker();
    public static ColorPicker colorStroke = new ColorPicker();
    KanTegnes current = new Linje();
    public static ArrayList figurer = new ArrayList();
    public static Slider linjeSlider, tekstSlider;
    public static KanTegnes valgt;
    public static Label label = new Label(""), tekstLabel;
    public static boolean selectAktiv = false;
    @Override
    public void start(Stage vindu) {
        //Venstre side
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        gridPane.setHgap(5.5);
        gridPane.setVgap(5.5);

        gridPane.add(new Label("Figurer"), 0,0);

        selectKnapp = new RadioButton("Select");
        selectKnapp.setToggleGroup(tg);
        gridPane.add(selectKnapp, 0,1);

        sirkelKnapp = new RadioButton("Sirkel");
        sirkelKnapp.setToggleGroup(tg);
        gridPane.add(sirkelKnapp, 0, 2);

        linjeKnapp = new RadioButton("Rett linje");
        linjeKnapp.setToggleGroup(tg);
        gridPane.add(linjeKnapp, 0, 3);

        rektangelKnapp = new RadioButton("Rektangel");
        rektangelKnapp.setToggleGroup(tg);
        gridPane.add(rektangelKnapp, 0, 4);

        tekstKnapp = new RadioButton("Tekst");
        tekstKnapp.setToggleGroup(tg);
        gridPane.add(tekstKnapp, 0, 5);

        tekstFelt.setPromptText("Skriv Tekst Her:");
        gridPane.add(tekstFelt, 0, 6);

        blankUtKnapp = new Button("Blank Ut");
        blankUtKnapp.setOnAction(e -> slettLister());
        gridPane.add(blankUtKnapp, 0,7);

        flyttFremKnapp = new Button("Flytt Frem");
        gridPane.add(flyttFremKnapp, 0, 8);

        flyttBakKnapp = new Button("Flytt bak");
        gridPane.add(flyttBakKnapp, 0, 9);
        gridPane.setStyle("-fx-background-color: grey;");

        // Midten
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        borderPane.setCenter(pane);
        borderPane.setLeft(gridPane);

        //Høyre side
        fPane.setPadding(new Insets(10.5, 12.5, 13.5, 14.5));

        info.setPrefRowCount(8);
        info.setPrefColumnCount(20);
        info.setEditable(false);
        fPane.add(info, 1, 1);

        guiFigurListe.setPrefRowCount(8);
        guiFigurListe.setPrefColumnCount(20);
        guiFigurListe.setEditable(false);
        fPane.add(guiFigurListe, 1, 9);

        fPane.setVgap(2);
        fPane.setHgap(5);
        fPane.add(new Label("Fargefyll"), 1,2);

        colorFill = new ColorPicker(Color.BLACK);
        fPane.add(colorFill, 1, 3);

        fPane.add(new Label("Fargestroke"), 1,4);
        fPane.add(colorStroke, 1, 5);

        fPane.add(new Label("Juster Linjebredde:"),1, 6);

        linjeSlider = new Slider(0, 100, 0.5);
        linjeSlider.setShowTickMarks(true);
        linjeSlider.setShowTickLabels(true);
        linjeSlider.setMajorTickUnit(10f);
        linjeSlider.setBlockIncrement(0.1f);
        fPane.add(linjeSlider, 1, 8 );

        tekstLabel = new Label("Størrelse tekst");

        fPane.add(tekstLabel, 1, 10);
        tekstSlider = new Slider(5, 100, 0.5);
        tekstSlider.setShowTickMarks(true);
        tekstSlider.setShowTickLabels(true);
        tekstSlider.setMajorTickUnit(10f);
        tekstSlider.setBlockIncrement(0.1f);
        fPane.add(tekstSlider, 1, 11);
        fPane.add(label, 1, 12);

        fPane.setStyle("-fx-background-color: grey;");
        borderPane.setRight(fPane);
        //Behandler klikk og dra events gjennom tegneBrettKlikk og tegneBrettDra;
        pane.setOnMousePressed(this::tegneBrettKlikk);
        pane.setOnMouseDragged(this::tegneBrettDra);

        flyttFremKnapp.setOnAction(e -> flyttFrem());
        flyttBakKnapp.setOnAction(e -> flyttBak());
        selectKnapp.setOnAction(this::radioButtonAction);
        sirkelKnapp.setOnAction(this::radioButtonAction);
        rektangelKnapp.setOnAction(this::radioButtonAction);
        tekstKnapp.setOnAction(this::radioButtonAction);
        linjeKnapp.setOnAction(this::radioButtonAction);

        // Selve vinduet, størrelse på vinduet, tittel, lagt til ikon og viser vinduet
        Scene scene = new Scene(borderPane, 1400, 800);
        vindu.setTitle("Paint");
        vindu.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/1158/1158164.png"));
        vindu.setScene(scene);
        vindu.show();
    }
    //Sjekker hvilken radiobutton som er selektert i gui, og tegner figur som velges
    public void tegneBrettKlikk(MouseEvent e) {
        if (linjeKnapp.isSelected()) {
            current = new Linje(e);
            pane.getChildren().add((Node) current);
            setInfo();
        }
        else if (rektangelKnapp.isSelected()) {
            current = new Rektangel(e);
            pane.getChildren().add((Node) current);
            setInfo();
        }
        else if (sirkelKnapp.isSelected()) {
            current = new Sirkel(e);
            pane.getChildren().add((Node) current);
            setInfo();
        }
        else if (tekstKnapp.isSelected()) {
           current = new Tekst(e, tekstFelt.getText());
           pane.getChildren().add((Node) current);
            setInfo();
        }
    }
    //Flytter frem figuren som er valgt
    public void flyttFrem() {
        if (selectAktiv) {
            int idx = figurer.indexOf(valgt);
            if (pane.getChildren().size() > 1 && idx < figurer.size() - 1) {
                Collections.swap(figurer, idx, idx + 1);
                pane.getChildren().clear();
                pane.getChildren().addAll(figurer);
            }
        }
    }
    //Flytter bak figuren som er valgt
    private void flyttBak() {
        if (selectAktiv){
            int idx = figurer.indexOf(valgt);
            if (pane.getChildren().size() > 1 && idx > 0) {
                Collections.swap(figurer, idx , idx - 1);
                pane.getChildren().clear();
                pane.getChildren().addAll(figurer);
            }
        }
    }
    // brukes i main for å behandle alt av events som går utpå å dra mus
    public void tegneBrettDra(MouseEvent e) {
        current.dra(e);
    }
    // Oppdaterer figurboksen til høyre for gui, lister opp hver figur som er satt inn
    public void setInfo() {
        int teller = figurer.size();
        figurer.add(current);
        guiFigurListe.appendText("Figur: " + figurer.get(teller) + "\n");
    }
    // Brukes for å slette liste på guiFigurListe textarea når panelet blir blanket ut
    public void slettLister() {
        pane.getChildren().clear();
        guiFigurListe.clear();
    }
    // Brukes for å selektere en figur, og for å få inn informasjon om figuren som blir trykket på.
    public static void velgFigur(KanTegnes figur) {
        valgt = figur;
        info.setText(valgt.navn());
    }
    // Her har jeg en boolean som er false ved oppstart. Hvis select ikke er false, så er select aktiv,
    // Dette ble gjort fordi hvis jeg ikke hadde denne, så ville tidligere figur som ble tegnet,
    public void radioButtonAction(ActionEvent e) {
        selectAktiv = e.getSource() == selectKnapp;
    }
    // Kjører javafx
    public static void main(String[] args) {
        launch();
    }
}