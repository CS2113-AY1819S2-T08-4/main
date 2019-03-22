package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the list of undoable command
 */
public class UndoListPanel extends UiPart<Region> {
    private static String FXML = "UndoListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(UndoListPanel.class);

    @FXML
    private ListView<String> undoListView;

    public UndoListPanel(ObservableList<String> undoList) {
        super(FXML);
        undoListView.setItems(undoList);
        undoListView.setCellFactory(listView -> new UndoListViewCell());
        //undoListView.getSelectionModel().getSelectedItems().addListener(observable -> {updateUndoList(undoList);});
    }

    public void updateUndoList(ObservableList<String> undoList) {
        undoListView.setItems(undoList);
        undoListView.setCellFactory(listView -> new UndoListViewCell());
    }

    class UndoListViewCell extends ListCell<String> {
        @Override
        protected void updateItem(String newUndoableCommand, boolean empty) {
            super.updateItem(newUndoableCommand, empty);

            if (empty || newUndoableCommand == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new UndoCard(newUndoableCommand, getIndex() + 1).getRoot());
            }
        }
    }
}
