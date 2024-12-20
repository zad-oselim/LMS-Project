package com.spring.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Notifications")
@Setter
@Getter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;

    @ManyToOne
    @JoinColumn( nullable = false)
    private User user;

    @Column( nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean isRead = false;


}
