package ee.ve2.openmail;

import java.util.ArrayList;
import java.util.List;

public final class CommandLineParser {
    private CommandLineParser() {
    }

    public static List<String> parse(String commandLine) {
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        char quote = 0;
        boolean escaped = false;

        for (char character : commandLine.toCharArray()) {
            if (escaped) {
                token.append(character);
                escaped = false;
            } else if (character == '\\') {
                escaped = true;
            } else if (quote != 0) {
                if (character == quote) {
                    quote = 0;
                } else {
                    token.append(character);
                }
            } else if (character == '\'' || character == '"') {
                quote = character;
            } else if (Character.isWhitespace(character)) {
                addToken(tokens, token);
            } else {
                token.append(character);
            }
        }

        if (escaped) {
            token.append('\\');
        }
        if (quote != 0) {
            throw new IllegalArgumentException("Unclosed quote in command");
        }
        addToken(tokens, token);
        return tokens;
    }

    private static void addToken(List<String> tokens, StringBuilder token) {
        if (!token.isEmpty()) {
            tokens.add(token.toString());
            token.setLength(0);
        }
    }
}