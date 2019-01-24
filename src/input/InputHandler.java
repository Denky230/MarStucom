
package input;

import input.commands.Command;
import input.commands.Delete;
import input.commands.GetGem;
import input.commands.ListRanking;
import input.commands.ListSuperheroes;
import input.commands.Login;
import input.commands.Move;
import input.commands.Register;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {

    private List<Command> commands;

    private InputHandler() {
        initCommands();
    }
    private static InputHandler instance;
    public static InputHandler getInstance() {
        if (instance == null)
            instance = new InputHandler();
        return instance;
    }

    /**
     * Initialize application supported commands.
     * @see Command
     */
    private void initCommands() {
        commands = new ArrayList<>();

        // Add supported commands
        commands.add(new Login(2, "L"));
        commands.add(new Delete(1, "D"));
        commands.add(new Register(3, "R"));
        commands.add(new ListRanking(0, "K"));
        commands.add(new ListSuperheroes(0, "V"));
        commands.add(new GetGem(2, "G"));
        commands.add(new Move(0, "N"));
        commands.add(new Move(0, "S"));
        commands.add(new Move(0, "E"));
        commands.add(new Move(0, "W"));
    }

    public void processInput(String input) throws IOException, SQLException {
            String[] in = input.split(" ");

            // First input is the command call, rest is arguments
            String commandCode = in[0];
            String[] arguments = new String[in.length - 1];
            for (int i = 0; i < arguments.length; i++) {
                arguments[i] = in[i + 1];
            }

            // Validate User input as a Command
            Command c = validateCommand(commandCode, arguments);            
            // Call Command with given arguments
            c.call(arguments);
    }

    private Command validateCommand(String callCode, String[] args) throws IOException {
        // Make sure given command exists
        Command c = getCommandByCallCode(callCode);
        // Make sure number of arguments is correct for given command
        if (c.getArguments() != args.length)
            throw new IOException("Invalid num of args");

        return c;
    }
    private Command getCommandByCallCode(String callCode) throws IOException {
        for (Command command : commands) {
            if (command.getCallCode().equalsIgnoreCase(callCode))
                return command;
        }
        throw new IOException("Bad command");
    }
}