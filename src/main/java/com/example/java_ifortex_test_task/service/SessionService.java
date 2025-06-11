package com.example.java_ifortex_test_task.service;

import com.example.java_ifortex_test_task.dto.SessionResponseDTO;
import com.example.java_ifortex_test_task.entity.DeviceType;
import com.example.java_ifortex_test_task.entity.Session;
import com.example.java_ifortex_test_task.mapper.SessionMapper;
import com.example.java_ifortex_test_task.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    // Returns the first (earliest) desktop Session
    public SessionResponseDTO getFirstDesktopSession() {
        Session session = sessionRepository.getFirstDesktopSession(DeviceType.DESKTOP);
        if (session == null) {
            throw new IllegalStateException("No desktop sessions found");
        }
        return sessionMapper.toDto(session);
    }

    // Returns only Sessions from Active users that were ended before 2025
    public List<SessionResponseDTO> getSessionsFromActiveUsersEndedBefore2025() {
        LocalDateTime endOf2024 = LocalDateTime.of(2024, 12, 31, 23, 59, 59);
        List<Session> sessions = sessionRepository.getSessionsFromActiveUsersEndedBefore2025(endOf2024);

        return sessions.stream()
                .map(sessionMapper::toDto)
                .collect(Collectors.toList());
    }
}
