package seedu.address.logic.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import static org.junit.Assert.fail;

public class LogoutCommandTest {

    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        LoginCommand command = new LoginCommand("JohnDoe", "12345");
        command.execute();
    }

    /**
     * Executes a {@code LogoutCommand} and checks that {@code expectedMessage} is displayed.
     */
    @Test
    public void execute_validLogout_success() {
        LogoutCommand command = new LogoutCommand();
        try {
            CommandResult commandResult = command.execute();
            assertEquals(commandResult.feedbackToUser, LogoutCommand.MESSAGE_SUCCESS);
        } catch (CommandException ce) {
            fail("This command should not fail.");
        }
    }

    /**
     * Executes a {@code LogoutCommand} and checks that a {@code CommandException}
     * is thrown with the {@code expectedMessage}.
     */
    @Test
    public void execute_invalidLogout_fail() {
        LogoutCommand command = new LogoutCommand();
        try {
            CommandResult commandResult = command.execute();
            fail("The expected CommandException was not thrown.");
        } catch (CommandException ce) {
            assertEquals(ce.getMessage(), LogoutCommand.MESSAGE_MULTIPLE_LOGOUT);
        }

    }


}
