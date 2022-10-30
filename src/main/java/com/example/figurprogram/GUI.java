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

public class GUI extends Application {
    RadioButton selectKnapp, sirkelKnapp, linjeKnapp, rektangelKnapp, tekstKnapp, flyttFrem, flyttBak;
    Button blankUtKnapp;
    TextArea info, guiFigurListe;
    Pane pane;
    MouseEvent e;
    Color valgtFarge;
    public static ColorPicker colorFill = new ColorPicker();
    public static ColorPicker colorStroke = new ColorPicker();
    KanTegnes current = new Linje();
    public double startX, startY;
    public static ArrayList figurer = new ArrayList();
    public static Slider linjeSlider, tekstSlider;
    TextField tekstFelt;
    Node node;
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

        tekstFelt = new TextField();
        tekstFelt.setPromptText("Skriv Tekst Her:");
        gridPane.add(tekstFelt, 0, 6);

        blankUtKnapp = new Button("Blank Ut");
        blankUtKnapp.setOnAction(e -> {
            slettLister();
        });
        gridPane.add(blankUtKnapp, 0,7);
        flyttFrem = new RadioButton("Flytt Frem");
        flyttFrem.setToggleGroup(tg);
        gridPane.add(flyttFrem, 0, 8);
        flyttBak = new RadioButton("Flytt bak");
        flyttBak.setToggleGroup(tg);
        gridPane.add(flyttBak, 0, 9);
        gridPane.setStyle("-fx-background-color: grey;");

        // Midten
        pane = new Pane();
        pane.setOnMousePressed(this::tegneBrettKlikk);
        pane.setOnMouseDragged(this::tegneBrettDra);
//        pane.setOnMouseClicked(e -> {
//            draTing(node, e);
//        });

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

        guiFigurListe = new TextArea();
        guiFigurListe.setPrefRowCount(8);
        guiFigurListe.setPrefColumnCount(20);
        guiFigurListe.setEditable(false);
        fPane.add(guiFigurListe, 1, 9);

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
        fPane.add(new Label("Juster Linjebredde:"),1, 6);

        linjeSlider = new Slider(0, 100, 0.5);
        linjeSlider.setShowTickMarks(true);
        linjeSlider.setShowTickLabels(true);
        linjeSlider.setMajorTickUnit(10f);
        linjeSlider.setBlockIncrement(0.1f);
        fPane.add(linjeSlider, 1, 8 );

        tekstSlider = new Slider(5, 100, 0.5);
        tekstSlider.setShowTickMarks(true);
        tekstSlider.setShowTickLabels(true);
        tekstSlider.setMajorTickUnit(10f);
        tekstSlider.setBlockIncrement(0.1f);
        fPane.add(tekstSlider, 1, 10);

        fPane.setStyle("-fx-background-color: grey;");
        borderPane.setRight(fPane);

//        pane.getChildren().forEach(this::draTing);
        // Selve vinduet
        Scene scene = new Scene(borderPane, 1400, 800);
        vindu.setTitle("Paint");
        vindu.getIcons().add(new Image("file:paintIcon.png"));
        vindu.setScene(scene);
        vindu.show();
    }
        /*
            Sjekker hvilken radiobutton som er selektert i gui, og utfører da deres oppgave utifra museklikk
        */
    public void tegneBrettKlikk(MouseEvent e) {

        if (selectKnapp.isSelected()) {
            musHentObjekt();
        }
        else if (linjeKnapp.isSelected()) {
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
        else if (flyttFrem.isSelected()) {
            flyttFrem(e, node);
        }
        else if (flyttBak.isSelected()) {
            flyttBak(e, node);
        }
    }
    public void draTing(Node node) {
        node.setOnMousePressed(event -> {
            startX = event.getSceneX();
            startY = event.getSceneY();
        });
        node.setOnMouseDragged(event -> {
            node.setLayoutX(event.getSceneX() - startX);
            node.setLayoutY(event.getSceneY() - startY);
        });
    }
    public void flyttFrem(MouseEvent e, Node node) {
        ((Node)(e.getSource())).toFront();
    }
    private void flyttBak(MouseEvent e, Node node) {
        ((Node)(e.getSource())).toFront();
    }
    public void tegneBrettDra(MouseEvent e) {
        current.dra(e);
    }
    // Oppdaterer figurboksen til høyre for gui, lister opp hver figur som er satt inn
    public void setInfo() {
        int teller = figurer.size();
        figurer.add(current);
        guiFigurListe.appendText("Figur: " + figurer.get(teller) + "\n");
    }
    public void musHentObjekt() {
        pane.setOnMouseClicked(e -> {
            System.out.println("select");
        });
    }
    public void byttFarge(ActionEvent e) {
        valgtFarge = colorFill.getValue();
        info.setText("Valgt farge: " + colorFill);
    }
    public void slettLister() {
        pane.getChildren().clear();
        guiFigurListe.clear();
    }
    public static void main(String[] args) {
        launch();
    }
}