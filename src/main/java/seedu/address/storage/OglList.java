package seedu.address.storage;

import java.util.ArrayList;

import seedu.address.model.role.Ogl;
/**
 * Stores the list of OGLs
 */
public class OglList {

    private static ArrayList<String> oglNames = new ArrayList<>();
    private static ArrayList<Ogl> ogls = new ArrayList<>();

    /**
     * Constructs a List for storing ogls.
     */
    public OglList() {
    }

    public static ArrayList<Ogl> getOgls() {
        return ogls;
    }

    /**
     * Adds an OGL
     */
    public static void addOgl(String nameToAdd) {
        oglNames.add(nameToAdd);
    }
    /**
     * Deletes a freshman
     */
    public static void deleteOgl(String nameToDelete) {
        oglNames.remove(nameToDelete);
    }
    /**
     * Checks if a freshman exists
     */
    public static boolean hasOgl (String toFind) {
        return oglNames.contains(toFind);
    }
    /**
     * Prints the list
     */
    public static void listOgl() {
        for (int i = 0; i < ogls.size(); i++) {
            System.out.println(ogls.get(i));
            System.out.println();
        }
    }
}

