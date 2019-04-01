package seedu.address.ui;

import java.io.File;
import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.image.WritableImage;

/**
 * Custom Pie Chart class
 */
public class CustomPieChart extends PieChart {
    public CustomPieChart() {
        super();
    }

    /**
     * Save chart to image
     * @param fileName
     * @param path
     */
    @FXML
    public void saveChart (String fileName, String path) {
        changeStyleSheet("DarkTheme", "Print");
        WritableImage image = this.snapshot(new SnapshotParameters(), null);
        changeStyleSheet("Print", "DarkTheme");

        File file = new File(path + "\\" + fileName + ".png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update chart data
     */
    public void updateData (ObservableMap<String, Integer> data) {
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();
        for (String key : data.keySet()) {
            chartData.add(new PieChart.Data(key, data.get(key)));
        }
        this.setData(chartData);
    }

    /**
     * Clear chart data
     */
    public void clearData() {
        this.getData().clear();
    }

    /**
     * Change styleSheet
     */
    public void changeStyleSheet (String target, String replacement) {
        String styleSheet;

        styleSheet = this.getScene().getStylesheets().get(0).replace(target, replacement);
        this.getScene().getStylesheets().remove(0);
        this.getScene().getStylesheets().add(0, styleSheet);
    }
}
