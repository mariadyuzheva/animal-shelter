package ru.matmex.animalshelter.model;

import javax.persistence.*;

@Entity
public class Curator {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String curatorName;
    private String curatorPhone;

    protected Curator() {}

    public Curator(String curatorName, String curatorPhone) {
        this.curatorName = curatorName;
        this.curatorPhone = curatorPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCuratorName() {
        return curatorName;
    }

    public void setCuratorName(String curatorName) {
        this.curatorName = curatorName;
    }

    public String getCuratorPhone() {
        return curatorPhone;
    }

    public void setCuratorPhone(String curatorPhone) {
        this.curatorPhone = curatorPhone;
    }
}