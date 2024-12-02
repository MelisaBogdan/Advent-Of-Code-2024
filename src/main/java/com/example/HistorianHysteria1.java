package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistorianHysteria1 {
    public int result() {
        Map<String, List<Integer>> arrays = splitIntoArrays();
        List<Integer> array1 = arrays.get("array1");
        Collections.sort(array1);
        List<Integer> array2 = arrays.get("array2");
        Collections.sort(array2);

        int diff = 0;
        for (int i = 0; i < array1.size(); i++) {
            diff += Math.abs(array1.get(i) - array2.get(i));
        }
        return diff; 
    }
    public int result_part2() {
        return totalSimilarityScore(splitIntoArrays().get("array1"), splitIntoArrays().get("array2"));
    }

    public static List<int[]> readFileAndPrint() {
            List<int[]> pairs = new ArrayList<>();
            String fileName = "/Users/melisab/Documents/GitHub/Advent-Of-Code-2024/src/main/resources/input1part1.txt";
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.trim().split("\s+");
                    int[] pair = {Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
                    pairs.add(pair);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return pairs;
        }
    
        private static String splitInput(String input) {
            return input.split("\n")[0];
        }
        private static Map<String, List<Integer>> splitIntoArrays(){
            List<int[]> input = readFileAndPrint();
            List<Integer> array1 = new ArrayList<>();
            List<Integer> array2 = new ArrayList<>();
            
            for (int[] pair : input) {
                array1.add(pair[0]);
                array2.add(pair[1]);
            }

            Map<String, List<Integer>> arrays = new HashMap<>();
            arrays.put("array1", array1);
            arrays.put("array2", array2);

            return arrays;
        }

        private static int totalSimilarityScore(List<Integer> array1, List<Integer> array2) {
            // count in a hash map the number of times each unique number shows up
            Map<Integer, Integer> occuranceHashMap = new HashMap<>();
            for (int i = 0; i < array1.size(); i++) {
                occuranceHashMap.put(array1.get(i), occuranceHashMap.getOrDefault(array1.get(i), 0) + 1);
            }
    
            // do the sum and search for the key in the map
            int similarityScore = 0;
            for (int i = 0; i < array2.size(); i++) {
                similarityScore += array2.get(i) * occuranceHashMap.getOrDefault(array2.get(i), 0);
            }
    
            return similarityScore;
        }
}