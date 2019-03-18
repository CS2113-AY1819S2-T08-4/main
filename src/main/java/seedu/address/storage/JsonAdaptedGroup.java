package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;

import seedu.address.model.grouping.Group;
import seedu.address.model.person.Name;

/**
 * Jackson-friendly version of {@link Group}.
 */
class JsonAdaptedGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String groupName;
    private final String houseName;

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("groupName") String groupName, @JsonProperty("houseName") String houseName) {
        this.groupName = groupName;
        this.houseName = houseName;
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        groupName = source.getGroupName();
        houseName = source.getHouseName();
    }

    /**
     * Converts this Jackson-friendly adapted group object into the model's {@code Group} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted group.
     */
    public Group toModelType() throws IllegalValueException {
        if (groupName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(groupName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelGroupName = new Name(groupName);

        if (houseName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(houseName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelHouseName = new Name(houseName);

        return new Group(modelGroupName.toString(), modelHouseName.toString());
    }

}
