package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GuardGallivant {
    public int result() {
        String input = readFileAndPrint();
        return 0;
    }

    public int result_part2() {
        String input = readFileAndPrint();
        return 0;
    }

    public static String readFileAndPrint() {
        String fileName = "/Users/melisab/Documents/GitHub/Advent-Of-Code-2024/src/main/resources/input3part1.txt";
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}