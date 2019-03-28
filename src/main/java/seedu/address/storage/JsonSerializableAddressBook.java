package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;
import seedu.address.model.participant.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedParticipant> persons = new ArrayList<>();
    private final List<JsonAdaptedGroup> groups = new ArrayList<>();
    private final List<JsonAdaptedHouse> houses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons, groups and houses.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedParticipant> persons,
                                       @JsonProperty("groups") List<JsonAdaptedGroup> groups,
                                       @JsonProperty("houses") List<JsonAdaptedHouse> houses) {
        this.persons.addAll(persons);
        this.groups.addAll(groups);
        this.houses.addAll(houses);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedParticipant::new).collect(Collectors.toList()));
        groups.addAll(source.getGroupList().stream().map(JsonAdaptedGroup::new).collect(Collectors.toList()));
        houses.addAll(source.getHouseList().stream().map(JsonAdaptedHouse::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        for (JsonAdaptedParticipant jsonAdaptedParticipant : persons) {
            Person person = jsonAdaptedParticipant.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }

        for (JsonAdaptedGroup jsonAdaptedGroup : groups) {
            Group group = jsonAdaptedGroup.toModelType();
            if (addressBook.hasGroup(group)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addGroup(group);
        }

        for (JsonAdaptedHouse jsonAdaptedHouse : houses) {
            House house = jsonAdaptedHouse.toModelType();
            if (addressBook.hasHouse(house)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addHouse(house);
        }

        return addressBook;
    }

}
