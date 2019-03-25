package seedu.address.logic.parser;

import seedu.address.logic.commands.RandomizeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Creates a new RandomizeCommand object
 */
public class RandomizeCommandParser implements Parser<RandomizeCommand> {

    /**
     * Returns a RandomizeCommand object for execution.
     * @throws ParseException if the user did not add participants and there is insufficient groups (minimum 2)
     */
    public RandomizeCommand parse(String userInput) throws ParseException {
        // check if there is at least 2 group and there is at least 1 participant in the system

        return new RandomizeCommand();
    }
}
