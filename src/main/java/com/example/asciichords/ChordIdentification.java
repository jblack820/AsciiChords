package com.example.asciichords;

import java.util.*;

public class ChordIdentification {

    static List<String> noteList = List.of("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");



    public static String getChordName(List<String> textListFromBoxes) {
        Set<String> distinctChordNotes = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            String currentText = textListFromBoxes.get(i);
            if (!currentText.equalsIgnoreCase("X")) {
                distinctChordNotes.add(getNote(i, currentText));
            }

        }
        return getTriadChordName(distinctChordNotes);
    }

    private static String getTriadChordName(Set<String> distinctChordNotes) {
        System.out.println("Distinct chord notes: "+ distinctChordNotes.toString());
        if (distinctChordNotes.size()!=3){return "Not triad chord.";}
        List<Integer> notesIndexes = getNotesIndexes(distinctChordNotes);
        Collections.sort(notesIndexes);
        notesIndexes.add(notesIndexes.get(0)+12);
        notesIndexes.add(notesIndexes.get(1)+12);

        for (int i = 0; i < 3; i++) {
            if (isMinorChord(notesIndexes.get(i), notesIndexes.get(i+1), notesIndexes.get(i+2))){
                return noteList.get(notesIndexes.get(i))+" minor";
            }
            if (isMajorChord(notesIndexes.get(i), notesIndexes.get(i+1), notesIndexes.get(i+2))){
                return noteList.get(notesIndexes.get(i))+" major";
            }
        }
        return "Not triad chord.";
    }

    private static boolean isMajorChord(Integer integer, Integer integer1, Integer integer2) {
        return integer1 - integer == 4 && integer2 - integer == 7;
    }

    private static boolean isMinorChord(Integer integer, Integer integer1, Integer integer2) {
        return integer1 - integer == 3 && integer2 - integer == 7;
    }


    private static List<Integer> getNotesIndexes(Set<String> distinctChordNotes) {
        List<Integer> notesIndexes = new ArrayList<>();
        for (String note : distinctChordNotes) {
            notesIndexes.add(getIndexOfNote(note));
        }
        return notesIndexes;
    }
    private static String getNote(int i, String fretNumber) {
        String stringBaseNote = getBaseNoteOFString(i);
        return getActualNote(stringBaseNote, fretNumber);
    }

    private static String getActualNote(String stringBaseNote, String fretNumber) {
        int indexOfBaseNote = getIndexOfNote(stringBaseNote);
        return getNoteBasedOnStringAndFret(indexOfBaseNote, fretNumber);
    }

    private static String getNoteBasedOnStringAndFret(int indexOfStringBaseNote, String fretNumber) {
        int fretNumberIndex = Integer.parseInt(fretNumber);
        while (fretNumberIndex>=12){
            fretNumberIndex-=12;
        }
        int noteIndex = indexOfStringBaseNote+fretNumberIndex;
        if (noteIndex>=12){
            noteIndex-=12;
        }

        return noteList.get(noteIndex);
    }

    private static int getIndexOfNote(String note) {
        int result = 0;
        for (int i = 0; i < noteList.size(); i++) {
            if (note.equalsIgnoreCase(noteList.get(i))){
                result = i;
                break;
            }
        }
        return result;
    }

    private static String getBaseNoteOFString(int i) {
        return switch (i) {
            case 0, 5 -> "E";
            case 1 -> "B";
            case 2 -> "G";
            case 3 -> "D";
            default -> "A";
        };
    }
}
