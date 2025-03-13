package com.synergy.backend.domain.interest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberInterest is a Querydsl query type for MemberInterest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberInterest extends EntityPathBase<MemberInterest> {

    private static final long serialVersionUID = -1753042169L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberInterest memberInterest = new QMemberInterest("memberInterest");

    public final com.synergy.backend.domain.member.entity.QAttendee attendee;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QInterest interest;

    public QMemberInterest(String variable) {
        this(MemberInterest.class, forVariable(variable), INITS);
    }

    public QMemberInterest(Path<? extends MemberInterest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberInterest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberInterest(PathMetadata metadata, PathInits inits) {
        this(MemberInterest.class, metadata, inits);
    }

    public QMemberInterest(Class<? extends MemberInterest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attendee = inits.isInitialized("attendee") ? new com.synergy.backend.domain.member.entity.QAttendee(forProperty("attendee"), inits.get("attendee")) : null;
        this.interest = inits.isInitialized("interest") ? new QInterest(forProperty("interest")) : null;
    }

}

