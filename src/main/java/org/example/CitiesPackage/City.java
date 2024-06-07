package org.example.CitiesPackage;


import java.io.Serializable;

public class City implements Serializable {
    private int id;
    private String name;
    private String creationDate;
    private float area;
    private long population;
    private double metersAboveSeaLevel;
    private long telephoneCode;
    private long carCode;
    private StandardOfLiving standardOfLiving;
    private Human governor;
    private int clinet_id;



    public City() {
    }
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


   public void setClinet_id(int clinet_id) {
        this.clinet_id = clinet_id;
   }

   public int getClinet_id() {
        return clinet_id;
   }

    public String getCreationDate() {

        return creationDate;
    }

    public void setCreationDate(String creationDate) {

        this.creationDate = creationDate;
    }

    public Float getArea() {

        return area;
    }

    public void setArea(Float area) {

        this.area = area;
    }

    public Long getPopulation() {

        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public double getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(double metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public long getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(long telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    public Long getCarCode() {
        return carCode;
    }

    public void setCarCode(Long carCode) {
        this.carCode = carCode;
    }

    public StandardOfLiving getStandardOfLiving() {
        return standardOfLiving;
    }

    public void setStandardOfLiving(StandardOfLiving standardOfLiving) {
        this.standardOfLiving = standardOfLiving;
    }

    public Human getGovernor() {
        return governor;
    }

    public void setGovernor(Human governor) {
        this.governor = governor;
    }
    @Override
    public String toString() {
        return this.name ;
    }
}