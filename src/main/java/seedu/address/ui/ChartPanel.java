package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;


/**
 *  Controller for a pie chart
 */
public class ChartPanel extends UiPart<Region> {
    private static final String FXML = "ChartPanel.fxml";
    private static final ObservableList<PieChart.Data> emptyData = FXCollections.observableArrayList();
    private final Logger logger = LogsCenter.getLogger(ChartPanel.class);

    @FXML
    private CustomPieChart agePieChart;

    @FXML
    private CustomPieChart majorPieChart;

    @FXML
    private CustomPieChart sexPieChart;

    public ChartPanel() {
        super(FXML);
        agePieChart.setStartAngle(90);
        agePieChart.setClockwise(true);
        sexPieChart.setStartAngle(90);
        sexPieChart.setClockwise(true);
        majorPieChart.setStartAngle(90);
        majorPieChart.setClockwise(true);
    }

    /**
     * Update chart's data
     */
    public void updateChartPanel(ObservableMap<String, Integer> ageData, ObservableMap<String, Integer> majorData,
                                 ObservableMap<String, Integer> sexData) {
        agePieChart.clearData();
        agePieChart.updateData(ageData);

        majorPieChart.clearData();
        majorPieChart.updateData(majorData);

        sexPieChart.clearData();
        sexPieChart.updateData(sexData);
    }

    /**
     * Save charts to images
     */
    public void saveChart(String fileName, String path) {
        agePieChart.saveChart(fileName + "_age", path);

        majorPieChart.saveChart(fileName + "_major", path);

        sexPieChart.saveChart(fileName + "_sex", path);
    }
}
