package com.synergy.backend.domain.session.service;

import com.synergy.backend.domain.session.dto.SessionDetailResDto;
import com.synergy.backend.domain.session.dto.SessionReqDto;
import com.synergy.backend.domain.session.dto.SessionResDto;
import com.synergy.backend.domain.session.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SessionService {

    void createSession(Long conferenceId, SessionReqDto reqDto);

    List<SessionResDto> getSessions(Long conferenceId);

    SessionDetailResDto getSessionInfo(Long sessionId);

    void updateSession(Long sessionId, SessionReqDto reqDto);

    void deleteSession(Long sessionId);
}
