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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application {
    RadioButton sirkelKnapp, linjeKnapp, rektangelKnapp, tekstKnapp;
    Button blankUtKnapp;
    TextArea info;
    Pane pane;
    MouseEvent e;
    Color valgtFarge;
    ColorPicker colorPicker;
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

        sirkelKnapp = new RadioButton("Sirkel");
        sirkelKnapp.setToggleGroup(tg);
        gridPane.add(sirkelKnapp, 0, 1);

        linjeKnapp = new RadioButton("Rett linje");
        linjeKnapp.setToggleGroup(tg);
        gridPane.add(linjeKnapp, 0, 2);

        rektangelKnapp = new RadioButton("Rektangel");
        rektangelKnapp.setToggleGroup(tg);
        gridPane.add(rektangelKnapp, 0, 3);

        tekstKnapp = new RadioButton("Tekst");
        tekstKnapp.setToggleGroup(tg);
        gridPane.add(tekstKnapp, 0, 4);

        blankUtKnapp = new Button("Blank ut");
        blankUtKnapp.setOnAction(e -> {
            pane.getChildren().clear();
        });
        gridPane.add(blankUtKnapp, 0,5);


        gridPane.add(new Button("Flytt frem"), 0, 6);
        gridPane.add(new Button("Flytt bak"), 1, 6);
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
        colorPicker = new ColorPicker(Color.BLACK);
        colorPicker.setOnAction(e -> {
            byttFarge(e);
        });
        fPane.add(colorPicker, 1, 4);


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
        if (linjeKnapp.isSelected()) {
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
        else if (tekstKnapp.isSelected()) {
            current = new Tekst(e);
            pane.getChildren().add((Node) current);
        }
    }
    public void tegneBrettDra(MouseEvent e) {
        current.dra(e);
    }

    public void byttFarge(ActionEvent e) {
        valgtFarge = colorPicker.getValue();
        info.setText("Valgt farge: " + valgtFarge);
    }
    public static void main(String[] args) {
        launch();
    }
}