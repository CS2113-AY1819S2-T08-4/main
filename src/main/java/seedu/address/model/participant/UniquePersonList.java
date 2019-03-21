package seedu.address.model.participant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.participant.exceptions.DuplicatePersonException;
import seedu.address.model.participant.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A participant is considered unique by comparing using {@code Participant#isSamePerson(Participant)}. As such, adding and updating of
 * persons uses Participant#isSamePerson(Participant) for equality so as to ensure that the participant being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a participant uses Participant#equals(Object) so
 * as to ensure that the participant with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Participant#isSamePerson(Participant)
 */
public class UniquePersonList implements Iterable<Participant> {

    private final ObservableList<Participant> internalList = FXCollections.observableArrayList();
    private final ObservableList<Participant> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent participant as the given argument.
     */
    public boolean contains(Participant toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a participant to the list.
     * The participant must not already exist in the list.
     */
    public void add(Participant toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the participant {@code target} in the list with {@code editedParticipant}.
     * {@code target} must exist in the list.
     * The participant identity of {@code editedParticipant} must not be the same as another existing participant in the list.
     */
    public void setPerson(Participant target, Participant editedParticipant) {
        requireAllNonNull(target, editedParticipant);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedParticipant) && contains(editedParticipant)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedParticipant);
    }

    /**
     * Removes the equivalent participant from the list.
     * The participant must exist in the list.
     */
    public void remove(Participant toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code participants}.
     * {@code participants} must not contain duplicate participants.
     */
    public void setPersons(List<Participant> participants) {
        requireAllNonNull(participants);
        if (!personsAreUnique(participants)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(participants);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Participant> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Participant> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code participants} contains only unique participants.
     */
    private boolean personsAreUnique(List<Participant> participants) {
        for (int i = 0; i < participants.size() - 1; i++) {
            for (int j = i + 1; j < participants.size(); j++) {
                if (participants.get(i).isSamePerson(participants.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
