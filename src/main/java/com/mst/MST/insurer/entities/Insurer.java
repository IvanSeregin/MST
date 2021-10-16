package com.mst.MST.insurer.entities;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(
        name="insurers",
        uniqueConstraints = {
                @UniqueConstraint(name = "name_unique", columnNames = "name"),
                @UniqueConstraint(name = "code_unique", columnNames = "code")
        })
public class Insurer {
    @Id
    @SequenceGenerator(
            name = "insurer_sequence",
            sequenceName = "insurer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "insurer_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    //--------------------------------
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    //--------------------------------
    @Column(
            name = "code",
            nullable = false
    )
    private String code;
    //--------------------------------

    public Insurer() {
    }

    public Insurer(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insurer insurer = (Insurer) o;
        return getName().equals(insurer.getName()) &&
                getCode().equals(insurer.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCode());
    }
}
