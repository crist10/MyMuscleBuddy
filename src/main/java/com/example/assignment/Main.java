package com.example.assignment;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application { //main class will receive functionality of Application class which manages the use of applications
    static Stage stg; //declared static because it will cause a null pointer/invocation exception if not static due to not being able to reference it
    //a nonstatic variable being referenced in a static context will cause an exception due to ambiguity.
    @Override //child class method overrides the base class method
    public void start(Stage primaryStage) throws IOException { //used to declare possible input/output exceptions
        stg = primaryStage; //the stage to be used for scene setting and showing
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml")); //root will hold Login.fxml file
        //objects of Parent are used as Parent class is responsible for graph operations -- such as adding and switching nodes which are treated as children

        primaryStage.setScene(new Scene(root)); //sets scene with content stored in root
        primaryStage.setTitle("MyMuscleBuddy"); //title of scene
        primaryStage.show(); //shows the scene
        primaryStage.setResizable(false); //user cannot full screen nor resize the application window
    }

    public static void changeScene(String fxml) throws Exception //exceptions must be tried/caught or declared to be thrown, FXML is string because files will be read in a string format as seen above
    {
        Parent pane = FXMLLoader.load(Main.class.getResource(fxml)); //pane will hold any file that FXMLLoader acquires
        stg.getScene().setRoot(pane); //stage will get the scene and set the root to that of which pane contains (new file)
    }


    public static void main(String[] args) {
        launch(args);
    } //launches application
}