package com.synergy.backend.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttendee is a Querydsl query type for Attendee
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttendee extends EntityPathBase<Attendee> {

    private static final long serialVersionUID = 1328866445L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttendee attendee = new QAttendee("attendee");

    public final com.synergy.backend.global.common.QBaseEntity _super = new com.synergy.backend.global.common.QBaseEntity(this);

    public final com.synergy.backend.domain.conference.entity.QConference conference;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final EnumPath<PositionType> desiredPosition = createEnum("desiredPosition", PositionType.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath information = createString("information");

    public final BooleanPath isHiringInterested = createBoolean("isHiringInterested");

    public final SetPath<com.synergy.backend.domain.interest.entity.MemberInterest, com.synergy.backend.domain.interest.entity.QMemberInterest> memberInterests = this.<com.synergy.backend.domain.interest.entity.MemberInterest, com.synergy.backend.domain.interest.entity.QMemberInterest>createSet("memberInterests", com.synergy.backend.domain.interest.entity.MemberInterest.class, com.synergy.backend.domain.interest.entity.QMemberInterest.class, PathInits.DIRECT2);

    public final EnumPath<MembershipLevelType> membershipLevelType = createEnum("membershipLevelType", MembershipLevelType.class);

    public final SetPath<com.synergy.backend.domain.techstack.entity.MemberTechStack, com.synergy.backend.domain.techstack.entity.QMemberTechStack> memberTechStacks = this.<com.synergy.backend.domain.techstack.entity.MemberTechStack, com.synergy.backend.domain.techstack.entity.QMemberTechStack>createSet("memberTechStacks", com.synergy.backend.domain.techstack.entity.MemberTechStack.class, com.synergy.backend.domain.techstack.entity.QMemberTechStack.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedTime = _super.modifiedTime;

    public final StringPath name = createString("name");

    public final EnumPath<OccupationType> occupationType = createEnum("occupationType", OccupationType.class);

    public final StringPath password = createString("password");

    public final StringPath personalHistory = createString("personalHistory");

    public final StringPath phone = createString("phone");

    public final ListPath<com.synergy.backend.domain.point.entity.Point, com.synergy.backend.domain.point.entity.QPoint> points = this.<com.synergy.backend.domain.point.entity.Point, com.synergy.backend.domain.point.entity.QPoint>createList("points", com.synergy.backend.domain.point.entity.Point.class, com.synergy.backend.domain.point.entity.QPoint.class, PathInits.DIRECT2);

    public final EnumPath<PositionType> position = createEnum("position", PositionType.class);

    public final StringPath selfIntroduction = createString("selfIntroduction");

    public final NumberPath<Integer> totalPoints = createNumber("totalPoints", Integer.class);

    public final StringPath yearsOfExperience = createString("yearsOfExperience");

    public QAttendee(String variable) {
        this(Attendee.class, forVariable(variable), INITS);
    }

    public QAttendee(Path<? extends Attendee> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttendee(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttendee(PathMetadata metadata, PathInits inits) {
        this(Attendee.class, metadata, inits);
    }

    public QAttendee(Class<? extends Attendee> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.conference = inits.isInitialized("conference") ? new com.synergy.backend.domain.conference.entity.QConference(forProperty("conference"), inits.get("conference")) : null;
    }

}

