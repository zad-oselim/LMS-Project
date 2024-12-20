package com.spring.repository;

import com.spring.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findByUser_UserId(int userId);      // Custom query to find notifications by user ID
    List<Notification> findByUser_UserIdAndIsReadFalse(int userId);
}
