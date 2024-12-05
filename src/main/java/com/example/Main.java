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
    }
}