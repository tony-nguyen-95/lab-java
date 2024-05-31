package services;

import java.util.*;

public class NormalizeTextService {

    private static final List<Character> Punctuations = Arrays.asList('(', '“');
    private static final List<Character> FullStopPunctuations = Arrays.asList('.', ':', ',', '!', ')', '?', '”');

    public static String normalizeString(String input) {
        StringBuilder builder = new StringBuilder();
        String normalizedInput = normalizeInput(input);

        boolean newParagraph = true;
        int inputLength = normalizedInput.length();
        char prevChar, nextChar;

        for (int index = 0; index < inputLength; index++) {
            char currentChar = normalizedInput.charAt(index);

            // Remove newlines in the middle of a paragraph
            if (currentChar == '\n') {
                if (index > 0) {
                    prevChar = normalizedInput.charAt(index - 1);
                    if (!FullStopPunctuations.contains(prevChar)) {
                        continue;
                    }
                }
            }

            // First char of the input or a new paragraph starts with uppercase
            if (newParagraph) {
                currentChar = Character.toUpperCase(currentChar);
                builder.append(currentChar);
                newParagraph = false;
                continue;
            }

            // If currentChar is a full stop punctuation, remove preceding whitespace and
            // add following whitespace
            if (FullStopPunctuations.contains(currentChar)) {
                // Remove preceding whitespace
                if (index > 0) {
                    prevChar = normalizedInput.charAt(index - 1);
                    if (Character.isWhitespace(prevChar)) {
                        builder.deleteCharAt(builder.length() - 1);
                    }
                }
                // Append FullStopPunctuation
                builder.append(currentChar);

                // Add following whitespace if needed
                if (index < inputLength - 1) {
                    nextChar = normalizedInput.charAt(index + 1);
                    if (!Character.isWhitespace(nextChar) && !FullStopPunctuations.contains(nextChar)) {
                        builder.append(" ");
                    }
                }

                continue;
            }

            // Else if currentChar is a normal punctuation, add preceding whitespace and
            // remove following whitespace
            if (Punctuations.contains(currentChar)) {
                // Add preceding whitespace if needed
                if (index > 0) {
                    prevChar = normalizedInput.charAt(index - 1);
                    if (!Character.isWhitespace(prevChar)) {
                        builder.append(" ");
                    }
                }
                // Append Punctuation
                builder.append(currentChar);
                continue;
            }

            if (Character.isWhitespace(currentChar) && index > 0) {
                prevChar = normalizedInput.charAt(index - 1);
                if (Punctuations.contains(prevChar)) {
                    continue;
                }
            }

            // After build
            if (Character.isWhitespace(builder.charAt(builder.length() - 1))
                    && builder.charAt(builder.length() - 2) == '.') {
                builder.append(Character.toUpperCase(currentChar));
                continue;
            }

            // All others, just append it
            builder.append(currentChar);
        }

        // Make sure the file ends with '.'
        if (builder.length() > 0 && builder.charAt(builder.length() - 1) != '.') {
            builder.append('.');
        }

        return builder.toString();
    }

    private static String normalizeInput(String input) {
        String newInput = input.trim();
        // Make sure there's just one whitespace between words
        return newInput.replaceAll("\\s+", " ");
    }

}
