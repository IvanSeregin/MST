package com.mst.MST.vehicle.entities;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="vehicles")
public class Vehicle {
    @Id
    @SequenceGenerator(
            name = "vehicle_sequence",
            sequenceName = "vehicle_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "vehicle_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    //--------------------------------
    @Column(
            name = "type",
            nullable = false
    )
    private String type;
    //--------------------------------
    @Column(
            name = "make",
            nullable = false
    )
    private String make;
    //--------------------------------
    @Column(
            name = "model",
            nullable = false
    )
    private String model;
    //--------------------------------
    @Column(
            name = "colour",
            nullable = false
    )
    private String colour;
    //--------------------------------


    public Vehicle(Long id, String type, String make, String model, String colour) {
        this.id = id;
        this.type = type;
        this.make = make;
        this.model = model;
        this.colour = colour;
    }

    public Vehicle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return getType().equals(vehicle.getType()) &&
                getMake().equals(vehicle.getMake()) &&
                getModel().equals(vehicle.getModel()) &&
                getColour().equals(vehicle.getColour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getMake(), getModel(), getColour());
    }
}
