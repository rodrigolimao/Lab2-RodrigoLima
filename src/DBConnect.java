import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import javafx.scene.image.Image;


public class DBConnect {
    private static String userName = "student";
    private static String password = "student";
    private XYChart.Series movieSeriesMale;
    @FXML
    private BarChart<?, ?> barChart;


    public static ArrayList<Speaker> getAllSpeakers() throws SQLException {
        ArrayList<Speaker> speakers = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;

        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/COMP1011T2S1?useSSL=false",
                    userName, password);

            //2.  Create a statement object
            statement = conn.createStatement();

            //3.  create and execute the query
            rs = statement.executeQuery("SELECT * FROM speakers");

            //4.  loop over the results and add to the ArrayList
            while (rs.next())
            {
                Speaker newSpeaker = new Speaker(rs.getInt("speakerID"),
                        rs.getString("productName"),
                       rs.getString("description"),
                        rs.getDouble("price"));
                        newSpeaker.setImage(new Image(rs.getString("image")));

                speakers.add(newSpeaker);
            }


        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (rs != null)
                rs.close();
        }

        return speakers;
    }


//    public void graph(int speakerID) {
//        movieSeriesMale = new XYChart.Series();
//        barChart.setTitle("Sales for 2018");
//        movies.setLabel("movie type");
//        students.setLabel("# of students");
//
//        //add data to the the data series
//        movieSeriesMale.getData().add(new XYChart.Data("Comedy", 10));
//        movieSeriesMale.getData().add(new XYChart.Data("Action", 15));
//        movieSeriesMale.getData().add(new XYChart.Data("Romance", 5));
//        movieSeriesMale.getData().add(new XYChart.Data("Thriller", 9));
//        movieSeriesMale.setName("Male");
//
//        //add the data series to the bar chart
//        barChart.getData().addAll(movieSeriesMale);
//
//    }

    }