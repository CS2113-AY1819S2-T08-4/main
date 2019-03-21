package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.JsonAdaptedParticipant.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Major;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.Phone;
import seedu.address.testutil.Assert;

public class JsonAdaptedParticipantTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_SEX = "ABC";
    private static final String INVALID_BIRTHDAY = "123";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_MAJOR = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GROUP = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_SEX = BENSON.getSex().toString();
    private static final String VALID_BIRTHDAY = BENSON.getBirthday().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getMajor().toString();
    private static final String VALID_GROUP = BENSON.getGroup().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedParticipant person = new JsonAdaptedParticipant(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedParticipant person =
                new JsonAdaptedParticipant(INVALID_NAME, VALID_SEX, VALID_BIRTHDAY, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_GROUP, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedParticipant person = new JsonAdaptedParticipant(null, VALID_SEX, VALID_BIRTHDAY, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_GROUP, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedParticipant person =
                new JsonAdaptedParticipant(VALID_NAME, VALID_SEX, VALID_BIRTHDAY, INVALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_GROUP, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedParticipant person = new JsonAdaptedParticipant(VALID_NAME, VALID_SEX, VALID_BIRTHDAY, null,
                VALID_EMAIL, VALID_ADDRESS, VALID_GROUP, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedParticipant person =
                new JsonAdaptedParticipant(VALID_NAME, VALID_SEX, VALID_BIRTHDAY, VALID_PHONE, INVALID_EMAIL,
                        VALID_ADDRESS, VALID_GROUP, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedParticipant person = new JsonAdaptedParticipant(VALID_NAME, VALID_SEX, VALID_BIRTHDAY, VALID_PHONE,
                null, VALID_ADDRESS, VALID_GROUP, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedParticipant person =
                new JsonAdaptedParticipant(VALID_NAME, VALID_SEX, VALID_BIRTHDAY, VALID_PHONE, VALID_EMAIL,
                        INVALID_MAJOR, VALID_GROUP, VALID_TAGS);
        String expectedMessage = Major.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedParticipant person = new JsonAdaptedParticipant(VALID_NAME, VALID_SEX, VALID_BIRTHDAY, VALID_PHONE,
                VALID_EMAIL, null, VALID_GROUP, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Major.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedParticipant person =
                new JsonAdaptedParticipant(VALID_NAME, VALID_SEX, VALID_BIRTHDAY, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_GROUP, invalidTags);
        Assert.assertThrows(IllegalValueException.class, person::toModelType);
    }

}
