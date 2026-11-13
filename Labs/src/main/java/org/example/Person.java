package org.example;

abstract class Person {
    private final int id;        // ← final: ключ в Map
    private String fullName;

    public Person(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }  // ← оставляем
    // setId УДАЛЁН

    public abstract String getRole();
}