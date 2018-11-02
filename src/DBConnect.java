import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import javafx.scene.image.Image;


public class DBConnect {
    private static String userName = "student";
    private static String password = "student";
    private XYChart.Series salesSeries;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private CategoryAxis months;
    @FXML
    private NumberAxis sales;


    public static ArrayList<Speaker> getAllSpeakers() throws SQLException {
        ArrayList<Speaker> speakers = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/COMP1011T2S1?useSSL=false",
                    userName, password);

            //2.  Create a statement object
            statement = conn.createStatement();

            //3.  create and execute the query
            rs = statement.executeQuery("SELECT * FROM speakers");

            //4.  loop over the results and add to the ArrayList
            while (rs.next()) {
                Speaker newSpeaker = new Speaker(rs.getInt("speakerID"),
                        rs.getString("productName"),
                        rs.getString("description"),
                        rs.getDouble("price"));
                newSpeaker.setImage(new Image(rs.getString("image")));

                speakers.add(newSpeaker);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (rs != null)
                rs.close();
        }

        return speakers;
    }


    public void graph(int speakerID) throws SQLException {
        salesSeries = new XYChart.Series();
        barChart.setTitle("Sales for 2018");
        months.setLabel("movie type");
        sales.setLabel("# of sales");

        ArrayList<Speaker> sales = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/COMP1011T2S1?useSSL=false",
                    userName, password);

            //2.  Create a statement object
            statement = conn.createStatement();

            //3.  create and execute the query
            rs = statement.executeQuery("SELECT * FROM sales");

            //4.  loop over the results and add to the data series
            while (rs.next()) {
                salesSeries.getData().add(new XYChart.Data(rs.getInt("speakerID"),rs.getInt("speunitsSold")));

                //add the data series to the bar chart
                barChart.getData().addAll(salesSeries);
            }

        } catch (SQLException e) {
        System.err.println(e);
    } finally {
        if (conn != null)
            conn.close();
        if (statement != null)
            statement.close();
        if (rs != null)
            rs.close();
    }

    }
}