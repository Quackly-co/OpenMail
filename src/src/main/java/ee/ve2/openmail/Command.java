package ee.ve2.openmail;

import java.io.PrintStream;
import java.util.List;

@FunctionalInterface
public interface Command {
    void execute(List<String> arguments, PrintStream output);
}