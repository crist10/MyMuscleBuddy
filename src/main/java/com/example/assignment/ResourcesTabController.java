package com.example.assignment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ResourcesTabController implements Initializable {

    @FXML
    private Text text1;
    @FXML
    private Text text2;
    @FXML
    private Text text3;
    @FXML
    private Text text4;
    @FXML
    private Text text5;
    @FXML
    private Text text6;

    private String x;

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
    protected void onProgramClick() throws Exception
    {
        Main.changeScene("Bodybuilding.fxml");
    }

    @FXML
    protected void onSupplementsClick()
    {
        x = "select sdescription from supplements order by sid;";
        String [] resourceArray2 = DatabaseConnection.resourcesData(x);
        String d1= resourceArray2[0];
        String d2 = resourceArray2[1];
        String d3 = resourceArray2[2];
        String d4 = resourceArray2[3];
        String d5 =resourceArray2[4];
        String d6 = resourceArray2[5];

       text1.setText("Supplements 1: " + d1);
       text2.setText("Supplements 2: " + d2);
       text3.setText("Supplements 3: " + d3);
       text4.setText("Supplements 4: " + d4);
       text5.setText("Supplements 5: " + d5);
       text6.setText("Supplements 6: " + d6);
    }
    @FXML
    protected void onEquipmentClick()
    {
        x = "select eqdescription from equipment order by eqid;";
        String [] resourceArray2 = DatabaseConnection.resourcesData(x);
        String d1= resourceArray2[0];
        String d2 = resourceArray2[1];
        String d3 = resourceArray2[2];
        String d4 = resourceArray2[3];
        String d5 =resourceArray2[4];
        String d6 = resourceArray2[5];

        text1.setText("Equipment 1: " + d1);
        text2.setText("Equipment 2: " + d2);
        text3.setText("Equipment 3: " + d3);
        text4.setText("Equipment 4: " + d4);
        text5.setText("Equipment 5: " + d5);
        text6.setText("Equipment 6: " + d6);

    }
    @FXML
    protected void onNutritionClick()
    {
        x= "select ndescription from Nutrition order by nid;";
        String [] resourceArray2 = DatabaseConnection.resourcesData(x);
        String d1= resourceArray2[0];
        String d2 = resourceArray2[1];
        String d3 = resourceArray2[2];
        String d4 = resourceArray2[3];
        String d5 =resourceArray2[4];
        String d6 = resourceArray2[5];

        text1.setText("Diet Plan 1: " + d1);
        text2.setText("Diet Plan 2: " + d2);
        text3.setText("Diet Plan 3: " + d3);
        text4.setText("Diet Plan 4: " + d4);
        text5.setText("Diet Plan 5: " + d5);
        text6.setText("Diet Plan 6: " + d6);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onSupplementsClick();
    }
}
