package com.synergy.backend.domain.session.service;

import com.synergy.backend.domain.session.dto.SessionDetailResDto;
import com.synergy.backend.domain.session.dto.SessionReqDto;
import com.synergy.backend.domain.session.dto.SessionResDto;
import org.springframework.data.domain.Page;

public class SessionServiceImpl implements SessionService {

    @Override
    public void createSession(SessionReqDto reqDto) {

    }

    @Override
    public Page<SessionResDto> getSessions() {
        return null;
    }

    @Override
    public SessionDetailResDto getSessionInfo(String sessionId) {
        return null;
    }

    @Override
    public void updateSession(String sessionId, SessionReqDto reqDto) {

    }

    @Override
    public void deleteSession(String sessionId) {

    }
}
