import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SpeakerViewController implements Initializable {

    @FXML
    private ComboBox<Speaker> speakerComboBox;

    @FXML
    private Label productLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private NumberAxis numberAxis;

    @FXML
    private CategoryAxis categoryAxis;

    private XYChart.Series sales;

    Speaker activeSpeaker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            speakerComboBox.getItems().addAll(DBConnect.getAllSpeakers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void comboBoxWasUpdated()
    {
        this.descriptionLabel.setText("Speaker selected: \n" + speakerComboBox.getValue().toString());
        this.imageView.setImage(speakerComboBox.getValue().getImage());
    }

}
