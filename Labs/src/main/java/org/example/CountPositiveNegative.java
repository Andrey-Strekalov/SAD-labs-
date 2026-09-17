package org.example;
import java.util.Scanner;

public class CountPositiveNegative {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int positiveCount = 0;
        int negativeCount = 0;
        double value;

        System.out.println("Вводите числа (0 — для завершения):");

        while (true) {
            System.out.print("Введите число: ");
            value = scanner.nextDouble();

            if (value == 0) {
                break;
            } else if (value > 0) {
                positiveCount++;
            } else {
                negativeCount++;
            }
        }

        System.out.println("\nКоличество положительных чисел: " + positiveCount);
        System.out.println("Количество отрицательных чисел: " + negativeCount);

        scanner.close();
    }
}