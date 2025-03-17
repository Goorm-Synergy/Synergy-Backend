package com.synergy.backend.domain.member.entity.details;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 직업
@Getter
@AllArgsConstructor
public enum JobType {
	FRONTEND_DEVELOPER(101, "Frontend Developer", OccupationType.DEVELOPMENT),
	BACKEND_DEVELOPER(102, "Backend Developer", OccupationType.DEVELOPMENT),
	FULLSTACK_DEVELOPER(103, "Fullstack Developer", OccupationType.DEVELOPMENT),
	AI_ENGINEER(104, "AI Engineer", OccupationType.DEVELOPMENT),
	CLOUD_ENGINEER(105, "Cloud Engineer", OccupationType.DEVELOPMENT),
	DEVOPS_ENGINEER(106, "DevOps Engineer", OccupationType.DEVELOPMENT),
	DATA_ENGINEER(107, "Data Engineer", OccupationType.DEVELOPMENT),
	MOBILE_APP_DEVELOPER(108, "Mobile App Developer", OccupationType.DEVELOPMENT),
	EMBEDDED_SYSTEM_DEVELOPER(109, "Embedded System Developer", OccupationType.DEVELOPMENT),
	BLOCK_CHAIN_DEVELOPER(110, "BlockChain Developer", OccupationType.DEVELOPMENT),

	UI_UX_DESIGNER(201, "UI/UX Designer", OccupationType.DESIGN),
	GRAPHIC_DESIGNER(202, "Graphic Designer", OccupationType.DESIGN),
	WEB_DESIGNER(203, "Web Designer", OccupationType.DESIGN),

	PROJECT_MANAGER(301, "Project Manager", OccupationType.PLANNING_OPERATION),
	DATA_ANALYST(302, "Data Analyst", OccupationType.PLANNING_OPERATION),
	MARKETER(303, "Marketer", OccupationType.PLANNING_OPERATION),

	STUDENT(401, "Student", OccupationType.OTHER),
	JOB_SEEKER(402, "Job Seeker", OccupationType.OTHER),
	RESEARCHER(403, "Researcher", OccupationType.OTHER),
	OTHER(404, "Other", OccupationType.OTHER),
	;

	private final int id; // DB에서 사용하는 ID 값
	private final String description;
	private final OccupationType occupationType; // 직무 매핑

	public static JobType fromId(int id) {
		return Arrays.stream(JobType.values())
			.filter(job -> job.id == id)
			.findFirst()
			.orElse(null);
	}
}
