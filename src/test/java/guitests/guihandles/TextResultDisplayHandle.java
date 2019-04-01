package guitests.guihandles;

import javafx.scene.control.TextArea;

/**
 * A handler for the {@code TextResultDisplay} of the UI
 */
public class TextResultDisplayHandle extends NodeHandle<TextArea> {

    public static final String TEXT_RESULT_DISPLAY = "#textResultDisplay";

    public TextResultDisplayHandle(TextArea textResultDisplayNode) {
        super(textResultDisplayNode);
    }

    /**
     * Returns the text in the result display.
     */
    public String getText() {
        return getRootNode().getText();
    }
}
