package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class TextResultDisplay extends UiPart<Region> {

    private static final String FXML = "TextResultDisplay.fxml";

    @FXML
    private TextArea textResultDisplay;

    public TextResultDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        textResultDisplay.setText(feedbackToUser);
    }

}
