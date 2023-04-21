package com.example.assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private ImageView musclePicker;
    @FXML
    private ComboBox musclePickerComboBox;
    @FXML
    private Label muscle;
    @FXML
    private Label exercise1;
    @FXML
    private Label exercise2;
    @FXML
    private Label exercise3;
    @FXML
    private Label exercise4;

    Image chest = new Image(getClass().getResourceAsStream("chest.jpg"));
    Image biceps = new Image(getClass().getResourceAsStream("biceps.jpg"));
    Image legs = new Image(getClass().getResourceAsStream("legs.jpg"));


    @FXML
    protected void onQuestionnaireClick() throws Exception
    {
        Main.changeScene("Questionnaire.fxml"); //calls changeScene method and applies Login.fxml to the root
    }
    @FXML
    protected void onPurposesClick() throws Exception
    {
        Main.changeScene("Purposes.fxml");
    }

    @FXML
    protected void onHealthClick() throws Exception
    {
        Main.changeScene("Health.fxml");
    }

    @FXML
    protected void onProgramClick() throws Exception
    {
        Main.changeScene("Bodybuilding.fxml");
    }

    @FXML
    protected void onResourcesClick() throws Exception
    {
        Main.changeScene("Resources.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> muscles = FXCollections.observableArrayList("Chest", "Biceps", "Legs");
        musclePickerComboBox.setItems(muscles);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                int x = 0;
                muscle.setText(String.valueOf(musclePickerComboBox.getValue()));
                if(musclePickerComboBox.getValue() == "Chest")
                {
                    x = 1; //categoryMuscle 1 is Chest
                    musclePicker.setImage(chest);
                    //add the setImage thing here
                }
                else if(musclePickerComboBox.getValue() == "Biceps")
                {
                    x = 4; //categoryMuscle 4 is Biceps
                    musclePicker.setImage(biceps);
                }
                else if(musclePickerComboBox.getValue() == "Legs")
                {
                    x = 6; //categoryMuscle 6 is Legs
                    musclePicker.setImage(legs);
                }

                String[] musclePickerExercises = DatabaseConnection.retrieveMusclePickerExercises(x);
                exercise1.setText(musclePickerExercises[0]);
                exercise2.setText(musclePickerExercises[1]);
                exercise3.setText(musclePickerExercises[2]);
                exercise4.setText(musclePickerExercises[3]);
            }
        };
        musclePickerComboBox.setOnAction(event);
    }
}