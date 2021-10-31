package ru.matmex.animalshelter.model;

import javax.persistence.*;

@Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String clinicName;

    @OneToOne
    private Address clinicAddress;

    private String clinicPhone;

    protected Clinic() {}

    public Clinic(String clinicName, Address clinicAddress, String clinicPhone) {
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
        this.clinicPhone = clinicPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public Address getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(Address clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public String getClinicPhone() {
        return clinicPhone;
    }

    public void setClinicPhone(String clinicPhone) {
        this.clinicPhone = clinicPhone;
    }
}
