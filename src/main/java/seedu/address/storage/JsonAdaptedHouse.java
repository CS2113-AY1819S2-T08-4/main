package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;

import seedu.address.model.grouping.House;
import seedu.address.model.participant.Name;

/**
 * Jackson-friendly version of {@link House}.
 */
public class JsonAdaptedHouse {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "House's %s field is missing!";

    private final String houseName;

    /**
     * Constructs a {@code JsonAdaptedHouse} with the given house details.
     */
    @JsonCreator
    public JsonAdaptedHouse(@JsonProperty("houseName") String houseName) {
        this.houseName = houseName;
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedHouse(House source) {
        houseName = source.getHouseName();
    }

    /**
     * Converts this Jackson-friendly adapted house object into the model's {@code House} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted house.
     */
    public House toModelType() throws IllegalValueException {
        if (houseName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }

        if (!Name.isValidName(houseName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        return new House(houseName);
    }

}
