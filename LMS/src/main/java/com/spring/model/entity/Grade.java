package com.spring.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gradeId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Assessment assessment;

    @ManyToOne
    @JoinColumn( nullable = false)
    private User student;

    private Float grade;

    @Column(columnDefinition = "TEXT")
    private String feedback;
}
