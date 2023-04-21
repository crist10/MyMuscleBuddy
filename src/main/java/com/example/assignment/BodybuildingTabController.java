package com.example.assignment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class BodybuildingTabController implements Initializable
{
    HealthTabController user = new HealthTabController(); //this will let us access the user values from HealthTabController which are provided through DC retrieveUserHealth method

    @FXML
    private Label label = new Label();
    @FXML
    private Label warning = new Label();
    @FXML
    private Button viewClick = new Button();
    @FXML
    private Button generateClick = new Button();

    @FXML
    protected void onHomeClick() throws Exception
    {
        Main.changeScene("Home.fxml");
    }

    @FXML
    protected void onHealthClick() throws Exception
    {
        Main.changeScene("Health.fxml");
    }

    @FXML
    protected void onResourcesClick() throws Exception
    {
        Main.changeScene("Resources.fxml");
    }

    @FXML
    protected void onViewClick() throws Exception
    {
        if(user.goal == 1)
        {
            if(user.BMI > 18)
            {
                Main.stg.setHeight(700);
                Main.stg.setWidth(815);
                Main.changeScene("HealthProgram.fxml");
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR); //alert is object of alert class and alert type specifies the kind of alert
                alert.setTitle("Underweight BMI"); //title seen in alert
                alert.setContentText("Underweight (BMI < 18) cannot view weight loss programs. Please redo the questionnaire and select another Goal."); //message shown in alert
                alert.showAndWait(); //meaning alert object is displayed and awaits user interaction
            }
        }
        else if(user.goal==2) {
            Main.stg.setHeight(700);
            Main.stg.setWidth(900);
            Main.changeScene("MuscleProgram.fxml");
        }
        else if(user.goal==3) {
            Main.stg.setHeight(700);
            Main.stg.setWidth(900);
            Main.changeScene("MuscleProgram.fxml");
        }
    }
    @FXML
    protected void onGenerateClick()
    {
       DatabaseConnection.assignProgram();
        label.setText("Your Program Has Been Generated!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if(user.BMI == 0) //only true if the user did not complete the questionnaires
        {
            Alert alert = new Alert(Alert.AlertType.ERROR); //alert is object of alert class and alert type specifies the kind of alert
            alert.setTitle("MISSING QUESTIONNAIRES"); //title seen in alert
            alert.setContentText("Please complete the questionnaires found on the home tab in order to allow full functionality of the program tab"); //message shown in alert
            alert.showAndWait(); //meaning alert object is displayed and awaits user interaction
            viewClick.setManaged(false);
            generateClick.setManaged(false);
        }
        else {
            warning.setText(""); //makes the warning label invisible
        }
    }
}
