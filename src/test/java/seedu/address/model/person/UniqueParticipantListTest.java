package seedu.address.model.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.participant.Person;
import seedu.address.model.participant.UniqueParticipantList;
import seedu.address.model.participant.exceptions.DuplicatePersonException;
import seedu.address.model.participant.exceptions.PersonNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class UniqueParticipantListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final UniqueParticipantList uniqueParticipantList = new UniqueParticipantList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueParticipantList.contains(null);
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueParticipantList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueParticipantList.add(ALICE);
        assertTrue(uniqueParticipantList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueParticipantList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withMajor(VALID_MAJOR_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueParticipantList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueParticipantList.add(null);
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueParticipantList.add(ALICE);
        thrown.expect(DuplicatePersonException.class);
        uniqueParticipantList.add(ALICE);
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueParticipantList.setPerson(null, ALICE);
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueParticipantList.setPerson(ALICE, null);
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        thrown.expect(PersonNotFoundException.class);
        uniqueParticipantList.setPerson(ALICE, ALICE);
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueParticipantList.add(ALICE);
        uniqueParticipantList.setPerson(ALICE, ALICE);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        expectedUniqueParticipantList.add(ALICE);
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueParticipantList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withMajor(VALID_MAJOR_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueParticipantList.setPerson(ALICE, editedAlice);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        expectedUniqueParticipantList.add(editedAlice);
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueParticipantList.add(ALICE);
        uniqueParticipantList.setPerson(ALICE, BOB);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        expectedUniqueParticipantList.add(BOB);
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueParticipantList.add(ALICE);
        uniqueParticipantList.add(BOB);
        thrown.expect(DuplicatePersonException.class);
        uniqueParticipantList.setPerson(ALICE, BOB);
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueParticipantList.remove(null);
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        thrown.expect(PersonNotFoundException.class);
        uniqueParticipantList.remove(ALICE);
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueParticipantList.add(ALICE);
        uniqueParticipantList.remove(ALICE);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueParticipantList.setPersons((UniqueParticipantList) null);
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueParticipantList.add(ALICE);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        expectedUniqueParticipantList.add(BOB);
        uniqueParticipantList.setPersons(expectedUniqueParticipantList);
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueParticipantList.setPersons((List<Person>) null);
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueParticipantList.add(ALICE);
        List<Person> personList = Collections.singletonList(BOB);
        uniqueParticipantList.setPersons(personList);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        expectedUniqueParticipantList.add(BOB);
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Person> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        thrown.expect(DuplicatePersonException.class);
        uniqueParticipantList.setPersons(listWithDuplicatePersons);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueParticipantList.asUnmodifiableObservableList().remove(0);
    }
}
