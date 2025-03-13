package com.synergy.backend.domain.session.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSession is a Querydsl query type for Session
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSession extends EntityPathBase<Session> {

    private static final long serialVersionUID = 879285023L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSession session = new QSession("session");

    public final SetPath<com.synergy.backend.domain.member.entity.Admin, com.synergy.backend.domain.member.entity.QAdmin> admins = this.<com.synergy.backend.domain.member.entity.Admin, com.synergy.backend.domain.member.entity.QAdmin>createSet("admins", com.synergy.backend.domain.member.entity.Admin.class, com.synergy.backend.domain.member.entity.QAdmin.class, PathInits.DIRECT2);

    public final com.synergy.backend.domain.conference.entity.QConference conference;

    public final StringPath description = createString("description");

    public final DateTimePath<java.time.LocalDateTime> endTime = createDateTime("endTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> progressDate = createDate("progressDate", java.time.LocalDate.class);

    public final StringPath speaker = createString("speaker");

    public final StringPath speakerPosition = createString("speakerPosition");

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public QSession(String variable) {
        this(Session.class, forVariable(variable), INITS);
    }

    public QSession(Path<? extends Session> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSession(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSession(PathMetadata metadata, PathInits inits) {
        this(Session.class, metadata, inits);
    }

    public QSession(Class<? extends Session> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.conference = inits.isInitialized("conference") ? new com.synergy.backend.domain.conference.entity.QConference(forProperty("conference"), inits.get("conference")) : null;
    }

}

