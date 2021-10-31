package ru.matmex.animalshelter.model;

public enum AnimalType {
    DOG("Собака"),
    CAT("Кошка"),
    OTHER("Другой");

    private final String displayValue;

    private AnimalType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
