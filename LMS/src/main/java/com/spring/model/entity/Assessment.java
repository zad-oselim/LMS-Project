package com.spring.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assessmentId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    @Column(nullable = false, length = 100)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Temporal(TemporalType.DATE)
    private Date dueDate;


    public enum Type {
        QUIZ, ASSIGNMENT
    }
}

