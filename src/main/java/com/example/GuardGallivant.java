package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GuardGallivant {
    public int result() {
        char[][] matrix = readFileAndPrint();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            System.out.println("Matrix is empty or not properly initialized.");
            return 0;
        }
        return moveCaret(matrix);
    }

    public int result_part2() {
        return 0;
    }

    public static char[][] readFileAndPrint() {
        String fileName = "/Users/melisab/Documents/GitHub/Advent-Of-Code-2024/src/main/resources/input6part1.txt";
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            if (lines.isEmpty()) {
                System.out.println("Input file is empty.");
                return null;
            }
            int rows = lines.size();
            int cols = lines.get(0).length();
            char[][] matrix = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                matrix[i] = lines.get(i).toCharArray();
            }

            System.out.println("Matrix initialized:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(matrix[i][j]);
                }
                System.out.println();
            }

            return matrix;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int moveCaret(char[][] matrix) {
        int count = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] direction = {-1, 0}; // Initial direction is up
        int x = -1, y = -1;

        // Find the initial position of the caret
        outerLoop:
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '^') {
                    x = i;
                    y = j;
                    // System.out.println("Initial position of ^: (" + x + ", " + y + ")");
                    break outerLoop;
                }
            }
        }

        if (x == -1 || y == -1) {
            // System.out.println("Initial position of ^ not found.");
            return 0;
        }
        // count++;
        // matrix[x][y] = 'X';

        while (true) {
            matrix[x][y] = 'X'; // Mark the current position as visited
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (newX < 0 || newX >= rows || newY < 0 || newY >= cols) {
                // Check if the obstacle or boundary is outside the matrix
                // System.out.println("Encountered obstacle or boundary outside the matrix at: (" + newX + ", " + newY + ")");
                break;
            } else if (matrix[newX][newY] == '#') {
                // Turn right 90 degrees
                // System.out.println("Encountered obstacle or boundary at: (" + newX + ", " + newY + ")");
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (direction[0] == -1 && direction[1] == 0) {
                    direction[0] = 0;
                    direction[1] = 1;
                } else if (direction[0] == 0 && direction[1] == 1) {
                    direction[0] = 1;
                    direction[1] = 0;
                } else if (direction[0] == 1 && direction[1] == 0) {
                    direction[0] = 0;
                    direction[1] = -1;
                } else if (direction[0] == 0 && direction[1] == -1) {
                    direction[0] = -1;
                    direction[1] = 0;
                }
                // System.out.println("New direction: (" + direction[0] + ", " + direction[1] + ")");
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // Move forward
                x = newX;
                y = newY;
                if (matrix[x][y] != 'X') {
                    count++;
                }
                // System.out.println("Moved to position: (" + x + ", " + y + "), Count: " + count);
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Check if the guard has left the mapped area
            if (x < 0 || x >= rows || y < 0 || y >= cols) {
                System.out.println("Guard has left the mapped area.");
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        // Print the final matrix for debugging
        // System.out.println("Final matrix:");
        // for (int i = 0; i < rows; i++) {
        //     for (int j = 0; j < cols; j++) {
        //         System.out.print(matrix[i][j]);
        //     }
        //     System.out.println();
        // }

        return count;
    }
}