package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DiskFragmenter {
    public int result() {
        String input = readFileAndPrint();
        if (input == null) {
            return -1; 
        }

        List<Integer> disk = new ArrayList<>();
        int fileId = 0;

        for (int i = 0; i < input.length(); i++) {
            int x = Character.getNumericValue(input.charAt(i));
            if (i % 2 == 0) {
                for (int j = 0; j < x; j++) {
                    disk.add(fileId);
                }
                fileId++;
            } else {
                for (int j = 0; j < x; j++) {
                    disk.add(-1);
                }
            }
        }

        List<Integer> blanks = new ArrayList<>();
        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == -1) {
                blanks.add(i);
            }
        }

        for (int i : blanks) {
            while (disk.get(disk.size() - 1) == -1) {
                disk.remove(disk.size() - 1);
            }
            if (disk.size() <= i) {
                break;
            }
            disk.set(i, disk.remove(disk.size() - 1));
        }

        int sum = 0;
        for (int i = 0; i < disk.size(); i++) {
            sum += i * disk.get(i);
        }

        return sum;
    }

    public static String readFileAndPrint() {
        String fileName = "/Users/melisab/Documents/GitHub/Advent-Of-Code-2024/src/main/resources/input9part1.txt";
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
