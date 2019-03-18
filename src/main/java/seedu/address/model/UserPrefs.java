package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path participantFilePath = Paths.get("data" , "participants.json");

    // List of created Houses
    private Path houseFilePath = Paths.get("data", "houses.json");
    // List of created Groups
    private Path groupFilePath = Paths.get("data", "groups.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setParticipantFilePath(newUserPrefs.getParticipantFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getParticipantFilePath() {
        return participantFilePath;
    }

    public void setParticipantFilePath(Path participantFilePath) {
        requireNonNull(participantFilePath);
        this.participantFilePath = participantFilePath;
    }

    public Path getHouseFilePath() {
        requireNonNull(houseFilePath);
        return houseFilePath;
    }

    public void setHouseFilePath(Path houseFilePath) {
        this.houseFilePath = houseFilePath;
    }

    public Path getGroupFilePath() {
        requireNonNull(groupFilePath);
        return groupFilePath;
    }

    public void setGroupFilePath(Path groupFilePath) {
        this.groupFilePath = groupFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && participantFilePath.equals(o.participantFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, participantFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + participantFilePath);
        return sb.toString();
    }

}
