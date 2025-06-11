package com.example.java_ifortex_test_task.repository;

import com.example.java_ifortex_test_task.entity.DeviceType;
import com.example.java_ifortex_test_task.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query(value = "SELECT * FROM session WHERE device_type = :deviceType ORDER BY start_date ASC LIMIT 1", nativeQuery = true)
    Session getFirstDesktopSession(DeviceType deviceType);

    @Query(value = "SELECT s.* FROM session s JOIN user u ON s.user_id = u.id WHERE u.is_active = true AND s.end_date < '2025-01-01 00:00:00' ORDER BY s.started_at_utc DESC", nativeQuery = true)
    List<Session> getSessionsFromActiveUsersEndedBefore2025(LocalDateTime endDate);
}