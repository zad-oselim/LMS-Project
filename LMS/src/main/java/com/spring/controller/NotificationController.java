package com.spring.controller;

import com.spring.model.dto.NotificationDto;
import com.spring.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{notificationId}")
    public NotificationDto getNotification(@PathVariable int notificationId) {
        return notificationService.getNotificationById(notificationId);
    }

    @GetMapping("/user/{userId}")
    public List<NotificationDto> getNotificationsByUserId(@PathVariable int userId) {
        return notificationService.getNotificationsByUserId(userId);
    }

    @GetMapping("/user/{userId}/unread")
    public List<NotificationDto> getUnreadNotifications(@PathVariable int userId) {
        return notificationService.getUnreadNotificationsByUserId(userId);
    }

    @PostMapping
    public NotificationDto createNotification(@RequestBody NotificationDto notificationDto) {
        return notificationService.createNotification(notificationDto);
    }

    @PutMapping("/{notificationId}/read")
    public NotificationDto markAsRead(@PathVariable int notificationId) {
        return notificationService.markAsRead(notificationId);
    }

    @DeleteMapping("/{notificationId}")
    public void deleteNotification(@PathVariable int notificationId) {
        notificationService.deleteNotification(notificationId);
    }
}
