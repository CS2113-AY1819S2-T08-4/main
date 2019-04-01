package seedu.address.logic.commands;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import seedu.address.commons.core.Messages;
import seedu.address.commons.util.ExcelUtil;
import seedu.address.logic.CommandHistory;
import seedu.address.model.DirectoryPath;
import seedu.address.model.Model;
import seedu.address.model.participant.Person;

import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.ExcelUtil.setPathFile;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Export the data of the FOP Manager into Excel Sheets.
 */
public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the contacts into Excel file .\n";

    private String directoryPath;
    private Predicate<Person> predicate;

    public ExportCommand() {
        this.directoryPath = DirectoryPath.WORKING_DIRECTORY_STRING;
        this.predicate = PREDICATE_SHOW_ALL_PERSONS;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) {
        requireNonNull(this);
        model.updateFilteredPersonList(predicate); //get the list
        List<Person> personList = model.getFilteredPersonList(); //set that list to the List created in this command
        String nameFile = ExcelUtil.setNameExcelFile(); //name the excel file
        String filePath = setPathFile(nameFile, directoryPath); //set the path of the file to directoryPath chosen

        /**
         * Write the success excel export message.
         */
        String message; //success message
        if (exportDataIntoExcelSheet(personList, filePath)) {
            message = String.format(Messages.MESSAGE_EXCEL_FILE_WRITTEN_SUCCESSFULLY, nameFile, directoryPath);
        } else {
            message = Messages.MESSAGE_EXPORT_COMMAND_ERRORS;
        }
        return new CommandResult(message);
    }
    /**
     * Export the contacts into Excel File.
     */
    private static Boolean exportDataIntoExcelSheet(List<Person> personList, String filePath) {
        XSSFWorkbook workbook = new XSSFWorkbook(); //create the workbook
        XSSFSheet recordData = workbook.createSheet("FOP CONTACTS"); //create the FOP CONTACTS tab on excel
        if (personList.size() > 0) {  //if the list is not empty, write the sheet to this location
            ExcelUtil.writeExcelSheetIntoDirectory(personList, recordData, workbook, filePath);
            return true;
        }
        return false; //if empty list
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportCommand // instanceof handles nulls
                && predicate.equals(((ExportCommand) other).predicate)
                && directoryPath.equals(((ExportCommand) other).directoryPath)); // state check
    }
}
