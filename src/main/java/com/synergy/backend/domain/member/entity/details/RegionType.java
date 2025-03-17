package com.synergy.backend.domain.member.entity.details;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RegionType {
	SEOUL(11, "서울"),
	GYEONGGI(41, "경기"),
	INCHEON(28, "인천"),
	GANGWON(51, "강원"),
	DAEJEON(30, "대전"),
	SAEJONG(36, "세종"),
	CHUNGNAM(44, "충남"),
	CHUNGBUK(43, "충북"),
	BUSAN(26, "부산"),
	ULSAN(31, "울산"),
	GYEONG_NAM(48, "경남"),
	GYEONG_BUK(47, "경북"),
	DAEGU(27, "대구"),
	GWANGJU(29, "광주"),
	JEONAM(46, "전남"),
	JEONBUK(52, "전북"),
	JEJU(50, "제주"),
	ETC(99, "기타"); // 기타 지역

	private final int code;
	private final String description;

	// 숫자로 Enum 찾기
	public static RegionType fromCode(int code) {
		return Arrays.stream(RegionType.values())
			.filter(region -> region.code == code)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Unknown region code: " + code));
	}
}
