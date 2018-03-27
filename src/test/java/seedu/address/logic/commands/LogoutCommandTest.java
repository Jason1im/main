package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class LoginCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        SignupCommand command = new SignupCommand("John", "123");
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        command.execute();

        LoginCommand login = new LoginCommand("John", "123");
        login.setData(model, new CommandHistory(), new UndoRedoStack());
        login.execute();
    }

    @Test
    public void execute_logoutSuccessful() throws Exception {
        CommandResult commandResult = new LogoutCommand().execute();
        assertEquals(LogoutCommand.MESSAGE_SUCCESS, commandResult.feedbackToUser);
    }

    @Test
    public void execute_logoutFailure() throws Exception {
        thrown.expect(CommandException.class);
        thrown.expectMessage(LogoutCommand.MESSAGE_MULTIPLE_LOGOUT);

        new LogoutCommand().execute();
        new LogoutCommand().execute();
    }

}
