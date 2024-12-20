package com.spring.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendanceId;

    @ManyToOne
    @JoinColumn( nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn( nullable = false)
    private User student;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date attendanceDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;


    public enum Status {
        PRESENT, ABSENT
    }
}

