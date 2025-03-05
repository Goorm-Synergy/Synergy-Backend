package com.synergy.backend.domain.session.service;

import com.synergy.backend.domain.session.dto.SessionDetailResDto;
import com.synergy.backend.domain.session.dto.SessionReqDto;
import com.synergy.backend.domain.session.dto.SessionResDto;
import com.synergy.backend.domain.session.entity.Session;
import com.synergy.backend.domain.session.exception.NotFoundSession;
import com.synergy.backend.domain.session.repository.SessionRepository;
import com.synergy.backend.domain.session.service.validate.DateTimeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Override
    public void createSession(Long conferenceId, SessionReqDto reqDto) {
        LocalDateTime startTime = DateTimeValidator.isValidLocalDateTime(reqDto.startTime());
        LocalDateTime endTime = DateTimeValidator.isValidLocalDateTime(reqDto.endTime());

        Session session = new Session(reqDto, startTime, endTime);
        // conference와 매핑
        sessionRepository.save(session);
    }

    @Override
    public List<SessionResDto> getSessions(Long conferenceId) {
        // conference 찾기
        return null;
    }

    @Override
    public SessionDetailResDto getSessionInfo(Long sessionId) {
        Session session = ifSessionExists(sessionId);
        return null;

    }

    @Override
    public void updateSession(Long sessionId, SessionReqDto reqDto) {
        Session session = ifSessionExists(sessionId);
        // session에 대한 본인 소지 여부 확인
        LocalDateTime startTime = DateTimeValidator.isValidLocalDateTime(reqDto.startTime());
        LocalDateTime endTime = DateTimeValidator.isValidLocalDateTime(reqDto.endTime());

        session.updateSession(reqDto, startTime, endTime);
    }

    @Override
    public void deleteSession(Long sessionId) {
        Session session = ifSessionExists(sessionId);
        // session에 대한 본인 소지 여부 확인
        sessionRepository.delete(session);
    }

    private Session ifSessionExists(Long sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(NotFoundSession::new);
    }

}
