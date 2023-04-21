package com.example.assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HealthProgramController implements Initializable {
    @FXML
    private ImageView banner;
    @FXML
    private AnchorPane sceneBackground;
    @FXML
    private AnchorPane scrollPaneBackground;
    @FXML
    private Label pageSex;
    @FXML
    private Label difficulty;
    @FXML
    private Label dailyTDEE;
    @FXML
    private Label weeklyTDEE;
    @FXML
    private Label dailyCalories;
    @FXML
    private Label weeklyCalories; //calories to lose the amount of weight
    @FXML
    private Label weightLoss; //how much to be lost per week
    @FXML
    private Label caloriesBurned;
    @FXML
    private Label exercise1;
    @FXML
    private Label exercise2;
    @FXML
    private Label exercise3;
    @FXML
    private Label exercise4;
    @FXML
    private Label exercise5;
    @FXML
    private Label exercise6;
    @FXML
    private Label exercise7;
    @FXML
    private Label exercise8;
    @FXML
    private Label exercise9;
    @FXML
    private Label exercise10;
    @FXML
    private Label exercise11;
    @FXML
    private Label exercise12;
    @FXML
    private Label exercise13;
    @FXML
    private Label exercise14;
    @FXML
    private Label exercise15;
    @FXML
    private Label exercise16;
    @FXML
    private Label exercise17;
    @FXML
    private Label exercise18;
    @FXML
    private Label desc1;
    @FXML
    private Label desc2;
    @FXML
    private Label desc3;
    @FXML
    private Label desc4;
    @FXML
    private Label desc5;
    @FXML
    private Label desc6;
    @FXML
    private Label desc7;
    @FXML
    private Label desc8;
    @FXML
    private Label desc9;
    @FXML
    private Label desc10;
    @FXML
    private Label desc11;
    @FXML
    private Label desc12;
    @FXML
    private Label desc13;
    @FXML
    private Label desc14;
    @FXML
    private Label desc15;
    @FXML
    private Label desc16;
    @FXML
    private Label desc17;
    @FXML
    private Label desc18;








    @FXML
    private TextArea about; //program description
    @FXML
    private TextField workoutDuration;
    @FXML
    private ComboBox exerciseComboBox;
    @FXML
    private Rectangle exercisesHeader;
    @FXML
    private Rectangle aboutHeader;
    @FXML
    private Rectangle pageHeader;

    private double activityMultiplier; //Multiplier based on user activity level
    private double TDEE; //Total Daily Energy Expenditure

    HealthTabController user = new HealthTabController(); //this will let us access the user values from HealthTabController which are provided through DC retrieveUserHealth method
    double height = user.height;
    int weight = user.weight;
    int age = user.age;
    int sex = user.sex;
    int activity = user.activity;
    int experience = user.experience;

    @FXML
    private void onBackClick() throws Exception
    {
        Main.stg.setHeight(430);
        Main.stg.setWidth(610);
        Main.changeScene("Bodybuilding.fxml");
    }

    @FXML
    private void setUp()
    {
        ObservableList<String> exercises = FXCollections.observableArrayList("Walking < 2.0 mph", "Speed Walking 3.5 mph", "Running 5 mph", "Weightlifting", "Cycling 10 - 11.9 mph", "Swimming", "Hiking (hills w/ 10 - 20 lb load)", "Tennis", "Pilates", "Jump Rope", "Mountain Climbers", "Yoga");
        exerciseComboBox.setItems(exercises);

        if(experience == 2) //if user chose intermediate
        {
            difficulty.setText("Intermediate");
        }
        else if (experience == 3) //if user chose advanced
        {
            difficulty.setText("Advanced");
        }

        if(activity == 1) //sedentary
        {
            activityMultiplier = 1.2;
        }
        else if(activity == 2) //lightly active
        {
            activityMultiplier = 1.375;
        }
        else if (activity == 3) //moderately active
        {
            activityMultiplier = 1.55;
        }
        else if (activity == 4) //very active
        {
            activityMultiplier = 1.725;
        }

        if(sex == 1)
        {
            TDEE = Math.round(((4.536 * weight) + (15.88 * height) - (5 * age) + 5) * activityMultiplier); //male formula mifflin st jeor
        }
        else if(sex == 2)
        {
            TDEE = Math.round(((4.536 * weight) + (15.88 * height) - (5 * age) - 161) * activityMultiplier); //female formula
            Image bannerImage = new Image(getClass().getResourceAsStream("Banner2.png"));
            banner.setImage(bannerImage);
            pageHeader.setStyle("-fx-fill: black;");
            exercisesHeader.setStyle("-fx-fill: black;");
            aboutHeader.setStyle("-fx-fill: black");
            sceneBackground.setStyle("-fx-background-color: pink;");
            about.setStyle("-fx-border-color: black");
            scrollPaneBackground.setStyle("-fx-border-color: black");
            pageSex.setText("Women's Weight Loss");
        }

        dailyTDEE.setText(String.valueOf(TDEE));
        weeklyTDEE.setText(String.valueOf(TDEE * 7));
        dailyCalories.setText(String.valueOf(TDEE - 125 - (125 * experience)));
        weeklyCalories.setText(String.valueOf((TDEE * 7) - 875 - (875 * experience))); //1750 calories equivalent is 0.5 lbs
        weightLoss.setText(0.25 + (0.25 * experience) + " Lbs a week");

    }
    @FXML
    private void calculateCaloriesBurned()
    {
        try {
            double MET = 0; //MET value of exercise, we assign it as 0 to initialize it and override it during the switch statement
            int duration = Integer.parseInt(workoutDuration.getText()); //duration of exercise
            double result; //result

            switch (exerciseComboBox.getValue().toString()) {
                case "Walking < 2.0 mph":
                    MET = 2.0;
                    break;
                case "Speed Walking 3.5 mph":
                    MET = 4.5;
                    break;
                case "Running 5 mph":
                    MET = 8.0;
                    break;
                case "Weightlifting":
                    MET = 6.0;
                    break;
                case "Cycling 10 - 11.9 mph":
                    MET = 8.0;
                    break;
                case "Swimming":
                    MET = 7.0;
                    break;
                case "Hiking (hills w/ 10 - 20 lb load)":
                    MET = 7.3;
                    break;
                case "Tennis":
                    MET = 7.0;
                    break;
                case "Pilates":
                    MET = 3.8;
                    break;
                case "Jump Rope":
                    MET = 12.3;
                    break;
                case "Mountain Climbers":
                    MET = 6.1;
                    break;
                case "Yoga":
                    MET = 2.0;
                    break;
            }
            result = (duration * MET * user.weight) / 200;
            caloriesBurned.setText(String.valueOf(Math.round(result)));
        }
        catch (Exception e) //if user did not select a workout or did not type in duration, this will cause an exception so we show them an alert
        {
            Alert alert = new Alert(Alert.AlertType.ERROR); //alert is object of alert class and alert type specifies the kind of alert
            alert.setTitle("Empty Duration Text Field"); //title seen in alert
            alert.setContentText("Please fill in workout duration"); //message shown in alert
            alert.showAndWait(); //meaning alert object is displayed and awaits user interaction
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        setUp();
        String[] Exercises;
        Exercises = DatabaseConnection.retrieveSavedExercises();

        exercise1.setText(Exercises[0]);
        desc1.setText(Exercises[1]);
        exercise2.setText(Exercises[2]);
        desc2.setText(Exercises[3]);
        exercise3.setText(Exercises[4]);
        desc3.setText(Exercises[5]);
        exercise4.setText(Exercises[6]);
        desc4.setText(Exercises[7]);
        exercise5.setText(Exercises[8]);
        desc5.setText(Exercises[9]);
        exercise6.setText(Exercises[10]);
        desc6.setText(Exercises[11]);
        exercise7.setText(Exercises[12]);
        desc7.setText(Exercises[13]);
        exercise8.setText(Exercises[14]);
        desc8.setText(Exercises[15]);
        exercise9.setText(Exercises[16]);
        desc9.setText(Exercises[17]);
        exercise10.setText(Exercises[18]);
        desc10.setText(Exercises[19]);
        exercise11.setText(Exercises[20]);
        desc11.setText(Exercises[21]);
        exercise12.setText(Exercises[22]);
        desc12.setText(Exercises[23]);
        exercise13.setText(Exercises[24]);
        desc13.setText(Exercises[25]);
        exercise14.setText(Exercises[26]);
        desc14.setText(Exercises[27]);
        exercise15.setText(Exercises[28]);
        desc15.setText(Exercises[29]);
        exercise16.setText(Exercises[30]);
        desc16.setText(Exercises[31]);
        exercise17.setText(Exercises[32]);
        desc17.setText(Exercises[33]);
        exercise18.setText(Exercises[34]);
        desc18.setText(Exercises[35]);
    }
}