package com.spring.service;

import com.spring.model.dto.NotificationDto;
import com.spring.model.entity.Notification;
import com.spring.model.mapper.EntityDtoMapper;
import com.spring.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final EntityDtoMapper entityDtoMapper;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, EntityDtoMapper entityDtoMapper) {
        this.notificationRepository = notificationRepository;
        this.entityDtoMapper = entityDtoMapper;
    }

    public NotificationDto getNotificationById(int notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new RuntimeException("Notification not found"));
        return entityDtoMapper.notificationToNotificationDto(notification);
    }

    public List<NotificationDto> getNotificationsByUserId(int userId) {
        List<Notification> notifications = notificationRepository.findByUser_UserId(userId);
        return notifications.stream().map(entityDtoMapper::notificationToNotificationDto).toList();
    }

    public List<NotificationDto> getUnreadNotificationsByUserId(int userId) {
        List<Notification> notifications = notificationRepository.findByUser_UserIdAndIsReadFalse(userId);
        return notifications.stream().map(entityDtoMapper::notificationToNotificationDto).toList();
    }

    public NotificationDto createNotification(NotificationDto notificationDto) {
        Notification notification = entityDtoMapper.notificationDtoToNotification(notificationDto);
        notification = notificationRepository.save(notification);
        return entityDtoMapper.notificationToNotificationDto(notification);
    }

    public NotificationDto markAsRead(int notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setIsRead(true);
        notification = notificationRepository.save(notification);
        return entityDtoMapper.notificationToNotificationDto(notification);
    }

    public void deleteNotification(int notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new RuntimeException("Notification not found"));
        notificationRepository.delete(notification);
    }
}