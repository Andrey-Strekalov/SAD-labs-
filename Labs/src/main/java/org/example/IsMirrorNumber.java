package org.example;
import java.util.Scanner;

public class IsMirrorNumber {
    public static void main(String[] args) {
        int number, number1, number2, number3;
        String report = "Нет одинаковых чисел.";

        Scanner input= new Scanner(System.in);
        System.out.print("Введите трехзначное число: ");
        number = input.nextInt();

        if (number < 100 || number > 999) {
            System.out.println("Вы ввели не трехзначное число");
            return;
        }

        number1 = number / 100;
        number2 = number % 100 / 10;
        number3 = number % 10;

        if (number1 == number3) {
            report = "Первое и третье числа одинаковые. ";
        }
        if (number1 == number2) {
            report += " Первое и второе числа одинаковые.";
        }
        if (number2 == number3) {
            report += " Второе и третье числа одинаковые.";
        }
        if(number1 == number2 && number2 == number3){
            report = " Все числа одинаковые.";
        }

        System.out.println(report);
    }
}
