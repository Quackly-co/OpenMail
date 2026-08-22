package ee.ve2.openmail;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public final class Setup {
    private static final String DATA_DIRECTORY_PROPERTY = "data.directory";
    private final Path settingsFile;
    private final Path defaultDataDirectory;

    public Setup() {
        Path home = Path.of(System.getProperty("user.home"));
        settingsFile = home.resolve(".openmail").resolve("settings.properties");
        defaultDataDirectory = home.resolve(".openmail").resolve("data");
    }

    public Path ensureReady(Scanner input, PrintStream output, boolean interactive) {
        if (Files.exists(settingsFile)) {
            return readDataDirectory();
        }

        Path dataDirectory = defaultDataDirectory;
        if (interactive) {
            output.println("Welcome to OpenMail. First-time setup is starting.");
            output.print("Data directory [" + defaultDataDirectory + "]: ");
            String value = input.nextLine().trim();
            if (!value.isEmpty()) {
                dataDirectory = Path.of(value).toAbsolutePath().normalize();
            }
        }

        try {
            Files.createDirectories(dataDirectory);
            Files.createDirectories(settingsFile.getParent());
            Files.writeString(settingsFile, DATA_DIRECTORY_PROPERTY + "=" + dataDirectory + System.lineSeparator());
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to complete OpenMail setup", exception);
        }
        output.println("OpenMail is ready. Data directory: " + dataDirectory);
        return dataDirectory;
    }

    private Path readDataDirectory() {
        try {
            for (String line : Files.readAllLines(settingsFile)) {
                if (line.startsWith(DATA_DIRECTORY_PROPERTY + "=")) {
                    return Path.of(line.substring(DATA_DIRECTORY_PROPERTY.length() + 1));
                }
            }
        } catch (IOException | InvalidPathException exception) {
            throw new IllegalStateException("Unable to read OpenMail setup", exception);
        }
        return defaultDataDirectory;
    }
}