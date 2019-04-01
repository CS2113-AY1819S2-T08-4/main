package seedu.address.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.model.grouping.Group;
import seedu.address.model.grouping.House;
import seedu.address.model.participant.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook extends Observable {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();


    /**
     * Return an unmodifiable view of the persons data
     * by age, major and sex.
     */
    ObservableMap<String, Integer> getAgeData();
    ObservableMap<String, Integer> getMajorData();
    ObservableMap<String, Integer> getSexData();

    /**
     * Returns an unmodifiable view of the groups list.
     * This list will not contain any duplicate group.
     */
    ObservableList<Group> getGroupList();

    /**
     * Returns an unmodifiable view of the house list.
     * This list will not contain any duplicate house.
     */
    ObservableList<House> getHouseList();

}
