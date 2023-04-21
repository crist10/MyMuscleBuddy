package com.example.assignment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.shape.Ellipse;

import java.net.URL;
import java.util.ResourceBundle;

public class HealthTabController implements Initializable {
    @FXML
    private Label output; //BMI, BMR, Calorie Intake values displayed
    @FXML
    private Label warning; //a message that pops up if the user has not completed the questionnaires
    @FXML
    private Label displayHeight;
    @FXML
    private Label displayWeight;
    @FXML
    private Label displayAge;
    @FXML
    private Label displaySex;
    @FXML
    private Label displayActivity;
    @FXML
    private Ellipse scale;

    int[] userGoalExp = DatabaseConnection.retrieveUserGoal();
    final int goal = userGoalExp[0];
    final int experience = userGoalExp[1]; //index1 holds experience value

    final int[] bmiArray = DatabaseConnection.retrieveUserHealth(); //holds height, weight, age, sex, activity in an array of int
    final double height = bmiArray[0]; // we intitialized a value for double height with the value of index 0 which is an integer value
    final int weight = bmiArray[1];
    final int age = bmiArray[2];
    final int sex = bmiArray[3]; //sex 1 is male sex 2 is female
    final int activity = bmiArray[4]; //activity 1 = none 2 = light 3 = moderate 4 = very active
    double activityMultiplier = 0;
    final double BMI = Math.round(weight / (height * height) * 703); //will round BMI decimals to nearest whole number
    @FXML
    protected void onHomeClick() throws Exception
    {
        Main.changeScene("Home.fxml");
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

    @FXML
    protected void onAboutHealthClick() throws Exception
    {
        Main.changeScene("AboutHealth.fxml");
    }
    public void displayValues()
    {
        double heightRoundedDown1 = height / 12; //5.6
        int heightRoundedDown2 = (int) Math.floor(height/12); //5
        int heightInchesValue = (int) ((heightRoundedDown1 - heightRoundedDown2) * 12); //the (int) will cast our double variable to an int variable to prevent a showing of a value like 5.0 instead of just 5

        displayHeight.setText("Height: " + heightRoundedDown2 + "' " + heightInchesValue + "'' ");
        displayWeight.setText("Weight: " + weight);
        displayAge.setText("Age: " + age);

        if (sex == 1)
        {
            displaySex.setText("Sex: Male");
        }
        else if (sex == 2)
        {
            displaySex.setText("Sex: Female");
            scale.setStyle("-fx-fill: pink");
        }

        if (activity == 1)
        {
            displayActivity.setText("No Activity");
            activityMultiplier = 1.2;
        }
        else if (activity == 2)
        {
            displayActivity.setText("Light Activity");
            activityMultiplier = 1.375;
        }
        else if (activity == 3)
        {
            displayActivity.setText("Moderately Active");
            activityMultiplier = 1.55;
        }
        else if (activity == 4)
        {
            displayActivity.setText("Very Active");
            activityMultiplier = 1.725;
        }
        else
        {
            displaySex.setText("Sex: null");
            displayActivity.setText("Activity: null");
        }
    }
    @FXML
    public void onBMIClick()
    {
        output.setText(String.valueOf(BMI)); //BMI is a double. As such, we will get the string value of BMI to display it to user
        output.setLayoutX(400);
    }
    @FXML
    public void onBMRClick()
    {
        if(sex == 1)
        {
            output.setText(Math.round((4.536 * weight) + (15.88 * height) - (5 * age) + 5) + " Calories");  //mifflin st jeor equation
        }
        else if (sex == 2)
        {
            output.setText(Math.round((4.536 * weight) + (15.88 * height) - (5 * age) - 161) + " Calories");
        }
        output.setLayoutX(275);
    }
    @FXML
    public void onTDEEClick()
    {
        if(sex == 1)
        {
            output.setText(Math.round(  ( (4.536 * weight) + (15.88 * height) - (5 * age) + 5) * activityMultiplier) + " Calories");  //mifflin st jeor equation
        }
        else if (sex == 2)
        {
            output.setText(Math.round(  ( (4.536 * weight) + (15.88 * height) - (5 * age) - 161)  * activityMultiplier) + " Calories");
        }
        output.setLayoutX(275);
    }

    public void BMIScale()
    {
        if(BMI <= 18)
        {
            scale.setLayoutX(310);
        }
        else if (BMI >= 19 & BMI < 25)
        {
            scale.setLayoutX(390);
        }
        else if (BMI >= 25 & BMI < 30)
        {
            scale.setLayoutX(460);
        }
        else if (BMI >= 30)
        {
            scale.setLayoutX(540);
        }
    }
    public void checkQuestionnaireCompletion() //health tab is personalized so this method checks to make sure user did the questionnaire
    {
        if(BMI == 0) //only true if the user did not complete the questionnaires
        {
            Alert alert = new Alert(Alert.AlertType.ERROR); //alert is object of alert class and alert type specifies the kind of alert
            alert.setTitle("MISSING QUESTIONNAIRES"); //title seen in alert
            alert.setContentText("Please complete the questionnaires found on the home tab in order to allow full functionality of the health tab"); //message shown in alert
            alert.showAndWait(); //meaning alert object is displayed and awaits user interaction
        }
        else
        {
            warning.setText(""); //makes the warning label invisible
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) //initialize allows methods to run automatically without user input
    {
        checkQuestionnaireCompletion();
        onBMIClick();
        displayValues();
        BMIScale();
    }
}