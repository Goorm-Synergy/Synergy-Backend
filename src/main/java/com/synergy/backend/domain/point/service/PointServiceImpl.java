package com.synergy.backend.domain.point.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergy.backend.domain.booth.entity.Booth;
import com.synergy.backend.domain.booth.repository.BoothRepository;
import com.synergy.backend.domain.member.entity.Recruiter;
import com.synergy.backend.domain.member.repository.RecruiterRepository;
import com.synergy.backend.domain.point.api.dto.PointResponseDto;
import com.synergy.backend.domain.point.entity.Point;
import com.synergy.backend.domain.point.entity.PointType;
import com.synergy.backend.domain.point.exception.PointNotFoundException;
import com.synergy.backend.domain.point.repository.PointRepository;
import com.synergy.backend.domain.session.entity.Session;
import com.synergy.backend.domain.session.repository.SessionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

	private final PointRepository pointRepository;
	private final BoothRepository boothRepository;
	private final SessionRepository sessionRepository;
	private final RecruiterRepository recruiterRepository;

	@Override
	public List<Point> getPointHistory(Long attendeeId) {
		return pointRepository.findByAttendeeIdOrderByCreatedTimeDesc(attendeeId);
	}

	@Override
	public PointResponseDto getPointResponse(Long pointId) {
		Point point = pointRepository.findById(pointId)
			.orElseThrow(PointNotFoundException::new);

		String details = "";

		if (point.getPointType() == PointType.BOOTH_VISIT && point.getBoothId() != null) {
			Booth booth = boothRepository.findById(point.getBoothId())
				.orElseThrow(() -> new RuntimeException("부스 정보를 찾을 수 없습니다."));
			details = booth.getName();  // 부스 이름
		} else if ((point.getPointType() == PointType.SESSION_ATTEND
			|| point.getPointType() == PointType.SESSION_QNA) && point.getSessionId() != null) {
			Session session = sessionRepository.findById(point.getSessionId())
				.orElseThrow(() -> new RuntimeException("세션 정보를 찾을 수 없습니다."));
			details = session.getTitle();  // 세션 제목
		} else if (point.getPointType() == PointType.RECRUITER_MEETING && point.getRecruiterId() != null) {
			Recruiter recruiter = recruiterRepository.findById(point.getRecruiterId())
				.orElseThrow(() -> new RuntimeException("채용 담당자 정보를 찾을 수 없습니다."));
			details = recruiter.getCompany();  // 담당자 회사
		}

		return PointResponseDto.from(point, details);
	}

}
