package com.synergy.backend.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAdmin is a Querydsl query type for Admin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdmin extends EntityPathBase<Admin> {

    private static final long serialVersionUID = 1331115100L;

    public static final QAdmin admin = new QAdmin("admin");

    public final com.synergy.backend.global.common.QBaseEntity _super = new com.synergy.backend.global.common.QBaseEntity(this);

    public final StringPath adminAuthCode = createString("adminAuthCode");

    public final SetPath<com.synergy.backend.domain.booth.entity.Booth, com.synergy.backend.domain.booth.entity.QBooth> booths = this.<com.synergy.backend.domain.booth.entity.Booth, com.synergy.backend.domain.booth.entity.QBooth>createSet("booths", com.synergy.backend.domain.booth.entity.Booth.class, com.synergy.backend.domain.booth.entity.QBooth.class, PathInits.DIRECT2);

    public final SetPath<com.synergy.backend.domain.conference.entity.Conference, com.synergy.backend.domain.conference.entity.QConference> conferences = this.<com.synergy.backend.domain.conference.entity.Conference, com.synergy.backend.domain.conference.entity.QConference>createSet("conferences", com.synergy.backend.domain.conference.entity.Conference.class, com.synergy.backend.domain.conference.entity.QConference.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedTime = _super.modifiedTime;

    public final SetPath<com.synergy.backend.domain.session.entity.Session, com.synergy.backend.domain.session.entity.QSession> sessions = this.<com.synergy.backend.domain.session.entity.Session, com.synergy.backend.domain.session.entity.QSession>createSet("sessions", com.synergy.backend.domain.session.entity.Session.class, com.synergy.backend.domain.session.entity.QSession.class, PathInits.DIRECT2);

    public QAdmin(String variable) {
        super(Admin.class, forVariable(variable));
    }

    public QAdmin(Path<? extends Admin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdmin(PathMetadata metadata) {
        super(Admin.class, metadata);
    }

}

