package org.example;
import java.util.Scanner;

public class ConvertAmount {
    public static void main(String[] args) {
        final double ROUBLES_PER_DOLLAR = 72.12;

        int dollars;
        double roubles;

        Scanner input = new Scanner(System.in);

        // Получить сумму денег в американских долларах
        System.out.print("Введите сумму денег в американских долларах: ");
        dollars = input.nextInt();

        // Конвертировать сумму денег в российские рубли
        roubles = ROUBLES_PER_DOLLAR * dollars;

        // Отобразить сумму денег в российских рублях в пользу покупателя
        System.out.println("Она равна " + (int)(roubles * 100) / 100.0
                + " российского рубля.");
    }
}