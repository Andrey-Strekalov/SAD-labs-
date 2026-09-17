package org.example;

public class NumberOutput {
    public static void main(String[] args) {
        int peak = 6;

        // от 1 до peak
        for (int i = 1; i <= peak; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }

        // от peak-1 до 1 числа
        for (int i = peak - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
