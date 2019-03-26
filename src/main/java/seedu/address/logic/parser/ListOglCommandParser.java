package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ListOglCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FindingOglPredicate;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ListOglCommandParser implements Parser<ListOglCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListOglCommand parse(String args) throws ParseException {
        String trimmedArgs = "OGL";
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListOglCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new ListOglCommand(new FindingOglPredicate(Arrays.asList(nameKeywords)));
    }

}
