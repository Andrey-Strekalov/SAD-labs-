package org.example;

class SeniorDriver extends Driver {
    private int yearsOfExperience;

    public SeniorDriver(int id, String fullName, int yearsOfExperience) {
        super(id, fullName);
        this.yearsOfExperience = yearsOfExperience;
    }
    public int getYearsOfExperience() { return yearsOfExperience; }
    @Override public String getRole() { return "Старший водитель"; }

    @Override
    public String toString() {
        return super.toString() + " [стаж: " + yearsOfExperience + " лет]";
    }
}
