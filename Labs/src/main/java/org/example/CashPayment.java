package org.example;

public class CashPayment implements PaymentMethod {
    @Override
    public boolean pay(double amount) {
        System.out.printf("[Оплата] Наличными: %.2f руб.%n", amount);
        return true;
    }

    @Override
    public String toString() { return "Наличные"; }
}