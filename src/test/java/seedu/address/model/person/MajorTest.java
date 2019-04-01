package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.participant.Major;
import seedu.address.testutil.Assert;

public class MajorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Major(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Major(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> Major.isValidMajor(null));

        // invalid addresses
        assertFalse(Major.isValidMajor("")); // empty string
        assertFalse(Major.isValidMajor(" ")); // spaces only

        // valid addresses
        assertTrue(Major.isValidMajor("CS"));
        assertTrue(Major.isValidMajor("cs")); // one character
        assertTrue(Major.isValidMajor("cOmputer SCience")); // long address
    }
}
