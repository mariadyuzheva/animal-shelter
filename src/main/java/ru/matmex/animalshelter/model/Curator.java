package ru.matmex.animalshelter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Curator {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String phone;
//    private List<Animal> animals;

    protected Curator() {}

    public Curator(String name, String phone) {
        this.name = name;
        this.phone = phone;
//        this.animals = animals;
    }

    @Override
    public String toString() {
        return String.format(
                "Curatorl[id=%d, name='%s', phone='%s']",
                id, name, phone);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

//    public List<Animal> getAnimals() {
//        return animals;
//    }

}