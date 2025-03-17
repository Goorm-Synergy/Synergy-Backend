package com.synergy.backend.domain.member;

import com.synergy.backend.domain.member.entity.details.JobType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Job {
	@Id
	private int id;

	@Column(nullable = false, unique = true)
	private String name;

	@ManyToOne
	@JoinColumn(name = "occupation_id", nullable = false)
	private Occupation occupation; // 직무 테이블 참조

	public JobType toEnum() {
		return JobType.fromId(this.id);
	}
}
