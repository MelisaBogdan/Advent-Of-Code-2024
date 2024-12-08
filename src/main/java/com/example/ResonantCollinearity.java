package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ResonantCollinearity {

    public static void main(String[] args) {
        int resultPart1 = solvePart1();
        System.out.println("Puzzle result part 1: " + resultPart1);

        int resultPart2 = solvePart2();
        System.out.println("Puzzle result part 2: " + resultPart2);
    }

    public static int solvePart1() {
        String puzzleInput = readFile("/Users/melisab/Documents/GitHub/Advent-Of-Code-2024/src/main/resources/input8part1.txt");
        if (puzzleInput == null) {
            return 0;
        }

        String[] lines = puzzleInput.strip().split("\n");
        int height = lines.length;
        int width = lines[0].length();

        Map<Character, List<int[]>> antennaPositions = new HashMap<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char ch = lines[y].charAt(x);
                if (Character.isLetterOrDigit(ch)) {
                    antennaPositions.computeIfAbsent(ch, k -> new ArrayList<>()).add(new int[]{x, y});
                }
            }
        }

        Set<List<Integer>> antinodeLocations = new HashSet<>();

        for (Map.Entry<Character, List<int[]>> entry : antennaPositions.entrySet()) {
            List<int[]> positions = entry.getValue();
            int n = positions.size();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int[] pos1 = positions.get(i);
                    int[] pos2 = positions.get(j);

                    int dx = pos2[0] - pos1[0];
                    int dy = pos2[1] - pos1[1];

                    int antinode1X = pos1[0] - dx;
                    int antinode1Y = pos1[1] - dy;
                    int antinode2X = pos2[0] + dx;
                    int antinode2Y = pos2[1] + dy;

                    if (0 <= antinode1X && antinode1X < width && 0 <= antinode1Y && antinode1Y < height) {
                        antinodeLocations.add(Arrays.asList(antinode1X, antinode1Y));
                    }

                    if (0 <= antinode2X && antinode2X < width && 0 <= antinode2Y && antinode2Y < height) {
                        antinodeLocations.add(Arrays.asList(antinode2X, antinode2Y));
                    }
                }
            }
        }

        return antinodeLocations.size();
    }

    public static int solvePart2() {
        String inputMap = readFile("/Users/melisab/Documents/GitHub/Advent-Of-Code-2024/src/main/resources/input8part1.txt");
        if (inputMap == null) {
            return 0;
        }

        String[] lines = inputMap.strip().split("\n");
        int height = lines.length;
        int width = lines[0].length();

        Map<Character, List<int[]>> antennas = new HashMap<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char ch = lines[y].charAt(x);
                if (Character.isLetterOrDigit(ch)) {
                    antennas.computeIfAbsent(ch, k -> new ArrayList<>()).add(new int[]{x, y});
                }
            }
        }

        Set<List<Integer>> antinodes = new HashSet<>();

        for (Map.Entry<Character, List<int[]>> entry : antennas.entrySet()) {
            List<int[]> locations = entry.getValue();
            for (int i = 0; i < locations.size(); i++) {
                int[] pos1 = locations.get(i);
                for (int j = 0; j < locations.size(); j++) {
                    if (i == j) continue;

                    int[] pos2 = locations.get(j);
                    int dx = pos2[0] - pos1[0];
                    int dy = pos2[1] - pos1[1];

                    for (int k = -width; k < width; k++) {
                        int ax = pos1[0] + k * dx;
                        int ay = pos1[1] + k * dy;

                        if (0 <= ax && ax < width && 0 <= ay && ay < height) {
                            antinodes.add(Arrays.asList(ax, ay));
                        }
                    }
                }
            }
        }

        return antinodes.size();
    }

    private static String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}