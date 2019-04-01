package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableMap;


/**
 * {@code AddressBook} that keeps track of its own history.
 */
public class VersionedAddressBook extends AddressBook {

    private ArrayList<String> redoList = new ArrayList<>();
    private ArrayList<String> undoList = new ArrayList<>();

    private final List<ReadOnlyAddressBook> addressBookStateList;
    private int currentStatePointer;


    public VersionedAddressBook(ReadOnlyAddressBook initialState) {
        super(initialState);

        addressBookStateList = new ArrayList<>();
        addressBookStateList.add(new AddressBook(initialState));
        currentStatePointer = 0;
    }

    public boolean isEmpty() {
        return addressBookStateList.get(currentStatePointer).getPersonList().isEmpty();
    }

    public ObservableMap<String, Integer> getAgeData() {
        return addressBookStateList.get(currentStatePointer).getAgeData();
    }

    public ObservableMap<String, Integer> getMajorData() {
        return addressBookStateList.get(currentStatePointer).getMajorData();
    }

    public ObservableMap<String, Integer> getSexData() {
        return addressBookStateList.get(currentStatePointer).getSexData();
    }

    public void addUndoableCommand (String undoableCommand) {
        undoList.add(0, undoableCommand);
    }
    /**
     * Saves a copy of the current {@code AddressBook} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    public void commit() {
        redoList.clear();
        removeStatesAfterCurrentPointer();
        addressBookStateList.add(new AddressBook(this));
        currentStatePointer++;
        indicateModified();
    }

    private void removeStatesAfterCurrentPointer() {
        addressBookStateList.subList(currentStatePointer + 1, addressBookStateList.size()).clear();
    }

    /**
     * Restores the address book to its previous state.
     */
    public void undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        if (undoList.size() > 0) {
            redoList.add(0, undoList.get(0));
            undoList.remove(0);
        }
        currentStatePointer--;
        resetData(addressBookStateList.get(currentStatePointer));
    }

    public ArrayList<String> getUndoList() {
        return undoList;
    }

    /**
     * Restores the address book to its previously undone state.
     */
    public void redo() {
        if (!canRedo()) {
            throw new NoRedoableStateException();
        }
        if (redoList.size() > 0) {
            undoList.add(0, redoList.get(0));
            redoList.remove(0);
        }
        currentStatePointer++;
        resetData(addressBookStateList.get(currentStatePointer));
    }

    public ArrayList<String> getRedoList() {
        return redoList;
    }


    /**
     * Returns true if {@code undo()} has address book states to undo.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if {@code redo()} has address book states to redo.
     */
    public boolean canRedo() {
        return currentStatePointer < addressBookStateList.size() - 1;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedAddressBook)) {
            return false;
        }

        VersionedAddressBook otherVersionedAddressBook = (VersionedAddressBook) other;

        // state check
        return super.equals(otherVersionedAddressBook)
                && addressBookStateList.equals(otherVersionedAddressBook.addressBookStateList)
                && currentStatePointer == otherVersionedAddressBook.currentStatePointer;
    }

    /**
     * Thrown when trying to {@code undo()} but can't.
     */
    public static class NoUndoableStateException extends RuntimeException {
        private NoUndoableStateException() {
            super("Current state pointer at start of addressBookState list, unable to undo.");
        }
    }

    /**
     * Thrown when trying to {@code redo()} but can't.
     */
    public static class NoRedoableStateException extends RuntimeException {
        private NoRedoableStateException() {
            super("Current state pointer at end of addressBookState list, unable to redo.");
        }
    }
}
