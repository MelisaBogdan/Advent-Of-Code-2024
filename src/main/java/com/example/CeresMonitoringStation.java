package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CeresMonitoringStation {
    public int result() {
        String input = readFile();
        char[][] grid = parseInput(input);
        return countXMASOccurrences(grid);
    }

    public int result_part2() {
        return 0; // I AM NOT DOING THIS THING ALL OVER AGAIN NO
    }

    private String readFile() {
        String fileName = "/Users/melisab/Documents/GitHub/Advent-Of-Code-2024/src/main/resources/input4part1.txt";
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private char[][] parseInput(String input) {
        String[] lines = input.split("\n");
        char[][] grid = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            grid[i] = lines[i].toCharArray();
        }
        return grid;
    }

    private int countXMASOccurrences(char[][] grid) {
        int count = 0;
        int[][] directions = {
            {0, 1}, {1, 0}, {1, 1}, {1, -1}, 
            {0, -1}, {-1, 0}, {-1, -1}, {-1, 1} 
        };

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                for (int[] dir : directions) {
                    if (checkWord(grid, r, c, dir[0], dir[1])) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private boolean checkWord(char[][] grid, int r, int c, int dr, int dc) {
        String word = "XMAS";
        for (int i = 0; i < word.length(); i++) {
            int nr = r + i * dr;
            int nc = c + i * dc;
            if (nr < 0 || nr >= grid.length || nc < 0 || nc >= grid[0].length || grid[nr][nc] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
