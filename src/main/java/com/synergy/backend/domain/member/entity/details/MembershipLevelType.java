package com.synergy.backend.domain.member.entity.details;

import com.synergy.backend.domain.member.vo.NextPointInfo;

public enum MembershipLevelType {
	PLATINUM,
	GOLD,
	SILVER,
	BRONZE,
	DEFAULT;

	public static MembershipLevelType getMembershipLevel(int point) {
		if (point >= 800)
			return GOLD;
		if (point >= 300)
			return SILVER;
		if (point >= 100)
			return BRONZE;
		return DEFAULT;
	}

	public static NextPointInfo getNextLevelInfo(int point) {
		if (point >= 800)
			return new NextPointInfo(PLATINUM, 0);
		if (point >= 300)
			return new NextPointInfo(GOLD, 800 - point);
		if (point >= 100)
			return new NextPointInfo(SILVER, 300 - point);
		if (point >= 0)
			return new NextPointInfo(BRONZE, 100 - point);
		return new NextPointInfo(DEFAULT, 0);
	}
}
