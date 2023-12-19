package com.example.asciichords;

import java.util.List;

public class ChordFactory {


    public static String getChord(List<String> textListFromBoxes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            String currentNote = textListFromBoxes.get(i);
            sb.append(getPrefix(i));
            sb.append(currentNote);
            sb.append(getSuffix(currentNote));
            sb.append("\n");
        }
        return sb.toString();
    }

    private static String getSuffix(String currentNote) {
        return currentNote.length()>1 ? "---" : "----";
    }

    private static String getPrefix(int stringNum) {
        return switch (stringNum) {
            case 1 -> "B\t|----";
            case 2 -> "G\t|----";
            case 3 -> "D\t|----";
            case 4 -> "A\t|----";
            case 0, default -> "E\t|----";
        };
    }
}
