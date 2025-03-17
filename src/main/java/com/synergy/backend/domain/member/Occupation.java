package com.synergy.backend.domain.member;

import com.synergy.backend.domain.member.entity.details.OccupationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "occupation_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Occupation {

	@Id
	private int id;

	@Column(nullable = false, unique = true)
	private String name;

	public OccupationType toEnum() {
		return OccupationType.fromId(this.id);
	}
}
