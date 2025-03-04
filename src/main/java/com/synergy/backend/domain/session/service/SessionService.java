package com.synergy.backend.domain.session.service;

import com.synergy.backend.domain.session.dto.SessionDetailResDto;
import com.synergy.backend.domain.session.dto.SessionReqDto;
import com.synergy.backend.domain.session.dto.SessionResDto;
import com.synergy.backend.domain.session.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface SessionService {

    void createSession(SessionReqDto reqDto);

    Page<SessionResDto> getSessions();

    SessionDetailResDto getSessionInfo(String sessionId);

    void updateSession(String sessionId, SessionReqDto reqDto);

    void deleteSession(String sessionId);
}
