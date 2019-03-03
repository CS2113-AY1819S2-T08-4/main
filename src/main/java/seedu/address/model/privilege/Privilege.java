package seedu.address.model.privilege;


/**
 * Determines the level of privilege of the student based on the privilege assigned.
 * Smaller number means more privilege.
 *
 * e.g. HOUSE_HEAD will have more privilege compared to OGL (1 is less than 2).
 */
public enum Privilege {
    PROJECT_DIRECTOR(0),
    HOUSE_HEAD(1),
    OGL(2),
    PARTICIPANTS(3);

    private final int privilegeNumber;

    Privilege(int privilegeNumber) {
        this.privilegeNumber = privilegeNumber;
    }


}
