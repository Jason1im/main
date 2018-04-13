package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.exception.InvalidPasswordException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;

public class UpdatePasswordCommand extends Command {
    public static final String COMMAND_WORD = "updatepassword";
    public static final String COMMAND_SYNTAX = COMMAND_WORD + " "
            + PREFIX_PASSWORD + " "
            + PREFIX_NEW_PASSWORD;
    public static final String MESSAGE_USAGE = COMMAND_WORD + "Updates the user account password."
            + "Parameters: "
            + PREFIX_PASSWORD + "PASSWORD "
            + PREFIX_NEW_PASSWORD + "PASSWORD\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PASSWORD + "Doe123 "
            + PREFIX_NEW_PASSWORD + "doe456";
    public static final String MESSAGE_SUCCESS = "You have successfully updated your password!";

    private final String oldPassword;
    private final String newPassword;

    public UpdatePasswordCommand(String inputPassword_1, String inputPassword_2) {
        requireAllNonNull(inputPassword_1, inputPassword_2);
        this.oldPassword = inputPassword_1;
        this.newPassword = inputPassword_2;
    }

    @Override
    public CommandResult execute() throws CommandException {
        requireNonNull(model);
        try {
            model.updatePassword(oldPassword, newPassword);
            return new CommandResult(String.format(MESSAGE_SUCCESS));
        } catch (InvalidPasswordException ipe) {
            throw new CommandException(Messages.MESSAGE_INVALID_PASSWORD);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpdatePasswordCommand) // instanceof handles nulls
                && this.oldPassword.equals(((UpdatePasswordCommand) other).oldPassword)
                && this.newPassword.equals(((UpdatePasswordCommand) other).newPassword);
    }
}
