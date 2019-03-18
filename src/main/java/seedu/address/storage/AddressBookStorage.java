package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ParticipantAddressBook;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Represents a participantStorage for {@link ParticipantAddressBook}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns ParticipantAddressBook data as a {@link ReadOnlyAddressBook}.
     *   Returns {@code Optional.empty()} if participantStorage file is not found.
     * @throws DataConversionException if the data in participantStorage is not in the expected format.
     * @throws IOException if there was any problem when reading from the participantStorage.
     */
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the participantStorage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyAddressBook)
     */
    void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException;

}
