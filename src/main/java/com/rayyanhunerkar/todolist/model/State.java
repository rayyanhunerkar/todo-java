package com.rayyanhunerkar.todolist.model;


import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Builder
@Table(name = "states", schema = "public")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID")
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "createdOn")
    @CreatedDate
    private Date createdOn;
    @Column(name = "modifiedOn")
    @LastModifiedDate
    private Date modifiedOn;

    public State() {
    }

    public State(UUID id, String name, String description, Date createdOn, Date modifiedOn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", modifiedOn=" + modifiedOn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(id, state.id) && Objects.equals(name, state.name) && Objects.equals(description, state.description) && Objects.equals(createdOn, state.createdOn) && Objects.equals(modifiedOn, state.modifiedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, createdOn, modifiedOn);
    }
}
