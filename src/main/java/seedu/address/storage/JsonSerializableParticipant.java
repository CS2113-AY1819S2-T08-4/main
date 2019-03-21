package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ParticipantAddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

/**
 * An Immutable ParticipantAddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "participant")
class JsonSerializableParticipant {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedParticipant> participants = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableParticipant} with the given participants.
     */
    @JsonCreator
    public JsonSerializableParticipant(@JsonProperty("participants") List<JsonAdaptedParticipant> participants) {
        this.participants.addAll(participants);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableParticipant}.
     */
    public JsonSerializableParticipant(ReadOnlyAddressBook source) {
        participants.addAll(
                source.getPersonList().stream().map(JsonAdaptedParticipant::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code ParticipantAddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ParticipantAddressBook toModelType() throws IllegalValueException {
        ParticipantAddressBook participantAddressBook = new ParticipantAddressBook();
        for (JsonAdaptedParticipant jsonAdaptedParticipant : participants) {
            Person person = jsonAdaptedParticipant.toModelType();
            if (participantAddressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            participantAddressBook.addPerson(person);
        }
        return participantAddressBook;
    }

}
