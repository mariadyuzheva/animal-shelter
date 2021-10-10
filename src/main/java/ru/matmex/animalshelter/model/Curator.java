package ru.matmex.animalshelter.model;

import javax.persistence.*;

@Entity
public class Curator {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;
    private String phone;

    protected Curator() {}

    public Curator(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format(
                "Curator[id=%d, name='%s', phone='%s']",
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

}