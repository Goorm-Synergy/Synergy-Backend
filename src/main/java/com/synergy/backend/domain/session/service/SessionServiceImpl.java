package com.synergy.backend.domain.session.service;

import com.synergy.backend.domain.conference.entity.Conference;
import com.synergy.backend.domain.conference.exception.NotFoundConference;
import com.synergy.backend.domain.conference.repository.ConferenceRepository;
import com.synergy.backend.domain.member.entity.Admin;
import com.synergy.backend.domain.member.entity.Attendee;
import com.synergy.backend.domain.member.entity.Member;
import com.synergy.backend.domain.member.repository.AttendeeRepository;
import com.synergy.backend.domain.session.dto.SessionDetailResDto;
import com.synergy.backend.domain.session.dto.SessionReqDto;
import com.synergy.backend.domain.session.dto.SessionResDto;
import com.synergy.backend.domain.session.dto.question.QuestionReqDto;
import com.synergy.backend.domain.session.dto.question.QuestionResDto;
import com.synergy.backend.domain.session.entity.AttendeeSession;
import com.synergy.backend.domain.session.entity.Session;
import com.synergy.backend.domain.session.exception.NotFoundSession;
import com.synergy.backend.domain.session.repository.AttendeeSessionRepository;
import com.synergy.backend.domain.session.repository.SessionRepository;
import com.synergy.backend.domain.session.service.validate.DateTimeValidator;
import com.synergy.backend.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Security;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final ConferenceRepository conferenceRepository;
    private final SessionRepository sessionRepository;
    private final AttendeeSessionRepository attendeeSessionRepository;

    public Member getCurrentMember() {
        return SecurityUtil.getCurrentMember();
    }

    @Override
    public void createSession(Long conferenceId, SessionReqDto reqDto) {
        Admin member = (Admin) getCurrentMember();
        Conference conference = ifConferenceExists(conferenceId);

        LocalDateTime startTime = DateTimeValidator.isValidLocalDateTime(reqDto.startTime());
        LocalDateTime endTime = DateTimeValidator.isValidLocalDateTime(reqDto.endTime());

        Session session = Session.of(reqDto, startTime, endTime, conference);
        sessionRepository.save(session);
    }

    @Transactional(readOnly = true)
    @Override
    public List<SessionResDto> getSessions(Long conferenceId) {
        Conference conference = ifConferenceExists(conferenceId);
        List<Session> sessions = sessionRepository.findAllByConferenceOrderByStartTime(conference);

        return sessions.stream().map(SessionResDto::from).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public SessionDetailResDto getSessionInfo(Long conferenceId, Long sessionId) {
        ifConferenceExists(conferenceId);
        Session session = ifSessionExists(sessionId);
        return SessionDetailResDto.from(session);
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

    // -------------------------------------- QnA ------------------------------------------------

    @Override
    public QuestionResDto createQuestion(Long conferenceId, Long sessionId, QuestionReqDto reqDto) {
        Attendee attendee = (Attendee) getCurrentMember();
        // 참가자에 대한 QR 인증 여부를 확인해야 할듯
        ifConferenceExists(conferenceId);
        Session session = ifSessionExists(sessionId);
        AttendeeSession attendeeSession = AttendeeSession.of(attendee, session);
        attendeeSessionRepository.save(attendeeSession);

        return new QuestionResDto(reqDto.content());
    }

    private Conference ifConferenceExists(Long conferenceId) {
        return conferenceRepository.findById(conferenceId).orElseThrow(NotFoundConference::new);
    }

    private Session ifSessionExists(Long sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(NotFoundSession::new);
    }

}
