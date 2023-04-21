package com.example.assignment;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignupController {
    @FXML
    private TextField User;
    @FXML
    private PasswordField Pass;
    @FXML
    private PasswordField Confirm;
    @FXML
    protected void onSignUpClick() throws Exception {
        String x = User.getText();
        String y = Pass.getText();
        String z = Confirm.getText();

        if(y.equals(z) && !x.isEmpty() && !y.isEmpty())  //if what user typed in password textfield matches up with confirm textfield & user field not empty & password not empty
        {
        DatabaseConnection.signUp(x, y);
        Main.changeScene("Login.fxml");
        }
        else if(x.isEmpty() || y.isEmpty() || z.isEmpty()) //if any field is empty
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty fields detected");
            alert.setContentText("Please fill every field.");
            alert.showAndWait();
        }
        else //if y does not match up to z
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Matchup");
            alert.setContentText("Your password must match up in both fields.");
            alert.showAndWait();
        }
    }
    @FXML
    protected void onBackClick() throws Exception
    {
        Main.changeScene("Login.fxml");
    }
}
