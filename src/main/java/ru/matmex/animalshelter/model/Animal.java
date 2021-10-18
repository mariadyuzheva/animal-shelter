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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Curator getCurator() {
        return curator;
    }

    public void setCurator(Curator curator) {
        this.curator = curator;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageBase64() {
        return Base64.getEncoder().encodeToString(image);
    }

}
