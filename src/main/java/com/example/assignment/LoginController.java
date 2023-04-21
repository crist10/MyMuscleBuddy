package com.example.assignment;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class LoginController {
    @FXML
    private TextField User; //private to protect value
    @FXML
    private PasswordField Password; //same here

    @FXML
    protected void onLoginClick() throws Exception {
        String x = User.getText(); //string x value defined as the username input text value
        String y = Password.getText(); //string y value defined as password input text
        if(DatabaseConnection.loginCheck(x,y)) //loginCheck will return a boolean value, and if true, then it means username and password matched database values
        {
            Main.changeScene("Home.fxml"); //calls changeScene method, sets Home.fxml to root and gets scene
        }
        else //if either user or password do not match up a popup appears
        {
            Alert alert = new Alert(Alert.AlertType.ERROR); //alert is object of alert class and alert type specifies the kind of alert
            alert.setTitle("Login Failed"); //title seen in alert
            alert.setContentText("Incorrect Username and/or Password"); //message shown in alert
            alert.showAndWait(); //meaning alert object is displayed and awaits user interaction
        }
    }
    @FXML
    protected void onSignUpClick() throws Exception {
        Main.changeScene("SignUp.fxml");
    }
}
