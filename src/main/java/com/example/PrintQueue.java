package com.example;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.*;

public class PrintQueue {
    public int result() {
        Result output = readFileAndPrint();
        List<List<Object>> rules = output.rules;
        List<List<Integer>> otherArrays = output.otherArrays;

        // System.out.println("Rules: " + rules);
        // System.out.println("Other Arrays: " + otherArrays);

        int sum = 0;

        for (List<Integer> otherArray : otherArrays) {
            boolean validArray = true;

            for (int i = 0; i < otherArray.size(); i++) {
                int number = otherArray.get(i);
                boolean valid = false;

                for (List<Object> rule : rules) {
                    int ruleNumber = (int) rule.get(0);
                    List<Integer> after = (List<Integer>) rule.get(1);
                    List<Integer> before = (List<Integer>) rule.get(2);

                    if (number == ruleNumber) {
                        valid = true;
                        for (int j = 0; j < i; j++) {
                            if (!before.contains(otherArray.get(j))) {
                                valid = false;
                                break;
                            }
                        }
                        for (int j = i + 1; j < otherArray.size(); j++) {
                            if (!after.contains(otherArray.get(j))) {
                                valid = false;
                                break;
                            }
                        }
                        break;
                    }
                }

                if (!valid) {
                    validArray = false;
                    break;
                }
            }

            if (validArray) {
                int middleIndex = otherArray.size() / 2;
                sum += otherArray.get(middleIndex);
            }
        }
        return sum;
    }

    public int result_part2() {
        return 0;
    }

    public static Result readFileAndPrint() {
        String fileName = "/Users/melisab/Documents/GitHub/Advent-Of-Code-2024/src/main/resources/input5part1.txt";
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            Map<Integer, List<Integer>> beforeMap = new HashMap<>();
            Map<Integer, List<Integer>> afterMap = new HashMap<>();
            List<List<Integer>> otherArrays = new ArrayList<>();

            boolean readingRules = true;

            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    readingRules = false;
                    continue;
                }

                if (readingRules) {
                    String[] parts = line.split("\\|");
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);

                    beforeMap.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
                    afterMap.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
                } else {
                    List<Integer> numbers = new ArrayList<>();
                    for (String num : line.split(",")) {
                        numbers.add(Integer.parseInt(num.trim()));
                    }
                    otherArrays.add(numbers);
                }
            }

            List<List<Object>> rules = new ArrayList<>();
            for (int key : beforeMap.keySet()) {
                List<Integer> before = beforeMap.get(key);
                List<Integer> after = afterMap.getOrDefault(key, new ArrayList<>());
                rules.add(Arrays.asList(key, after, before));
            }
            // System.out.println(rules);
            // System.out.println(otherArrays);
            return new Result(rules, otherArrays);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class Result {
        public List<List<Object>> rules;
        public List<List<Integer>> otherArrays;

        public Result(List<List<Object>> rules, List<List<Integer>> otherArrays) {
            this.rules = rules;
            this.otherArrays = otherArrays;
        }
        @Override
        public String toString() {
            return rules + " , " + otherArrays;
        }
    }

    public static class Rule {
        public int number;
        public List<Integer> before;
        public List<Integer> after;

        public Rule(int number, List<Integer> before, List<Integer> after) {
            this.number = number;
            this.before = before;
            this.after = after;
        }
        @Override
        public String toString() {
            return number + "," + before + " , " + after;
        }
    }
}