package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static final int PL = 1;
    public static final int ENG = 0;

    public static List<String> getAllWords(int language) {
        List<String[]> linesFromFile = getLinesFromFile();
        List<String> words = new ArrayList<>();
        switch (language) {
            case PL: {
                linesFromFile.forEach(line -> {
                    String wordLanguage = line[0];
                    String word = line[1];
                    if (wordLanguage.matches("pl")) words.add(word);
                });
                break;
            }
            case ENG: {
                linesFromFile.forEach(line -> {
                    String wordLanguage = line[0];
                    String word = line[1];
                    if (wordLanguage.matches("eng")) words.add(word);
                });
                break;
            }
            default: {
                linesFromFile.forEach(line -> {
                    String word = line[1];
                    words.add(word);
                });
                break;
            }
        }
        return words;
    }

    public static List<String[]> getLinesFromFile() {
        List<String> readLines = null;
        try {
            //TODO - niech kazdy zmieni sobie patha na swojego
            readLines = Files.readAllLines(Paths.get("E:\\Repozytoria\\javaprojectwww\\src\\main\\resources\\db.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String[]> splittedLines = new ArrayList<>();
        readLines.forEach(line -> {
            String[] strings = line.split(",");
            splittedLines.add(strings);
        });
        return splittedLines;
    }

    public static String[] getWordDetails(String word) {
        List<String[]> linesFromFile = getLinesFromFile();
        for (String[] line : linesFromFile) {
            if (line[1].matches(word)) return line;
        }
        return null;
    }

    public static void deleteWordFromFile(String word) {
        List<String[]> linesFromFile = getLinesFromFile();
        for (String[] line : linesFromFile) {
            if (line[1].matches(word)) {
                linesFromFile.remove(line);
                break;
            }
        }

        File file = new File("E:\\Repozytoria\\javaprojectwww\\src\\main\\resources\\db.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
            for (String[] line : linesFromFile) {
                writer.write(line[0] + "," + line[1] + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
