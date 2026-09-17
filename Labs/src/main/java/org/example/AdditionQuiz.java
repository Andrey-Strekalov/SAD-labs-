package org.example;
import java.util.Scanner;

public class AdditionQuiz {
    public static void main(String[] args) {
        int number1, number2, answer;
        Scanner input = new Scanner(System.in);
        // Сгенерировать два случайных целых числа number1 и number2
        number1 = (int)(System.currentTimeMillis() % 10);
        number2 = (int)(System.currentTimeMillis() / 10 % 10);
        // Получить ответ на вопрос "Сколько будет number1 + number2?"
        System.out.print(
                "Сколько будет " + number1 + " + " + number2 + "? ");
        answer = input.nextInt();
        // Вычислить и проверить ответ (true/false)
        System.out.println(
                number1 + " + " + number2 + " = " + answer + " это " +
                        (number1 + number2 == answer));
    }
}
