package ee.ve2.openmail;

import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class CommandRegistry {
    private final Map<String, Command> commands = new LinkedHashMap<>();

    public void add(String name, Command command) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Command name must not be blank");
        }
        if (command == null) {
            throw new IllegalArgumentException("Command must not be null");
        }
        commands.put(name.toLowerCase(), command);
    }

    public boolean execute(List<String> tokens, PrintStream output) {
        if (tokens.isEmpty()) {
            return true;
        }

        Command command = commands.get(tokens.get(0).toLowerCase());
        if (command == null) {
            output.println("Unknown command: " + tokens.get(0));
            output.println("Run 'help' to list available commands.");
            return false;
        }

        command.execute(List.copyOf(tokens.subList(1, tokens.size())), output);
        return true;
    }

    public List<String> names() {
        return List.copyOf(commands.keySet());
    }
}