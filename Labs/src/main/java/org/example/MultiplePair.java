package org.example;

import java.util.Scanner;

public class MultiplePair {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a, b;

        System.out.println("Вводите пары целых чисел. Программа завершится, "
                + "когда одно из чисел пары будет кратно другому.");

        while (true) {
            System.out.print("Введите первое число: ");
            while (!input.hasNextInt()) {
                System.out.print("Ошибка! Введите целое число: ");
                input.next();
            }
            a = input.nextInt();

            System.out.print("Введите второе число: ");
            while (!input.hasNextInt()) {
                System.out.print("Ошибка! Введите целое число: ");
                input.next();
            }
            b = input.nextInt();

            // Проверка условия кратности (с защитой от деления на ноль)
            if ((b != 0 && a % b == 0) || (a != 0 && b % a == 0)) {
                System.out.println("Найдена пара: " + a + " и " + b
                        + ". Одно из чисел кратно другому. Завершение.");
                break;
            } else {
                System.out.println("Пара (" + a + ", " + b
                        + ") не удовлетворяет условию. Продолжайте ввод.\n");
            }
        }

        input.close();
        System.out.println("Программа завершена.");
    }
}
