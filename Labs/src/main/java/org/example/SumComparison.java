package org.example;
import java.util.Scanner;

public class SumComparison {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Введите число n: ");
        int n = input.nextInt();


        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }


        int result = (n * (n + 1)) / 2;


        if (sum == result) {
            System.out.println("Сумма " + sum + " эквивалентна формуле " + result + ".");
        } else {
            System.out.println("Сумма " + sum + " НЕ эквивалентна формуле " + result + ".");
        }

        input.close();
    }
}
