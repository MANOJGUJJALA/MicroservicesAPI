package com.myproject.demoproject.beans;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Country")

public class Country {
    @Id
    @Column(name = "id")
    int id;

    @Column(name = "countryname")
    String countryName;

    @Column(name = "countrycapital")
    String countryCapital;

    public Country(){

    }
    public Country(int i, String india, String delhi) {
        this.id = i;
        this.countryName=india;
        this.countryCapital=delhi;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }

    public int getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCapital() {
        return countryCapital;
    }


}
