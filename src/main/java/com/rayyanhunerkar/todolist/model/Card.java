package com.rayyanhunerkar.todolist.model;


import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Builder
@Table(name = "cards", schema = "public")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID")
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "deadline", nullable = true)
    private Date deadline;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private State state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @Column(name = "createdOn")
    @CreatedDate
    private Date createdOn;
    @Column(name = "modifiedOn")
    @LastModifiedDate
    private Date modifiedOn;

    public Card(UUID id, String title, String description, Date deadline, State state, User user, Date createdOn, Date modifiedOn) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.state = state;
        this.user = user;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public Card() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id) && Objects.equals(title, card.title) && Objects.equals(description, card.description) && Objects.equals(deadline, card.deadline) && Objects.equals(state, card.state) && Objects.equals(user, card.user) && Objects.equals(createdOn, card.createdOn) && Objects.equals(modifiedOn, card.modifiedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, deadline, state, user, createdOn, modifiedOn);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", state=" + state +
                ", user=" + user +
                ", createdOn=" + createdOn +
                ", modifiedOn=" + modifiedOn +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

}
