package org.example;

public class PopulationGrowth {
    public static void main(String[] args) {
        double population = 9870;
        final double GROWTH_RATE = 0.10;
        final int TARGET = 30000;

        int countYears = 0;

        System.out.println("Ежегодная численность населения:");


        while (population <= TARGET) {
            population = population * (1 + GROWTH_RATE);
            countYears++;
            System.out.printf("Год %d: %.0f чел.%n", countYears, population);
        }

        System.out.println("\nНаселение превысит 30 000 человек через "
                + countYears + " лет.");
    }
}
