package com.spring.model.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationDto {

    private int notificationId;
    private int userId;
    private String content;
    private boolean isRead;


}
