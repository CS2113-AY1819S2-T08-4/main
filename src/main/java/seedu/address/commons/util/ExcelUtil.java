package seedu.address.commons.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.participant.Person;
import seedu.address.model.tag.Tag;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Logger;

/**
 * Credits to Apache.poi website.
 */
public class ExcelUtil {
    private static final int FIRST_COLUMN = 0;
    private static final int SECOND_COLUMN = 1;
    private static final int THIRD_COLUMN = 2;
    private static final int FOURTH_COLUMN = 3;
    private static final int FIFTH_COLUMN = 4;
    private static final int SIXTH_COLUMN = 5;
    private static final int SEVENTH_COLUMN = 6;
    private static final int EIGHTH_COLUMN = 7;
    private static final int LEFT_OUT_CHARACTER = 4;
    private static final int STARTING_INDEX = 0;
    private static final int RECORD_EMPTY = 0;
    private static final String NAME_TITLE = "NAME";
    private static final String SEX_TITLE = "SEX";
    private static final String BIRTHDAY_TITLE = "BIRTHDAY";
    private static final String PHONE_TITLE = "PHONE";
    private static final String EMAIL_TITLE = "EMAIL";
    private static final String MAJOR_TITLE = "MAJOR";
    private static final String GROUP_TITLE = "GROUP";
    private static final String TAG_TITLE = "TAGS";
    private static final String TAG_SEPARATOR = "  ... ";

    private static Logger logger = LogsCenter.getLogger(ExcelUtil.class);

    /**
     * Write the excel sheet into Directory.
     */
    public static void writeExcelSheetIntoDirectory (List<Person> persons,
                                                     XSSFSheet recordDataSheet,
                                                     XSSFWorkbook workbook, String filePath) {
        try {
            writeDataIntoExcelSheet(persons, recordDataSheet);  //Write data into the sheet specified
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(filePath, false);
            workbook.write(out); //write the workbook out to the filepath
            out.close(); //close the outputing.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Check is the file is being used, if yes, then we cannot read/write the Excel file.
     */
    public static Boolean checkIfFileOpen (String filePath) {
        try {
            File filexssf = new File(filePath);
            File filexssfCopy = new File(filePath);
            if (filexssf.renameTo(filexssfCopy)) {
                logger.info("WRITE EXCEL: FILE CLOSED");
                return false;
            } else {
                logger.info("WRITE EXCEL: FILE OPENED");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Write data into Excel Sheet.
     */
    private static void writeDataIntoExcelSheet (List<Person> persons, XSSFSheet sheet) {
        logger.info("----------------------------------------------------------START WRITE INTO EXCEL FILE");
        int rowNum = STARTING_INDEX;
        Row startingRow = sheet.createRow(rowNum);
        //WRITE IN THE TITLES OF EACH COLUMN == 8 COLUMNS
        writeDataIntoCell(startingRow, FIRST_COLUMN, NAME_TITLE);
        writeDataIntoCell(startingRow, SECOND_COLUMN, SEX_TITLE);
        writeDataIntoCell(startingRow, THIRD_COLUMN, BIRTHDAY_TITLE);
        writeDataIntoCell(startingRow, FOURTH_COLUMN, PHONE_TITLE);
        writeDataIntoCell(startingRow, FIFTH_COLUMN, EMAIL_TITLE);
        writeDataIntoCell(startingRow, SIXTH_COLUMN, MAJOR_TITLE);
        writeDataIntoCell(startingRow, SEVENTH_COLUMN, GROUP_TITLE);
        writeDataIntoCell(startingRow, EIGHTH_COLUMN, TAG_TITLE);

        for (Person person : persons) {
            Row row = sheet.createRow(++rowNum);
            //WRITING THE DATA INTO THE ROWS
            StringBuilder stringBuilder = new StringBuilder();
            writeDataIntoCell(row, FIRST_COLUMN, person.getName().fullName);
            writeDataIntoCell(row, SECOND_COLUMN, person.getSex().value);
            writeDataIntoCell(row, THIRD_COLUMN, person.getBirthday().value);
            writeDataIntoCell(row, FOURTH_COLUMN, person.getPhone().value);
            writeDataIntoCell(row, FIFTH_COLUMN, person.getEmail().value);
            writeDataIntoCell(row, SIXTH_COLUMN, person.getMajor().value);
            writeDataIntoCell(row, SEVENTH_COLUMN, person.getGroup().getGroupName());
            if (person.getTags().size() > RECORD_EMPTY) {
                for (Tag tag : person.getTags()) {
                    stringBuilder.append(tag.tagName + TAG_SEPARATOR);
                }
                writeDataIntoCell(row, EIGHTH_COLUMN, stringBuilder.toString()
                        .substring(STARTING_INDEX, stringBuilder.toString().length() - LEFT_OUT_CHARACTER));
            }
        }
    }
    /**
     * Write data into cell.
     */
    private static void writeDataIntoCell (Row row, int colNum, Object object) {
        if (object instanceof String) {
            row.createCell(colNum).setCellValue((String) object);
        } else {
            row.createCell(colNum).setCellValue((Double) object);
        }
    }

    /**
     * Create the fileName path.
     */
    public static String setPathFile (String nameFile, String directoryPath) {
        String checkedNameFile;

        checkedNameFile = (nameFile.length() > 5
                && nameFile.substring(nameFile.length() - 5, nameFile.length()).equals(".xlsx"))
                ? nameFile : (nameFile + ".xlsx");
        logger.info("=----------------------------------------" + checkedNameFile);
        return directoryPath + (System.getProperty("file.separator") + checkedNameFile);
    }
    /**
     * Set the name for the Excel file based on type of inputs.
     */
    public static String setNameExcelFile () {
        return  "FOP_MANAGER_LIST.xlsx";
    }
}
