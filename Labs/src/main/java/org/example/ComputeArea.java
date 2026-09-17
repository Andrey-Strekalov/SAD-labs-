package org.example;

import java.util.Scanner;

public class ComputeArea {
    public static void main(String[] args) {
        double radius;
        System.out.print("Введите радиус круга: ");
        Scanner input = new Scanner(System.in);
        radius = input.nextDouble();
        if (radius >= 0) {
            double area = radius * radius * Math.PI;
            System.out.println("Площадь круга с радуисом " + radius + " равна " + area);
        } else {
            System.out.println("Введенное значение меньше нуля");
        }
    }
}
