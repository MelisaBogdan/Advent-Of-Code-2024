package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RopeBridge {
    public long result() {
        List<long[]> parsedData = readFileAndParse();
        long totalSum = 0;
        for (long[] array : parsedData) {
            long target = array[0];
            StringBuilder expression = new StringBuilder();
            if (isValidCombination(array, target, expression)) {
                // System.err.println("Valid array: " + arrayToString(array) + " with expression: " + expression.toString());
                totalSum += target;
            }
        }
        // System.err.println("Total sum of valid arrays: " + totalSum);
        return totalSum;
    }

    public long result_part2() {
        List<long[]> parsedData = readFileAndParse();
        long totalSum = 0;
        for (long[] array : parsedData) {
            long target = array[0];
            StringBuilder expression = new StringBuilder();
            if (isValidCombinationWithConcatenation(array, target, expression)) {
                // System.err.println("Valid array: " + arrayToString(array) + " with expression: " + expression.toString());
                totalSum += target;
            }
        }
        // System.err.println("Total sum of valid arrays: " + totalSum);
        return totalSum;
        // return 0;
    }

    private static boolean isValidCombination(long[] array, long target, StringBuilder expression) {
        return isValidCombinationHelper(array, target, 1, array[1], new StringBuilder(Long.toString(array[1])), expression);
    }

    private static boolean isValidCombinationWithConcatenation(long[] array, long target, StringBuilder expression) {
        return isValidCombinationHelperWithConcatenation(array, target, 1, array[1], new StringBuilder(Long.toString(array[1])), expression);
    }

    private static boolean isValidCombinationHelperWithConcatenation(long[] array, long target, int index, long currentValue, StringBuilder currentExpression, StringBuilder finalExpression) {
        if (index == array.length - 1) {
            if (currentValue == target) {
                finalExpression.append(currentExpression);
                return true;
            }
            return false;
        }

        long nextValue = array[index + 1];

        // Try addition
        currentExpression.append(" + ").append(nextValue);
        if (isValidCombinationHelperWithConcatenation(array, target, index + 1, currentValue + nextValue, currentExpression, finalExpression)) {
            return true;
        }
        currentExpression.setLength(currentExpression.length() - (" + " + nextValue).length());

        // Try multiplication
        currentExpression.append(" * ").append(nextValue);
        if (isValidCombinationHelperWithConcatenation(array, target, index + 1, currentValue * nextValue, currentExpression, finalExpression)) {
            return true;
        }
        currentExpression.setLength(currentExpression.length() - (" * " + nextValue).length());

        // Try concatenation
        currentExpression.append(" || ").append(nextValue);
        long concatenatedValue = Long.parseLong(currentValue + "" + nextValue);
        if (isValidCombinationHelperWithConcatenation(array, target, index + 1, concatenatedValue, currentExpression, finalExpression)) {
            return true;
        }
        currentExpression.setLength(currentExpression.length() - (" || " + nextValue).length());

        return false;
    }

    private static boolean isValidCombinationHelper(long[] array, long target, int index, long currentValue, StringBuilder currentExpression, StringBuilder finalExpression) {
        if (index == array.length - 1) {
            if (currentValue == target) {
                finalExpression.append(currentExpression);
                return true;
            }
            return false;
        }

        long nextValue = array[index + 1];

        currentExpression.append(" + ").append(nextValue);
        if (isValidCombinationHelper(array, target, index + 1, currentValue + nextValue, currentExpression, finalExpression)) {
            return true;
        }
        currentExpression.setLength(currentExpression.length() - (" + " + nextValue).length());

        currentExpression.append(" * ").append(nextValue);
        if (isValidCombinationHelper(array, target, index + 1, currentValue * nextValue, currentExpression, finalExpression)) {
            return true;
        }
        currentExpression.setLength(currentExpression.length() - (" * " + nextValue).length());

        return false;
    }

    private static String arrayToString(long[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(array[0]);
        for (int i = 1; i < array.length; i++) {
            sb.append(", ").append(array[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    public static List<long[]> readFileAndParse() {
        String fileName = "/Users/melisab/Documents/GitHub/Advent-Of-Code-2024/src/main/resources/input7part1.txt";
        List<long[]> result = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    long beforeColon = Long.parseLong(parts[0].trim());
                    String[] afterColonStrings = parts[1].trim().split(" ");
                    long[] combinedArray = new long[afterColonStrings.length + 1];
                    combinedArray[0] = beforeColon;
                    for (int i = 0; i < afterColonStrings.length; i++) {
                        combinedArray[i + 1] = Long.parseLong(afterColonStrings[i].trim());
                    }
                    result.add(combinedArray);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
