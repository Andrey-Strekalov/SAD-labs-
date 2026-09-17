package org.example;
import java.util.Scanner;

public class CostTaxi {
    public static void main(String[] args) {
        int initialdata, finaldata, distance;
        double costoftravel;
        final double COST_T=8.50;
        Scanner input = new Scanner(System.in);
        // Введите начальные показания одометра
        System.out.print("Введите начальные показания одометра: ");
        initialdata = input.nextInt();
        // Введите начальные показания одометра
        System.out.print("Введите конечные показания одометра: ");
        finaldata = input.nextInt();
        // Расчет проезда в км
        distance = finaldata-initialdata;
        //Расчет стоимости проезда
        costoftravel = distance*COST_T;
        // Отобразить результат поездки
        System.out.println("Вы проехали " + distance + " км." + " Из расчета 8 руб. 50 коп. за км," +
                "стоимость проезда равна "+ (int)costoftravel+ " руб. " +
                        (int)((costoftravel-(int)costoftravel)*100) +" коп.");
    }
}