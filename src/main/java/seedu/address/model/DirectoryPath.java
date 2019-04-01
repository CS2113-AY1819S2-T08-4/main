package seedu.address.model;

import seedu.address.commons.core.Messages;

import java.io.File;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * File path is used to store the preferable location to store the Excel File.
 */
public class DirectoryPath {
    public static final String WORKING_DIRECTORY_STRING = System.getProperty("user.dir");

    public static final DirectoryPath WORKING_DIRECTORY = new DirectoryPath(WORKING_DIRECTORY_STRING);

    private String value;

    public DirectoryPath(String dirPath) {
        requireNonNull(dirPath);
        checkArgument((isExistingDirectory(dirPath) || isExistingFilePath(dirPath)),
                String.format(Messages.MESSAGE_DIRECTORY_NO_EXIST));
        this.value = dirPath;
    }
    public DirectoryPath() {
        this.value = getDefaultWorkingDirectoryValue();
    }

    public String getValue() {
        return value;
    }

    public DirectoryPath getDirectoryPath() {
        return (value == null) ? WORKING_DIRECTORY : new DirectoryPath(value);
    }

    public String getDirectoryPathValue() {
        return (value == null) ? WORKING_DIRECTORY_STRING : value;
    }

    public static String getDefaultWorkingDirectoryValue() {
        return WORKING_DIRECTORY_STRING;
    }

    public static DirectoryPath getDefaultWorkingDirectory() {
        return WORKING_DIRECTORY;
    }

    /**
     * Check whether the given directory is realistic or not.
     * @return boolean value to indicate whether the directory exists.
     */
    public static Boolean isExistingDirectory(String dirPath) {
        File file = new File(dirPath);
        return file.isDirectory();
    }

    /**
     * Check whether the given directory is realistic or not.
     * @return boolean value to indicate whether the directory exists.
     */
    public static Boolean isExistingFilePath(String dirPath) {
        File file = new File(dirPath);
        return file.isFile();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DirectoryPath // instanceof handles nulls
                && (this.getDirectoryPath().getDirectoryPathValue())
                .equals(((DirectoryPath) other).getDirectoryPath().getDirectoryPathValue()));
    }
}