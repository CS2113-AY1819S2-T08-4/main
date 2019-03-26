package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the list of redoable command
 */
public class RedoListPanel extends UiPart<Region> {
    private static final String FXML = "RedoListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RedoListPanel.class);

    @FXML
    private ListView<String> redoListView;

    public RedoListPanel(ObservableList<String> redoList) {
        super(FXML);
        redoListView.setItems(redoList);
        redoListView.setCellFactory(listView -> new RedoListViewCell());
        //redoListView.getSelectionModel().getSelectedItems().addListener(observable -> {updateRedoList(redoList);});
    }

    /** Update the redo list after it changes */
    public void updateRedoList(ObservableList<String> redoList) {
        redoListView.setItems(redoList);
        redoListView.setCellFactory(listView -> new RedoListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Redoable Command} using a {@code RedoCard}.
     */
    class RedoListViewCell extends ListCell<String> {
        @Override
        protected void updateItem(String newRedoableCommand, boolean empty) {
            super.updateItem(newRedoableCommand, empty);

            if (empty || newRedoableCommand == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RedoCard(newRedoableCommand, getIndex() + 1).getRoot());
            }
        }
    }
}
