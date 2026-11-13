package org.example;

public class CardPayment implements PaymentMethod {
    private final String cardNumber;

    public CardPayment(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4)
            throw new IllegalArgumentException("Номер карты должен быть >= 4 символов");
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean pay(double amount) {
        System.out.printf("[Оплата] Карта %s: %.2f руб.%n", mask(cardNumber), amount);
        return true;
    }

    /** Маскирует номер: ****1234 */
    private String mask(String n) {
        return "****" + n.substring(n.length() - 4);
    }

    @Override
    public String toString() { return "Карта " + mask(cardNumber); }
}