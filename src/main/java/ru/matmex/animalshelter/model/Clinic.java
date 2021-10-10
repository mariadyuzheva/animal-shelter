package ru.matmex.animalshelter.model;

import javax.persistence.*;

@Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne
    private Address address;

    private String phone;

    protected Clinic() {}

    public Clinic(String name, Address address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format(
                "Clinic[id=%d, name='%s', phone='%s', address='%s']",
                id, name, phone, address.toString());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

}
