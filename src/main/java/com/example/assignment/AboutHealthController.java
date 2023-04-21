package com.example.assignment;
import javafx.fxml.FXML;
public class AboutHealthController
{
    @FXML
    public void onBackClick() throws Exception
    {
        Main.changeScene("Health.fxml");
    }
}
