package com.example.assignment;
import javafx.scene.control.Alert;
import java.sql.*; //allows all sql functionalities
import java.time.LocalDate;

public class DatabaseConnection {

    private static Connection databaseLink;
    final static String databaseName = "Fitness"; //name of database accessed
    final static String databaseUser = "root"; //name of database user
    final static String databasePassword = "xxxx"; //password of database user
    final static String url = "jdbc:mysql://localhost/" + databaseName; //path guiding to database location
    private static int uID;
    private static int pID; //static so the variable holds this value upon reference where it may be
    private static PreparedStatement SQLquery; //this object will be used to hold our SQL Queries
    private static ResultSet result;

    public static Connection getConnection() //will allow connection to our database
    {
        try //method to be done as part of try and catch
        {
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword); //DriverManager manages Java Database Drivers
        } catch (Exception e) //catching possible exceptions
        {
            e.printStackTrace(); //will print a log of the exception
        }
        return databaseLink; //returns databaseLink which is an object of DatabaseConnection class
    }

    public static void signUp(String x, String y) //takes two string values as parameters when called
    {
        try {
            databaseLink = getConnection(); //calls getConnection method which returns a Connection value
            SQLquery = databaseLink.prepareStatement("insert into Users (email, password) values (?, ?)"); //our query
            SQLquery.setString(1, x); //first parameter i.e. first question mark
            SQLquery.setString(2, y);
            SQLquery.executeUpdate(); //execute the query
        } catch (SQLIntegrityConstraintViolationException e) //if the username already exists we get this exception
        {
            Alert alert = new Alert(Alert.AlertType.ERROR); //alert is object of alert class and alert type specifies the kind of alert
            alert.setTitle("Account name already exists"); //title seen in alert
            alert.setContentText("This username already belongs to an existing user. Please choose a different username."); //message shown in alert
            alert.showAndWait(); //meaning alert object is displayed and awaits user interaction
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (databaseLink != null) {
                try {
                    databaseLink.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean loginCheck(String x, String y) //loginCheck will take two string values (email, password) when querying
    {
        try {
            databaseLink = getConnection(); //provides access to the database through the previously defined getConnection method seen above
            SQLquery = databaseLink.prepareStatement("SELECT uID, email, password FROM users WHERE email = ? AND BINARY password = ?"); //issues statement made while connected to database
            SQLquery.setString(1, x); //parameter 1 (email) is set as String X
            SQLquery.setString(2, y); //paremeter 2 (password) is set as String y, set as binary to ensure case sensitivity
            result = SQLquery.executeQuery(); //result will hold the value of the query's execution
            if (result.isBeforeFirst()) //true if the cursor is before the first row; false if the cursor is at any other position or the result set contains no rows
            {
                result.next();
                uID = result.getInt(1);
                return true; //this above if statement is saying if the isBeforeFirst is not true (meaning info not in database) then return false
            }
        } catch (SQLException e) { //SQL can cause exceptions and as such must be tried and caught
            e.printStackTrace(); //prints a log of what, where, and why exception occurred
        } finally {
            if (databaseLink != null) {
                try {
                    databaseLink.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false; //if true then credentials matched up with database values
    }

    /*Methods for questionnaire screen*/
    public static void healthQuestions(int ht, int wt, int ag, int sx, int ac) {
        try {
            databaseLink = getConnection();
            SQLquery = databaseLink.prepareStatement("insert into HealthQuestionnaire(uID, height, weight, age, sex, activity) values (?, ?, ?, ?, ?, ?)");
            SQLquery.setInt(1, uID);
            SQLquery.setInt(2, ht);
            SQLquery.setInt(3, wt);
            SQLquery.setInt(4, ag);
            SQLquery.setInt(5, sx);
            SQLquery.setInt(6, ac);
            SQLquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (databaseLink != null) {
                try {
                    databaseLink.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void goalQuestions(int x, int y) //two integer values as parameters when this method is called
    {
        try {
            databaseLink = getConnection();
            SQLquery = databaseLink.prepareStatement("insert into GoalQuestionnaire (uID, goal, experience) values (?,?,?)");
            SQLquery.setInt(1, uID); //this calls the getuID method from our Controller1 class which returns the uID of the user that is logged in
            SQLquery.setInt(2, x);
            SQLquery.setInt(3, y);
            SQLquery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (databaseLink != null) {
                try {
                    databaseLink.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*Methods for Home Tab*/

    public static String[] retrieveMusclePickerExercises(int x) {
        String[] musclePickerExercises = new String[4];
        try {
            databaseLink = getConnection();
            SQLquery = databaseLink.prepareStatement("Select eName from Exercises where categoryMuscle = " + x + " order by rand() limit 4");
            result = SQLquery.executeQuery();
            result.next();
            musclePickerExercises[0] = result.getString(1);
            result.next();
            musclePickerExercises[1] = result.getString(1);
            result.next();
            musclePickerExercises[2] = result.getString(1);
            result.next();
            musclePickerExercises[3] = result.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (databaseLink != null) {
                try {
                    databaseLink.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return musclePickerExercises;
    }


    /*Methods for Health Tab*/
    public static int[] retrieveUserHealth() //returns an integer array when called
    {
        int[] bmiArray = new int[5]; //array of type integer with a size of 3
        try {
            databaseLink = getConnection(); //calls getConnection method
            SQLquery = databaseLink.prepareStatement("Select Height, Weight, Age, Sex, Activity, healthID from HealthQuestionnaire where uID = " + uID + " order by healthID DESC"); //our query
            result = SQLquery.executeQuery(); //our tabular data value is what we get from our query's execution
            result.next(); //moves cursor to the first row
            bmiArray[0] = result.getInt(1); //first element is equal to height since that's the first column we encounter
            bmiArray[1] = result.getInt(2); //second element is equal to weight
            bmiArray[2] = result.getInt(3); //third element is equal to age
            bmiArray[3] = result.getInt(4); //fourth element is equal to sex
            bmiArray[4] = result.getInt(5); //fifth element is equal to activity
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (databaseLink != null) {
                try {
                    databaseLink.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return bmiArray; //returns our array
    }

    public static int[] retrieveUserGoal() //called in HealthTabController class
    {
        int[] questionnaireValueArray = new int[2];
        try {
            databaseLink = getConnection();
            SQLquery = databaseLink.prepareStatement("Select goal, experience from GoalQuestionnaire where uID = " + uID + " order by goalID DESC");
            result = SQLquery.executeQuery();
            result.next();
            questionnaireValueArray[0] = result.getInt(1);
            questionnaireValueArray[1] = result.getInt(2);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (databaseLink != null) {
                try {
                    databaseLink.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return questionnaireValueArray;
    }


        public static void assignProgram()
        {
            if(!programChecker())   //if the user is in the same category AND if it has not been two months since the user started their program
            {
                Alert alert = new Alert(Alert.AlertType.ERROR); //alert is object of alert class and alert type specifies the kind of alert
                alert.setTitle("Program ERROR"); //title seen in alert
                alert.setContentText("Please either select a new goal and experience or wait the remaining time of your program's duration."); //message shown in alert
                alert.showAndWait(); //meaning alert object is displayed and awaits user interaction
            }
            else {
                try {
                    int[] goalAndExpArray = retrieveUserGoal();
                    int goal = goalAndExpArray[0];
                    int experience = goalAndExpArray[1];
                    int category = 0;

                    if(goal == 1 && experience == 1)
                    {
                        category = 1;
                    }
                    else if (goal == 1 && experience == 2)
                    {
                        category = 2;
                    }
                    else if (goal == 1 && experience == 3)
                    {
                        category = 3;
                    }
                    else if (goal == 2 && experience == 1)
                    {
                        category = 4;
                    }
                    else if (goal == 2 && experience == 2)
                    {
                        category = 5;
                    }
                    else if (goal == 2 && experience == 3)
                    {
                        category = 6;
                    }
                    else if (goal == 3 && experience == 1)
                    {
                        category = 7;
                    }
                    else if (goal == 3 && experience == 2)
                    {
                        category = 8;
                    }
                    else if (goal == 3 && experience == 3)
                    {
                        category = 9;
                    }

                    Date currentDate = Date.valueOf(LocalDate.now()); //current date
                    Date futureDate = Date.valueOf(LocalDate.now().plusMonths(2)); //2 months from our current date

                    databaseLink = getConnection();
                    SQLquery = databaseLink.prepareStatement("insert into Program (uID, catID, startDate, endDate) values (?, ?, ?, ?)");
                    SQLquery.setInt(1, uID); //this calls the getuID method from our Controller1 class which returns the uID of the user that is logged in
                    SQLquery.setInt(2, category);
                    SQLquery.setDate(3, currentDate);
                    SQLquery.setDate(4, futureDate);
                    SQLquery.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        public static Boolean programChecker() //will not allow assigning program if the output is false meaning it has not been 2 months since user
        {
            try {
                int[] goalAndExpArray = retrieveUserGoal();
                databaseLink = getConnection();
                SQLquery = databaseLink.prepareStatement("select endDate, catID from program where uID = " + uID + " ORDER BY pID DESC");
                result = SQLquery.executeQuery();
                if(!result.next())
                {
                   return true;
                }
                else {
                    Date currentDate = Date.valueOf(LocalDate.now());
                    Date endDate = result.getDate(1);
                    int catID = result.getInt(2);
                    if (catID == goalAndExpArray[0] * goalAndExpArray[1] && currentDate.before(endDate))
                    {
                        return false;
                    }
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return true;
        }

    public static String[] retrieveSavedExercises()
    {
        pID = getpID();
        databaseLink = getConnection();
        String[] savedExercisesArray = new String[36];
        try
        {
            PreparedStatement retrieveProgramQuery;
            ResultSet result;
            retrieveProgramQuery = databaseLink.prepareStatement("select eName, eDescription from category_exercises join exercises on category_exercises.eID = exercises.eID join category on category_exercises.catID = category.catID join program on category.catID = program.catID where program.pID = " + pID);
            result = retrieveProgramQuery.executeQuery();
            result.next();
            for(int i = 0; i < savedExercisesArray.length - 1; i++)
            {
                savedExercisesArray[i] = result.getString(1);
                savedExercisesArray[i + 1] = result.getString(2);
                result.next();
                i++; //prevent us from overwriting the iteration
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();

        }
        finally {
            if (databaseLink != null) {
                try {
                    databaseLink.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return savedExercisesArray;
    }

    public static int getpID() //called when you assign a program and when you retrieve exercises because a user will not always generate a program on the screen but will always view program
    {
            try
            {
                databaseLink = getConnection();
                SQLquery = databaseLink.prepareStatement("select pID from program where uID = " + uID + " ORDER BY pID DESC");
                result = SQLquery.executeQuery();
                result.next();
                pID = result.getInt(1);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        return pID;
    }

//Method for resources tab
public static String[] resourcesData(String x) {
    databaseLink = getConnection();
    String[] resourceArray2 = new String[6]; //array of type integer with a size of 6
    try {
        PreparedStatement retrieveResourcesQuery;
        ResultSet result;
        retrieveResourcesQuery = databaseLink.prepareStatement(x); //our query
        result = retrieveResourcesQuery.executeQuery(); //our tabular data value is what we get from our query's execution
        result.next(); //moves cursor to the first row
        for (int i = 0; i < 6; i++) {
            resourceArray2[i] = result.getString(1);
            result.next();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (databaseLink != null) {
            try {
                databaseLink.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return resourceArray2; //returns our array
}
    }
