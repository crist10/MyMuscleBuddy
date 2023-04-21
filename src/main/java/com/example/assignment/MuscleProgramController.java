package com.example.assignment;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
public class MuscleProgramController implements Initializable {

HealthTabController user = new HealthTabController();

@FXML
private Label level;

@FXML
private Label goal;

@FXML
private Label displayAge;

@FXML
private Label ex1;

    @FXML
    private Label ex2;
    @FXML
    private Label ex3;
    @FXML
    private Label ex4;
    @FXML
    private Label ex5;
    @FXML
    private Label ex6;
    @FXML
    private Label ex7;

    @FXML
    private Label ex8;
    @FXML
    private Label ex9;
    @FXML
    private Label ex10;
    @FXML
    private Label ex11;
    @FXML
    private Label ex12;
    @FXML
    private Label ex13;

    @FXML
    private Label ex14;
    @FXML
    private Label ex15;
    @FXML
    private Label ex16;
    @FXML
    private Label ex17;
    @FXML
    private Label ex18;

    @FXML
    private Label des1;
    @FXML
    private Label des2;
    @FXML
    private Label des3;
    @FXML
    private Label des4;
    @FXML
    private Label des5;
    @FXML
    private Label des6;
    @FXML
    private Label des7;
    @FXML
    private Label des8;
    @FXML
    private Label des9;
    @FXML
    private Label des10;
    @FXML
    private Label des11;
    @FXML
    private Label des12;
    @FXML
    private Label des13;
    @FXML
    private Label des14;
    @FXML
    private Label des15;
    @FXML
    private Label des16;
    @FXML
    private Label des17;
    @FXML
    private Label des18;

    private final int[] stats = DatabaseConnection.retrieveUserHealth();

    private final int age= stats[2];


@FXML
protected void onBackClick() throws Exception {
    Main.stg.setHeight(430);
    Main.stg.setWidth(610);
    Main.changeScene("Bodybuilding.fxml");
}


public void displayUserInfo(){
    displayAge.setText("Age: "+age);

    if(user.experience==1){
        level.setText("Difficulty: Beginner");
    }
    else if(user.experience ==2){
        level.setText("Difficulty: Intermediate");
    }
    else if(user.experience ==3){
        level.setText("Difficulty: Advanced");
    }
    if(user.goal == 1){
        goal.setText("Lose Weight");
    }
    else if(user.goal == 2){
        goal.setText("Build Muscle");
    }
    else if(user.goal == 3){
        goal.setText("Build Strength");
    }
}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    displayUserInfo();
        String[] Push = DatabaseConnection.retrieveSavedExercises();
        ex1.setText(Push[0]);
        des1.setText(Push[1]);
        ex2.setText(Push[2]);
        des2.setText(Push[3]);
        ex3.setText(Push[4]);
        des3.setText(Push[5]);
        ex4.setText(Push[6]);
        des4.setText(Push[7]);
        ex5.setText(Push[8]);
        des5.setText(Push[9]);
        ex6.setText(Push[10]);
        des6.setText(Push[11]);
        ex7.setText(Push[12]);
        des7.setText(Push[13]);
        ex8.setText(Push[14]);
        des8.setText(Push[15]);
        ex9.setText(Push[16]);
        des9.setText(Push[17]);
        ex10.setText(Push[18]);
        des10.setText(Push[19]);
        ex11.setText(Push[20]);
        des11.setText(Push[21]);
        ex12.setText(Push[22]);
        des12.setText(Push[23]);
        ex13.setText(Push[24]);
        des13.setText(Push[25]);
        ex14.setText(Push[26]);
        des14.setText(Push[27]);
        ex15.setText(Push[28]);
        des15.setText(Push[29]);
        ex16.setText(Push[30]);
        des16.setText(Push[31]);
        ex17.setText(Push[32]);
        des17.setText(Push[33]);
        ex18.setText(Push[34]);
        des18.setText(Push[35]);
    }
}
