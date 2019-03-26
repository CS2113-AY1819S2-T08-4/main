package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Undoable Command}.
 */
public class UndoCard extends UiPart<Region> {

    private static final String FXML = "UndoListCard.fxml";

    @FXML
    private HBox undoCardPane;
    @FXML
    private Label undoableCommand;
    @FXML
    private Label id;

    public UndoCard(String undoableCommand, int displayedIndex) {
        super(FXML);
        id.setText(displayedIndex + ". ");
        this.undoableCommand.setText(undoableCommand);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UndoCard)) {
            return false;
        }

        // state check
        UndoCard card = (UndoCard) other;
        return id.getText().equals(card.id.getText())
                && undoableCommand.equals(card.undoableCommand);
    }
}
