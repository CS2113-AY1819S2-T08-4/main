package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Redoable Command}.
 */
public class RedoCard extends UiPart<Region> {

    private static final String FXML = "RedoListCard.fxml";

    @FXML
    private HBox redoCardPane;
    @FXML
    private Label redoableCommand;
    @FXML
    private Label id;

    public RedoCard(String redoableCommand, int displayedIndex) {
        super(FXML);
        id.setText(displayedIndex + ". ");
        this.redoableCommand.setText(redoableCommand);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RedoCard)) {
            return false;
        }

        // state check
        RedoCard card = (RedoCard) other;
        return id.getText().equals(card.id.getText())
                && redoableCommand.equals(card.redoableCommand);
    }
}
