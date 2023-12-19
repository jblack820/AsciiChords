package com.example.asciichords;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private ComboBox<String> box1;
    @FXML
    private ComboBox<String> box2;
    @FXML
    private ComboBox<String> box3;
    @FXML
    private ComboBox<String> box4;
    @FXML
    private ComboBox<String> box5;
    @FXML
    private ComboBox<String> box6;
    @FXML
    private TextArea ta;

    private final List<ComboBox<String>> boxList = new ArrayList<>();
    private final List<String> possibleBoxValues = new ArrayList<>();

    @FXML
    public void initialize() {
        setupPossibleBoxValues();
        createBoxList();
        setupBoxes();
    }

    private void createBoxList() {
        boxList.addAll(List.of(box1, box2, box3, box4, box5, box6));
    }

    private void setupPossibleBoxValues() {
        possibleBoxValues.add("X");
        for (int i = 0; i <= 24; i++) {
            possibleBoxValues.add(String.valueOf(i));
        }
    }

    private void setupBoxes() {
        boxList.forEach(box -> box.setItems(FXCollections.observableList(possibleBoxValues)));
    }

    @FXML
    public void buttonClicked(){
        if (isAllBoxesFilled()){
            List<String> textListFromBoxes = getTextList();
            String chordName = ChordIdentification.getChordName(textListFromBoxes);
            String ASCCII_Chord = ChordFactory.getChord(textListFromBoxes);
            ta.setText(ASCCII_Chord + "\n\n" + chordName);
        } else {
            ta.setText("Not all strings are defined... \nPlease select an option for every string!");
        }
    }

    private boolean isAllBoxesFilled() {
        for (ComboBox<String> box : boxList){
            if (box.getValue() == null || box.getValue().equals("")){
                return false;
            }
        }
        return true;
    }

    private List<String> getTextList() {
        List<String> list = new ArrayList<>();
        boxList.forEach(box -> list.add(box.getValue()));
        return list;
    }


}