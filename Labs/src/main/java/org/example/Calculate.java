package org.example;
import java.util.Scanner;

public class Calculate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод переменных
        System.out.print("Введите значение p: ");
        double p = scanner.nextDouble();

        System.out.print("Введите значение z: ");
        double z = scanner.nextDouble();

        System.out.print("Введите значение x: ");
        double x = scanner.nextDouble();

        // 1. Вычисление Y = p + z^2
        double Y = p + Math.pow(z, 2);

        // 2. Вычисление Q
        // Числитель: sin(3π/2) + x
        double numeratorQ = Math.sin(3 * Math.PI / 2) + x;
        // Знаменатель: x + sqrt(|3π/2 + x|)
        double denominatorQ = x + Math.sqrt(Math.abs(3 * Math.PI / 2 + x));
        double Q = numeratorQ / denominatorQ;

        // 3. Вычисление W
        // Первая дробь: sin^2(x) / (x^2 - 4)
        double term1W = Math.pow(Math.sin(x), 2) / (x * x - 4);
        // Вторая дробь: cos(x^2) / ((x - 3)(x - 5))
        double term2W = Math.cos(x * x) / ((x - 3) * (x - 5));
        double W = term1W + term2W;

        // Вывод результатов
        System.out.println("\nРезультаты вычислений:");
        System.out.printf("Y = %.4f\n", Y);
        System.out.printf("Q = %.4f\n", Q);
        System.out.printf("W = %.4f\n", W);

        scanner.close();
    }
}
