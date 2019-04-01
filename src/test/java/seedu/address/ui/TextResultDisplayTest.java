package seedu.address.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.TextResultDisplayHandle;

public class TextResultDisplayTest extends GuiUnitTest {

    private TextResultDisplay textResultDisplay;
    private TextResultDisplayHandle textResultDisplayHandle;

    @Before
    public void setUp() {
        textResultDisplay = new TextResultDisplay();
        uiPartRule.setUiPart(textResultDisplay);

        textResultDisplayHandle = new TextResultDisplayHandle(getChildNode(textResultDisplay.getRoot(),
                TextResultDisplayHandle.TEXT_RESULT_DISPLAY));
    }

    @Test
    public void display() {
        // default result text
        guiRobot.pauseForHuman();
        assertEquals("", textResultDisplayHandle.getText());

        // new result received
        guiRobot.interact(() -> textResultDisplay.setFeedbackToUser("Dummy feedback to user"));
        guiRobot.pauseForHuman();
        assertEquals("Dummy feedback to user", textResultDisplayHandle.getText());
    }
}
