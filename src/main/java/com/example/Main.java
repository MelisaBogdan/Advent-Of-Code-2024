package com.example;

public class Main {
    public static void main(String[] args) {
        HistorianHysteria1 historian = new HistorianHysteria1();
        int result_day1 = historian.result();
        int result2_day1 = historian.result_part2();
        RedNosedReports redNosedReports = new RedNosedReports();
        int result1_day2 = redNosedReports.result();
        System.out.println("Day 1 Advent of Code: " + result_day1 + " " + result2_day1);

        System.out.println("Day 2 Advent of Code: " + result1_day2 + " " );
        NorthPoleRentalShop northPoleRentalShop = new NorthPoleRentalShop();
        int result_day3 = northPoleRentalShop.result();
        int result2_day3 = northPoleRentalShop.result_part2();
        System.out.println("Day 3 Advent of Code: " + result_day3 + " " + result2_day3);

        CeresMonitoringStation ceresMonitoringStation = new CeresMonitoringStation();
        int result_day4 = ceresMonitoringStation.result();
        int result2_day4 = ceresMonitoringStation.result_part2();
        System.out.println("Day 4 Advent of Code: " + result_day4 + " " + result2_day4);

        PrintQueue printQueue = new PrintQueue();
        int result_day5 = printQueue.result();
        int result2_day5 = printQueue.result_part2();
        System.out.println("Day 5 Advent of Code: " + result_day5 + " " + result2_day5);
        
        GuardGallivant guardGallivant = new GuardGallivant();
        int result_day6 = guardGallivant.result();
        int result2_day6 = guardGallivant.result_part2();
        System.out.println("Day 6 Advent of Code: " + result_day6 + " " + result2_day6);

        RopeBridge ropeBridge = new RopeBridge();
        long result_day7 = ropeBridge.result();
        long result2_day7 = ropeBridge.result_part2();
        System.out.println("Day 7 Advent of Code: " + result_day7 + " " + result2_day7);
    }
}