package org.example;
import java.util.Scanner;

public class WindSpeed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите скорость ветра (м/с): ");
        double speed = scanner.nextDouble();

        String result;

        if (speed >= 1 && speed <= 4) {
            result = "слабый (1)";
        } else if (speed >= 5 && speed <= 10) {
            result = "умеренный (2)";
        } else if (speed >= 9 && speed <= 18) {
            result = "сильный (3)";
        } else if (speed > 19) {
            result = "ураганный (4)";
        } else {
            result = "не определён (скорость вне указанных диапазонов)";
        }

        System.out.println("Характер ветра: " + result);
        scanner.close();
    }
}