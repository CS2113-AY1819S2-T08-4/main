package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.person.FindingOglPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListOglCommand extends Command {

    public static final String COMMAND_WORD = "list_o";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all OGLs ";

    public static final String MESSAGE_SUCCESS = "Listed all orientation group leaders";


    private final FindingOglPredicate predicate;

    public ListOglCommand(FindingOglPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListOglCommand // instanceof handles nulls
                && predicate.equals(((ListOglCommand) other).predicate)); // state check
    }
}

