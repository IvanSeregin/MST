package com.mst.MST.registration.entities;

import com.mst.MST.insurer.entities.Insurer;
import com.mst.MST.vehicle.entities.Vehicle;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(
        name="registrations",
        uniqueConstraints = {
            @UniqueConstraint(name = "number_plate_unique", columnNames = "number_plate"),
            @UniqueConstraint(name = "vehicle_id_unique", columnNames = "vehicles_id")
        }
)
public class Registration {
    @Id
    @SequenceGenerator(
            name = "registration_sequence",
            sequenceName = "registration_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "registration_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    //--------------------------------
    @Column(
            name = "number_plate",
            nullable = false
    )
    private String numberPlate;
    //--------------------------------
    @OneToOne (cascade = {CascadeType.ALL})
    @JoinColumn(
            name = "vehicles_id",
            nullable=false,
            foreignKey = @ForeignKey(name = "FK_REGISTRATION_VEHICLE")
    )
    private Vehicle vehicle;
    //--------------------------------
    @ManyToOne (cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "insurers_id",
            nullable=false,
            foreignKey = @ForeignKey(name = "FK_REGISTRATION_INSURER")
    )
    private Insurer insurer;

    public Registration() {
    }

    public Registration(Long id, String numberPlate, Vehicle vehicle, Insurer insurer) {
        this.id = id;
        this.numberPlate = numberPlate;
        this.vehicle = vehicle;
        this.insurer = insurer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Insurer getInsurer() {
        return insurer;
    }

    public void setInsurer(Insurer insurer) {
        this.insurer = insurer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return getNumberPlate().equals(that.getNumberPlate()) &&
                getVehicle().equals(that.getVehicle()) &&
                getInsurer().equals(that.getInsurer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumberPlate(), getVehicle(), getInsurer());
    }
}
