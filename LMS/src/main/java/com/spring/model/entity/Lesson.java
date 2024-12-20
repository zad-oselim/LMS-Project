package com.spring.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lessonId;

    @ManyToOne
    @JoinColumn( nullable = false)
    private Course course;

    @Column(nullable = false, length = 100)
    private String title;

    private String content;

}
