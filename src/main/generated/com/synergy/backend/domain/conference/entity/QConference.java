package com.synergy.backend.domain.conference.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConference is a Querydsl query type for Conference
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConference extends EntityPathBase<Conference> {

    private static final long serialVersionUID = -1192295375L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConference conference = new QConference("conference");

    public final SetPath<com.synergy.backend.domain.member.entity.Admin, com.synergy.backend.domain.member.entity.QAdmin> admins = this.<com.synergy.backend.domain.member.entity.Admin, com.synergy.backend.domain.member.entity.QAdmin>createSet("admins", com.synergy.backend.domain.member.entity.Admin.class, com.synergy.backend.domain.member.entity.QAdmin.class, PathInits.DIRECT2);

    public final ListPath<com.synergy.backend.domain.booth.entity.Booth, com.synergy.backend.domain.booth.entity.QBooth> booths = this.<com.synergy.backend.domain.booth.entity.Booth, com.synergy.backend.domain.booth.entity.QBooth>createList("booths", com.synergy.backend.domain.booth.entity.Booth.class, com.synergy.backend.domain.booth.entity.QBooth.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final StringPath name = createString("name");

    public final StringPath organizer = createString("organizer");

    public final QTimePeriod period;

    public final ListPath<com.synergy.backend.domain.session.entity.Session, com.synergy.backend.domain.session.entity.QSession> sessions = this.<com.synergy.backend.domain.session.entity.Session, com.synergy.backend.domain.session.entity.QSession>createList("sessions", com.synergy.backend.domain.session.entity.Session.class, com.synergy.backend.domain.session.entity.QSession.class, PathInits.DIRECT2);

    public final StringPath type = createString("type");

    public QConference(String variable) {
        this(Conference.class, forVariable(variable), INITS);
    }

    public QConference(Path<? extends Conference> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConference(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConference(PathMetadata metadata, PathInits inits) {
        this(Conference.class, metadata, inits);
    }

    public QConference(Class<? extends Conference> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.period = inits.isInitialized("period") ? new QTimePeriod(forProperty("period")) : null;
    }

}

