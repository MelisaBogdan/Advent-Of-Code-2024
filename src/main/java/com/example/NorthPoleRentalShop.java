package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NorthPoleRentalShop {
    public int result() {
        String input = readFileAndPrint();
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(input);

        int sum = 0;
        while (matcher.find()) {
            int a = Integer.parseInt(matcher.group(1));
            int b = Integer.parseInt(matcher.group(2));
            sum += a * b;
        }

        return sum;
    }

    public int result_part2() {
        String input = readFileAndPrint();
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)");

        Matcher matcher = pattern.matcher(input);

        int sum = 0;
        boolean mulEnabled = true; 
        
        while (matcher.find()) {
            String match = matcher.group();
            if (match.startsWith("mul") && mulEnabled) {
                int a = Integer.parseInt(matcher.group(1));
                int b = Integer.parseInt(matcher.group(2));
                sum += a * b;
            } else if (match.equals("do()")) {
                mulEnabled = true;
            } else if (match.equals("don't()")) {
                mulEnabled = false;
            }
        }

        return sum;
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