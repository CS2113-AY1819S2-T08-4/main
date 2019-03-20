package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddHouseCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input argument and creates a new AddHouseCommand object
 */
public class AddHouseCommandParser implements Parser<AddHouseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddGroupCommand
     * and returns an AddGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    @Override
    public AddHouseCommand parse(String args) throws ParseException {
        String houseName = args.trim().toUpperCase();

        if (houseName.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHouseCommand.MESSAGE_USAGE));
        }

        return new AddHouseCommand(houseName);
    }
}
