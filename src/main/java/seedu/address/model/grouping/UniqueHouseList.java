package seedu.address.model.grouping;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.participant.exceptions.DuplicatePersonException;
import seedu.address.model.participant.exceptions.PersonNotFoundException;

/**
 * A list of houses that enforces uniqueness between its elements and does not allow nulls.
 * A house is considered unique by comparing using {@code House#isSameHouse(House)}.
 * As such, adding and updating of
 * houses uses House#isSameHouse(House) for equality so as to ensure that the House being added or updated is
 * unique in terms of identity in the UniqueHouseList.
 * However, the removal of a house uses House#equals(Object) so
 * as to ensure that the house with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see House#isSameHouse(House)
 */
public class UniqueHouseList implements Iterable<House> {

    private final ObservableList<House> internalList = FXCollections.observableArrayList();
    private final ObservableList<House> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent house as the given argument.
     */
    public boolean contains(House toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameHouse);
    }

    /**
     * Adds a house to the list.
     * The house must not already exist in the list.
     */
    public void add(House toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the house {@code target} in the list with {@code editedHouse}.
     * {@code target} must exist in the list.
     * The house identity of {@code editedHouse} must not be the same as another existing house in the list.
     */
    public void setHouse(House target, House editedHouse) {
        requireAllNonNull(target, editedHouse);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameHouse(editedHouse) && contains(editedHouse)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedHouse);
    }

    /**
     * Removes the equivalent house from the list.
     * The house must exist in the list.
     */
    public void remove(House toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code houses}.
     * {@code houses} must not contain duplicate houses.
     */
    public void setHouses(List<House> houses) {
        requireAllNonNull(houses);
        if (!housesAreUnique(houses)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(houses);
    }

    public void setHouses(UniqueHouseList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<House> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<House> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueHouseList // instanceof handles nulls
                && internalList.equals(((UniqueHouseList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code houses} contains only unique houses.
     */
    private boolean housesAreUnique(List<House> houses) {
        for (int i = 0; i < houses.size() - 1; i++) {
            for (int j = i + 1; j < houses.size(); j++) {
                if (houses.get(i).isSameHouse(houses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
