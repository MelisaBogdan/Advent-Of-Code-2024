package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedNosedReports {
    public int result() {
        List<int[]> input = readFile("/Users/melisab/Documents/GitHub/Advent-Of-Code-2024/src/main/resources/input2part1.txt");
        // check rule 1 increasing or decreasing
        int sum = 0;
        for (int i = 0; i <input.size(); i++) {
            sum += rule1(input.get(i));
        }
        // check rule 2 numbers differ by at least one and at most three
        return sum; 
    }
    public int result_part2() {
        return 0;
    }
    private static List<int[]> readFile(String fileName) {
        List<int[]> pairs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                int[] pair = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    pair[i] = Integer.parseInt(parts[i]);
                }
                pairs.add(pair);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pairs;
    }

    private static int rule1(int[] array) {
        int safe = 1;
        boolean isIncreasing = array[0] < array[1];

        for (int i = 0; i < array.length - 1; i++) {
            int diff = array[i + 1] - array[i];
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                safe = 0;
                break;
            }
            if (isIncreasing && diff < 0) {
                safe = 0;
                break;
            }
            if (!isIncreasing && diff > 0) {
                safe = 0;
                break;
            }
        }
        return safe;
    }
}