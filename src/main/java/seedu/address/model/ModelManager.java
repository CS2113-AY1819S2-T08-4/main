package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;
import seedu.address.model.participant.Person;
import seedu.address.model.participant.exceptions.PersonNotFoundException;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final UserPrefs userPrefs;

    private final FilteredList<Person> filteredPersons;
    private final SimpleObjectProperty<Person> selectedPerson = new SimpleObjectProperty<>();

    private final FilteredList<Group> filteredGroups;
    private final SimpleObjectProperty<Group> selectedGroups = new SimpleObjectProperty<>();

    //private final FilteredList<House> filteredHouses;
    //private final SimpleObjectProperty<House> selectedHouses = new SimpleObjectProperty<>();

    private String undoableCommand;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(versionedAddressBook.getPersonList());
        filteredPersons.addListener(this::ensureSelectedPersonIsValid);

        filteredGroups = new FilteredList<>(versionedAddressBook.getGroupList());
        filteredGroups.addListener(this::ensureSelectedGroupIsValid);

        // filteredHouses = new FilteredList<>(versionedAddressBook.getHouseList());
        // filteredHouses.addListener(this::ensureSelectedHouseIsValid);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        versionedAddressBook.resetData(addressBook);
        undoableCommand = "clear";
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return versionedAddressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return versionedAddressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        versionedAddressBook.removePerson(target);
        undoableCommand = "Delete" + target.getName().fullName;

        if (FreshmanList.hasFreshman(target.toString())) {
            FreshmanList.deleteFreshman(target.toString());
        }
    }

    @Override
    public void addPerson(Person person) {
        versionedAddressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        undoableCommand = "Add " + person.getName().fullName;
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        versionedAddressBook.setPerson(target, editedPerson);
        undoableCommand = "Edit " + editedPerson.getName().fullName;
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Undoable Command} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<String> getUndoList() {
        return FXCollections.observableArrayList(versionedAddressBook.getUndoList());
    }

    /**
     * Returns an unmodifiable view of the list of {@code Redoable Command} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<String> getRedoList() {
        return FXCollections.observableArrayList(versionedAddressBook.getRedoList());
    }

    /**
     * Returns an unmodifiable view of the list of {@code Undoable Command} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoAddressBook() {
        return versionedAddressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return versionedAddressBook.canRedo();
    }

    @Override
    public void undoAddressBook() {
        versionedAddressBook.undo();
    }

    @Override
    public void redoAddressBook() {
        versionedAddressBook.redo();
    }

    @Override
    public void commitAddressBook() {
        versionedAddressBook.commit();
        versionedAddressBook.addUndoableCommand(undoableCommand);
    }

    //=========== Selected person ===========================================================================

    @Override
    public ReadOnlyProperty<Person> selectedPersonProperty() {
        return selectedPerson;
    }

    @Override
    public Person getSelectedPerson() {
        return selectedPerson.getValue();
    }

    @Override
    public void setSelectedPerson(Person person) {
        if (person != null && !filteredPersons.contains(person)) {
            throw new PersonNotFoundException();
        }
        selectedPerson.setValue(person);
    }

    // ================ Group Operations ======================
    @Override
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return versionedAddressBook.hasGroup(group);
    }

    @Override
    public void deleteGroup(Group target) {
        versionedAddressBook.removeGroup(target);
        undoableCommand = "Delete" + target.getGroupName();

        if (GroupList.hasGroup(target.toString())) {
            GroupList.deleteGroup(target.toString());
        }
    }

    @Override
    public void addGroup(Group group) {
        versionedAddressBook.addGroup(group);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        undoableCommand = "Add " + group.getGroupName();
    }

    @Override
    public void setGroup(Group target, Group editedGroup) {
        requireAllNonNull(target, editedGroup);

        versionedAddressBook.setGroup(target, editedGroup);
        undoableCommand = "Edit " + editedGroup.getGroupName();
    }

    @Override
    public ObservableList<Group> getFilteredGroupList() {
        return filteredGroups;
    }

    @Override
    public void updateFilteredGroupList(Predicate<Group> predicate) {
        requireNonNull(predicate);
        filteredGroups.setPredicate(predicate);
    }

    // ================ House Operations ======================
    @Override
    public House getHouse(House house) {
        return null;
    }

    @Override
    public boolean hasHouse(House house) {
        requireNonNull(house);
        return versionedAddressBook.hasHouse(house);
    }

    @Override
    public void deleteHouse(House target) {
        versionedAddressBook.removeHouse(target);
        undoableCommand = "Delete" + target.getHouseName();

        if (HouseList.hasHouse(target.toString())) {
            HouseList.deleteHouse(target.toString());
        }
    }

    @Override
    public void addHouse(House house) {
        versionedAddressBook.addHouse(house);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        undoableCommand = "Add " + house.getHouseName();
    }

    @Override
    public void setHouse(House target, House editedHouse) {
        requireAllNonNull(target, editedHouse);

        versionedAddressBook.setHouse(target, editedHouse);
        undoableCommand = "Edit " + editedHouse.getHouseName();

    }

    @Override
    public ObservableList<Group> getFilteredHouseList() {
        return null;
    }

    @Override
    public void updateFilteredHouseList(Predicate<House> predicate) {

    }

    /**
     * Ensures {@code selectedPerson} is a valid person in {@code filteredPersons}.
     */
    private void ensureSelectedPersonIsValid(ListChangeListener.Change<? extends Person> change) {
        while (change.next()) {
            if (selectedPerson.getValue() == null) {
                // null is always a valid selected person, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedPersonReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedPerson.getValue());
            if (wasSelectedPersonReplaced) {
                // Update selectedPerson to its new value.
                int index = change.getRemoved().indexOf(selectedPerson.getValue());
                selectedPerson.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedPersonRemoved = change.getRemoved().stream()
                    .anyMatch(removedPerson -> selectedPerson.getValue().isSamePerson(removedPerson));
            if (wasSelectedPersonRemoved) {
                // Select the person that came before it in the list,
                // or clear the selection if there is no such person.
                selectedPerson.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    /**
     * Ensures {@code selectedGroup} is a valid group in {@code filteredGroups}.
     */
    private void ensureSelectedGroupIsValid(ListChangeListener.Change<? extends Group> change) {
        while (change.next()) {
            if (selectedGroups.getValue() == null) {
                // null is always a valid selected group, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedGroupReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedGroups.getValue());
            if (wasSelectedGroupReplaced) {
                // Update selectedGroups to its new value.
                int index = change.getRemoved().indexOf(selectedGroups.getValue());
                selectedGroups.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedGroupRemoved = change.getRemoved().stream()
                    .anyMatch(removedGroup -> selectedGroups.getValue().isSameGroup(removedGroup));
            if (wasSelectedGroupRemoved) {
                // Select the group that came before it in the list,
                // or clear the selection if there is no such group.
                selectedGroups.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    /**
     * Ensures {@code selectedHouse} is a valid house in {@code filteredHouses}.
     */
    /*private void ensureSelectedHouseIsValid(ListChangeListener.Change<? extends House> change) {
        while (change.next()) {
            if (selectedHouses.getValue() == null) {
                // null is always a valid selected house, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedHouseReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedHouses.getValue());
            if (wasSelectedHouseReplaced) {
                // Update selectedHouses to its new value.
                int index = change.getRemoved().indexOf(selectedHouses.getValue());
                selectedHouses.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedHouseRemoved = change.getRemoved().stream()
                    .anyMatch(removedHouse -> selectedHouses.getValue().isSameHouse(removedHouse));
            if (wasSelectedHouseRemoved) {
                // Select the house that came before it in the list,
                // or clear the selection if there is no such house.
                selectedHouses.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }*/

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedAddressBook.equals(other.versionedAddressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && Objects.equals(selectedPerson.get(), other.selectedPerson.get());
    }

}
