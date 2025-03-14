package com.synergy.backend.domain.point.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synergy.backend.domain.booth.entity.Booth;
import com.synergy.backend.domain.booth.repository.BoothRepository;
import com.synergy.backend.domain.member.entity.Attendee;
import com.synergy.backend.domain.member.entity.Recruiter;
import com.synergy.backend.domain.member.repository.AttendeeRepository;
import com.synergy.backend.domain.member.repository.RecruiterRepository;
import com.synergy.backend.domain.point.api.dto.PointResponseDto;
import com.synergy.backend.domain.point.entity.Point;
import com.synergy.backend.domain.point.entity.PointType;
import com.synergy.backend.domain.point.exception.PointNotFoundException;
import com.synergy.backend.domain.point.repository.PointRepository;
import com.synergy.backend.domain.session.entity.Session;
import com.synergy.backend.domain.session.repository.SessionRepository;
import com.synergy.backend.global.security.exception.UnKnownUserTypeException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

	private final PointRepository pointRepository;
	private final AttendeeRepository attendeeRepository;
	private final BoothRepository boothRepository;
	private final SessionRepository sessionRepository;
	private final RecruiterRepository recruiterRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Point> getPointHistory(Long attendeeId) {
		return pointRepository.findByAttendeeIdOrderByCreatedTimeDesc(attendeeId);
	}

	@Override
	public PointResponseDto getPointResponse(Long pointId) {
		Point point = pointRepository.findById(pointId)
			.orElseThrow(PointNotFoundException::new);

		String details = "";
		switch (point.getPointType()) {
			case BOOTH_VISIT:
				if (point.getBoothId() != null) {
					Booth booth = boothRepository.findById(point.getBoothId())
						.orElseThrow(() -> new RuntimeException("부스 정보를 찾을 수 없습니다."));
					details = booth.getName();
				}
				break;
			case SESSION_ATTEND:
			case SESSION_QNA:
				if (point.getSessionId() != null) {
					Session session = sessionRepository.findById(point.getSessionId())
						.orElseThrow(() -> new RuntimeException("세션 정보를 찾을 수 없습니다."));
					details = session.getTitle();
				}
				break;
			case RECRUITER_MEETING:
				if (point.getRecruiterId() != null) {
					Recruiter recruiter = recruiterRepository.findById(point.getRecruiterId())
						.orElseThrow(() -> new RuntimeException("채용 담당자 정보를 찾을 수 없습니다."));
					details = recruiter.getCompany();
				}
				break;
			case SIGN_UP:
				details = "회원가입 적립";
				break;
			case SURVEY_PARTICIPATION:
				details = "설문조사 참여 적립";
				break;
			case CONTENT_SHARE:
				details = "컨텐츠 공유 적립";
				break;
			default:
				break;
		}
		return PointResponseDto.from(point, details);
	}

	@Override
	public void addPoint(Long attendeeId, PointType pointType, Long detailId) {
		Attendee attendee = attendeeRepository.findById(attendeeId).orElseThrow(UnKnownUserTypeException::new);

		Point point = Point.builder()
			.pointType(pointType)
			.build();

		if (detailId != null) {
			switch (pointType) {
				case BOOTH_VISIT -> {
					point.updateBoothId(detailId);
				}
				case SESSION_ATTEND, SESSION_QNA -> {
					point.updateSessionId(detailId);
				}
				case RECRUITER_MEETING -> {
					point.updateRecruiterId(detailId);
				}
			}
		}

		attendee.addPoint(point);

		pointRepository.save(point);
		int pointValue = pointType.getPointValue();

		// Attendee의 총 포인트 업데이트
		attendee.addPoints(pointValue);

		// Attendee 업데이트 (트랜잭션 내에서 변경 감지가 이루어지므로 save() 생략 가능)
		attendeeRepository.save(attendee);

	}

}
