package ru.matmex.animalshelter.model;

import javax.persistence.*;
import java.util.Base64;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AnimalType type;

    private String breed;
    private Integer age;

    @ManyToOne
    private Clinic clinic;

    @ManyToOne
    private Curator curator;

    @Lob
    @Column(columnDefinition="BLOB")
    private byte[] image;

    protected Animal() {}

    public Animal(
            String name, AnimalType type, String breed, Integer age, Clinic clinic, Curator curator, byte[] image) {
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.age = age;
        this.clinic = clinic;
        this.curator = curator;
        this.image = image;
    }

    @Override
    public String toString() {
        return String.format(
                "Animal[id=%d, name='%s', type='%s', breed='%s', age=%d, curator='%s', clinic='%s']",
                id, name, type, breed, age, curator.getName(), clinic.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AnimalType getType() {
        return type;
    }

    public String getBreed() {
        return breed;
    }

    public Integer getAge() {
        return age;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public Curator getCurator() {
        return curator;
    }

    public byte[] getImage() {
        return image;
    }

    public String getImageBase64() {
        return Base64.getEncoder().encodeToString(image);
    }

}
