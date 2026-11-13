package org.example;

public interface PaymentMethod {
    /**
     * Оплатить указанную сумму.
     * @return true — оплата прошла успешно; false — отказ (например, недостаточно средств).
     */
    boolean pay(double amount);
}