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

public class GUI extends Application {
    RadioButton selectKnapp, sirkelKnapp, linjeKnapp, rektangelKnapp, tekstKnapp;
    Button blankUtKnapp;
    TextArea info;
    Pane pane;
    MouseEvent e;
    Color valgtFarge;
    public static ColorPicker colorFill = new ColorPicker();
    public static ColorPicker colorStroke = new ColorPicker();
    KanTegnes current = new Linje();

    @Override
    public void start(Stage vindu) {

//        Color myColor = colorPicker.getValue();
        //Oppretter borderpane vindu
        BorderPane borderPane = new BorderPane();

        //Venstre side
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        gridPane.setHgap(5.5);
        gridPane.setVgap(5.5);
        ToggleGroup tg = new ToggleGroup();

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

        blankUtKnapp = new Button("Blank ut");
        blankUtKnapp.setOnAction(e -> {
            pane.getChildren().clear();
        });
        gridPane.add(blankUtKnapp, 0,6);

        gridPane.add(new Button("Flytt frem"), 0, 7);
        gridPane.add(new Button("Flytt bak"), 1, 7);
        gridPane.setStyle("-fx-background-color: grey;");

        // Midten
        pane = new Pane();
        pane.setOnMousePressed(this::tegneBrettKlikk);
        pane.setOnMouseDragged(this::tegneBrettDra);
        //pane.setStyle("-fx-background-color: grey");
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        borderPane.setCenter(pane);
        borderPane.setLeft(gridPane);

        //Høyre side
        GridPane fPane = new GridPane();
        fPane.setPadding(new Insets(10.5, 12.5, 13.5, 14.5));

        info = new TextArea();
        info.setPrefRowCount(8);
        info.setPrefColumnCount(20);
        info.setEditable(false);
        info.setText("Valgt farge: Svart");
        fPane.add(info, 1, 1);

        fPane.setVgap(2);
        fPane.setHgap(5);
        colorFill = new ColorPicker(Color.BLACK);
        colorFill.setOnAction(e -> {
            byttFarge(e);
        });
        fPane.add(new Label("Fargefyll"), 1,2);
        fPane.add(colorFill, 1, 3);
        fPane.add(new Label("Fargestroke"), 1,4);
        fPane.add(colorStroke, 1, 5);

        fPane.setStyle("-fx-background-color: green;");
        borderPane.setRight(fPane);

        // Selve vinduet
        Scene scene = new Scene(borderPane, 1400, 800);
        vindu.setTitle("Paint");
        vindu.getIcons().add(new Image("file:paintIcon.png"));
        vindu.setScene(scene);
        vindu.show();
    }
    public void tegneBrettKlikk(MouseEvent e) {
        // Actionevents og listeners
        if (selectKnapp.isSelected()) {
            System.out.print("test");
        }
        else if (linjeKnapp.isSelected()) {
            current = new Linje(e);
            pane.getChildren().add((Node) current);
        }
        else if (rektangelKnapp.isSelected()) {
            current = new Rektangel(e);
            pane.getChildren().add((Node) current);
        }
        else if (sirkelKnapp.isSelected()) {
            current = new Sirkel(e);
            pane.getChildren().add((Node) current);
        }
//        else if (tekstKnapp.isSelected()) {
//            current = new Tekst(e);
//            pane.getChildren().add((Node) current);
//        }
    }
    public void tegneBrettDra(MouseEvent e) {
        current.dra(e);
    }

    public void byttFarge(ActionEvent e) {
        valgtFarge = colorFill.getValue();
        info.setText("Valgt farge: " + colorFill);
    }
    public static void main(String[] args) {
        launch();
    }
}