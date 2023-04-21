package com.example.assignment;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class QuestionnaireController {
    @FXML
    private TextField Feet;
    @FXML
    private TextField Inches;
    @FXML
    private TextField Weight;
    @FXML
    private TextField Age;
    @FXML
    private RadioButton Male;
    @FXML
    private RadioButton Female;
    @FXML
    ToggleGroup sexButtons = new ToggleGroup();
    @FXML
    private RadioButton Exercise1;
    @FXML
    private RadioButton Exercise2;
    @FXML
    private RadioButton Exercise3;
    @FXML
    private RadioButton Exercise4;
    @FXML
    ToggleGroup exerciseButtons = new ToggleGroup();
    @FXML
    private RadioButton exp1;
    @FXML
    private RadioButton exp2;
    @FXML
    private RadioButton exp3;
    @FXML
    ToggleGroup experienceButtons = new ToggleGroup();

    @FXML
    private RadioButton goal1;
    @FXML
    private RadioButton goal2;
    @FXML
    private RadioButton goal3;
    @FXML
    private ToggleGroup goalButtons = new ToggleGroup();
    private int height;
    private int weight;
    private int age;
    private int sex = 0; //set to zero meaning the user did not select something but will be overriden by selection values
    private int activity = 0;
    private int experience = 0;
    private int goal = 0;

    @FXML
    protected void onNextClick()
    {
        try {
    if(Exercise1.isSelected())
    {
        activity = 1; //sedentary activity
    }
    else if(Exercise2.isSelected())
    {
        activity = 2; //light activity
    }
    else if(Exercise3.isSelected())
    {
        activity = 3; //moderate activity
    }
    else if(Exercise4.isSelected())
    {
        activity = 4; //very active
    }

    if(Male.isSelected())
    {
        sex = 1; //sex 1 is male
    }
    else if(Female.isSelected())
    {
        sex = 2; //sex 2 is female
    }

    if(goal1.isSelected())
    {
        goal = 1;
    }
    else if(goal2.isSelected())
    {
        goal = 2;
    }
    else if(goal3.isSelected())
    {
        goal = 3;
    }

    if (exp1.isSelected())
    {
        experience = 1;
    }
    else if (exp2.isSelected())
    {
        experience = 2;
    }
    else if(exp3.isSelected())
    {
        experience = 3;
    }

        if(fieldNumCheck(Feet.getText(), 10) && fieldNumCheck(Inches.getText(), 11) && fieldNumCheck(Weight.getText(), 1000) && fieldNumCheck(Age.getText(), 100) & activity > 0 & sex > 0 & goal > 0 & experience > 0) //if the fields contain only numbers and the user selected things
        {
            height = Integer.parseInt(Feet.getText()) * 12 + Integer.parseInt(Inches.getText()); //parseInt because we are getting the text as a string and therefore must convert it to its int value. height stored as inches in DB.
            weight = Integer.parseInt(Weight.getText());
            age = Integer.parseInt(Age.getText());

            DatabaseConnection.healthQuestions(height, weight, age, sex, activity);
            DatabaseConnection.goalQuestions(goal, experience);
            Main.changeScene("Home.fxml");
        }
        else if(Feet.getText().isEmpty() || Inches.getText().isEmpty() || Weight.getText().isEmpty() || Age.getText().isEmpty() || activity == 0 || sex == 0 || goal == 0 || experience == 0) //if some user info is missing
            {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("MISSING INPUTS");
            alert.setContentText("Please ensure that you have given values for every field displayed");
            alert.showAndWait();
            }
        else //only other possibility is that the user typed in letters instead of just numbers because fieldNumCheck did not evaluate to true one or more variables.
            {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("INCORRECT VALUES FOUND");
            alert.setContentText("Please ensure you are only using numerical values in each of the text fields");
            alert.showAndWait();
            }
        }
        catch(NumberFormatException e) //if user selected activity and sex but did not type anything into the number fields
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("EMPTY NUMBER FIELDS");
            alert.setContentText("Please provide numerical values for the number fields");
            alert.showAndWait();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    protected Boolean fieldNumCheck(String x, int y) //returns true if the string parameter only consists of numbers and if it is shorter than the int parameter. Example: Inches must be less than or equal to 11
    {
        for(int i = 0; i < x.length(); i++)
        {
            if(!Character.isDigit(x.charAt(i)) || Integer.parseInt(x) > y)
                {
                return false;
                }
        }
        return true;
    }
    @FXML
    protected void onBackClick() throws Exception
    {
        Main.changeScene("Home.fxml");
    }
}
