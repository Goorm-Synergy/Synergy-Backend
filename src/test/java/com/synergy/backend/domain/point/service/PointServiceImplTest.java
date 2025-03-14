package com.synergy.backend.domain.point.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.synergy.backend.domain.booth.repository.BoothRepository;
import com.synergy.backend.domain.member.entity.Attendee;
import com.synergy.backend.domain.member.repository.AttendeeRepository;
import com.synergy.backend.domain.member.repository.RecruiterRepository;
import com.synergy.backend.domain.point.entity.Point;
import com.synergy.backend.domain.point.entity.PointType;
import com.synergy.backend.domain.point.repository.PointRepository;
import com.synergy.backend.domain.session.repository.SessionRepository;

@ExtendWith(MockitoExtension.class)
class PointServiceImplTest {

	@InjectMocks
	private PointServiceImpl pointService;

	@Mock
	private PointRepository pointRepository;

	@Mock
	private AttendeeRepository attendeeRepository;

	@Mock
	private BoothRepository boothRepository;

	@Mock
	private SessionRepository sessionRepository;

	@Mock
	private RecruiterRepository recruiterRepository;

	private Attendee attendee;
	private Point point;

	@BeforeEach
	void setUp() {
		attendee = Attendee.of("email", "pass", "name", "0101");
		point = Point.of(PointType.SIGN_UP);
	}

	@DisplayName("사용자의 포인트 내역을 조회할 수 있다.")
	@Test
	void testGetPointHistory() {
		// given
		List<Point> points = List.of(point);
		when(pointRepository.findByAttendeeIdOrderByCreatedTimeDesc(1L)).thenReturn(points);

		// when
		List<Point> result = pointService.getPointHistory(1L);

		// then
		assertEquals(1, result.size());
		assertEquals(point, result.get(0));
	}

}
