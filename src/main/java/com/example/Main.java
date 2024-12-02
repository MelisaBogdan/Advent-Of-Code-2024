package com.example;

public class Main {
    public static void main(String[] args) {
        HistorianHysteria1 historian = new HistorianHysteria1();
        int result = historian.result();
        int result2 = historian.result_part2();
        System.out.println("Day 1 Advent of Code: " + result + " " + result2);
    }
}