package com.example.assignment;

import javafx.fxml.FXML;

public class PurposesController {

    @FXML
    protected void onBackClick() throws Exception
    {
        Main.changeScene("Home.fxml");
    }


}
