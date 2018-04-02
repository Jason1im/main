package seedu.address.logic.commands.appointment;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ShowYearRequestEvent;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;

import java.time.Year;

//@@author trafalgarandre
/**
 * Change view of calendar to specific year.
 */
public class YearCommand extends Command {
    public static final String COMMAND_WORD = "year";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View year. "
            + "Parameters: year (optional, but must be in format YYYY if have)\n"
            + "Example: " + COMMAND_WORD + " 2018";

    public static final String MESSAGE_SUCCESS = "View year: %1$s";
    public static final String YEAR_VALIDATION_REGEX = "^$|^\\d{4}";
    public static final String MESSAGE_YEAR_CONSTRAINTS = "Year needs to be null or in format YYYY";

    private final Year year;

    /**
     * Creates an YearCommand to view the specified {@code yearMonth} or current if null
     */
    public YearCommand(Year year) {
        this.year = year;
    }

    @Override
    public CommandResult execute() {
        EventsCenter.getInstance().post(new ShowYearRequestEvent(year));

        return new CommandResult(String.format(MESSAGE_SUCCESS, year));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof YearCommand // instanceof handles nulls
                && year.equals(((YearCommand) other).year));
    }
}
