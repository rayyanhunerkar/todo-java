package com.rayyanhunerkar.todolist.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
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
}
