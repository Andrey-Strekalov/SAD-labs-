package org.example;

import java.time.LocalDate;

public class Rating {
    private final int value;        // 1..5
    private final String comment;   // может быть пустым
    private final LocalDate date;

    public Rating(int value, String comment, LocalDate date) {
        if (value < 1 || value > 5)
            throw new IllegalArgumentException("Оценка должна быть от 1 до 5, получено: " + value);
        if (date == null)
            throw new IllegalArgumentException("Дата оценки не может быть null");
        this.value = value;
        this.comment = comment == null ? "" : comment;
        this.date = date;
    }

    public int getValue() { return value; }
    public String getComment() { return comment; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return value + "/5" + (comment.isEmpty() ? "" : " («" + comment + "»)") + " от " + date;
    }
}