package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.person.FindingParticipantPredicate;
/**
 * Lists all persons in the address book to the user.
 */
public class ListParticipantCommand extends Command {

    public static final String COMMAND_WORD = "list_f";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all participants";

    public static final String MESSAGE_SUCCESS = "Listed all freshmen";

    private final FindingParticipantPredicate predicate;

    public ListParticipantCommand(FindingParticipantPredicate predicate) {
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
                || (other instanceof ListParticipantCommand // instanceof handles nulls
                && predicate.equals(((ListParticipantCommand) other).predicate)); // state check
    }
}
