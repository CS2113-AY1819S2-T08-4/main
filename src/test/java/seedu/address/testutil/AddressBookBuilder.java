package seedu.address.testutil;

import seedu.address.model.ParticipantAddressBook;
import seedu.address.model.participant.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code ParticipantAddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ParticipantAddressBook participantAddressBook;

    public AddressBookBuilder() {
        participantAddressBook = new ParticipantAddressBook();
    }

    public AddressBookBuilder(ParticipantAddressBook participantAddressBook) {
        this.participantAddressBook = participantAddressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code ParticipantAddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        participantAddressBook.addPerson(person);
        return this;
    }

    public ParticipantAddressBook build() {
        return participantAddressBook;
    }
}
