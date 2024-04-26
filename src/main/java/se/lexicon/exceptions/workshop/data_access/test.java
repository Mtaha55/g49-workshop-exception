package se.lexicon.exceptions.workshop.data_access;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test {

    public static List<String> getMaleFirstNames() throws IOException {
        BufferedReader reader = null;
        List<String> names = null;

        try {
            reader = Files.newBufferedReader(Paths.get("firstname_males.txt"));
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } catch (NoSuchFileException e) {
            System.out.println("File not found: " + e.getFile());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return names;
    }

    public static List<String> getFemaleFirstNames() {
        List<String> names = null;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("firstname_female.txt"))) {
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return names;
    }

    public static List<String> getLastNames() throws IOException {
        List<String> names = null;
        BufferedReader reader = null;

        try {
            reader = Files.newBufferedReader(Paths.get("lastnames.txt"));
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return names;
    }

    public static void saveLastNames(List<String> lastNames) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("lastnames.txt"))) {
            for (String toWrite : lastNames) {
                writer.append(toWrite).append(",");
            }
            writer.flush();
        }
    }

    public static void saveFemaleNames(List<String> femaleNames) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_female.txt"))) {
            for (String toWrite : femaleNames) {
                writer.append(toWrite).append(",");
            }
            writer.flush();
        }
    }

    public static void saveMaleNames(List<String> maleNames) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_males.txt"))) {
            for (String toWrite : maleNames) {
                writer.append(toWrite).append(",");
            }
            writer.flush();
        }
    }
}
