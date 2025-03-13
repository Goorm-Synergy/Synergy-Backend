package com.synergy.backend.domain.techstack.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberTechStack is a Querydsl query type for MemberTechStack
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberTechStack extends EntityPathBase<MemberTechStack> {

    private static final long serialVersionUID = -40532475L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberTechStack memberTechStack = new QMemberTechStack("memberTechStack");

    public final com.synergy.backend.domain.member.entity.QAttendee attendee;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QTechStack techStack;

    public QMemberTechStack(String variable) {
        this(MemberTechStack.class, forVariable(variable), INITS);
    }

    public QMemberTechStack(Path<? extends MemberTechStack> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberTechStack(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberTechStack(PathMetadata metadata, PathInits inits) {
        this(MemberTechStack.class, metadata, inits);
    }

    public QMemberTechStack(Class<? extends MemberTechStack> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attendee = inits.isInitialized("attendee") ? new com.synergy.backend.domain.member.entity.QAttendee(forProperty("attendee"), inits.get("attendee")) : null;
        this.techStack = inits.isInitialized("techStack") ? new QTechStack(forProperty("techStack")) : null;
    }

}

