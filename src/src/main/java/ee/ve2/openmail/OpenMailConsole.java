package ee.ve2.openmail;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public final class OpenMailConsole {
    private final PrintStream output;
    private final Scanner input;
    private final CommandRegistry registry;
    private final Setup setup;

    private OpenMailConsole(PrintStream output, InputStream input) {
        this.output = output;
        this.input = new Scanner(input);
        this.registry = new CommandRegistry();
        this.setup = new Setup();
        registerCommands();
    }

    public static OpenMailConsole create(PrintStream output, InputStream input) {
        return new OpenMailConsole(output, input);
    }

    public void addCommand(String name, Command command) {
        registry.add(name, command);
    }

    public void run(String[] arguments) {
        boolean commandMode = arguments.length > 0 && "-c".equals(arguments[0]);
        setup.ensureReady(input, output, !commandMode);
        if (commandMode) {
            if (arguments.length == 1) {
                output.println("Usage: java -jar openmail.jar -c \"command [arguments]\"");
                return;
            }
            runCommand(String.join(" ", java.util.Arrays.copyOfRange(arguments, 1, arguments.length)));
            return;
        }
        if (arguments.length > 0) {
            output.println("Usage: java -jar openmail.jar [-c \"command [arguments]\"]");
            return;
        }
        interactiveLoop();
    }

    private void registerCommands() {
        registry.add("help", (arguments, result) -> {
            result.println("Available commands: " + String.join(", ", registry.names()));
            result.println("Usage: -c \"command [arguments]\"");
        });
        registry.add("version", (arguments, result) -> result.println("OpenMail 0.1"));
        registry.add("exit", (arguments, result) -> {
        });
    }

    private void interactiveLoop() {
        output.println("Type 'help' for available commands.");
        while (true) {
            output.print("openmail> ");
            if (!input.hasNextLine()) {
                return;
            }
            String commandLine = input.nextLine();
            if ("exit".equalsIgnoreCase(firstToken(commandLine))) {
                return;
            }
            runCommand(commandLine);
        }
    }

    private void runCommand(String commandLine) {
        try {
            registry.execute(CommandLineParser.parse(commandLine), output);
        } catch (IllegalArgumentException exception) {
            output.println("Invalid command: " + exception.getMessage());
        }
    }

    private String firstToken(String commandLine) {
        try {
            var tokens = CommandLineParser.parse(commandLine);
            return tokens.isEmpty() ? "" : tokens.get(0);
        } catch (IllegalArgumentException exception) {
            return "";
        }
    }
}