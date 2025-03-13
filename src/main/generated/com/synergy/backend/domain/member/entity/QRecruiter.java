package com.synergy.backend.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecruiter is a Querydsl query type for Recruiter
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruiter extends EntityPathBase<Recruiter> {

    private static final long serialVersionUID = -318005512L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecruiter recruiter = new QRecruiter("recruiter");

    public final com.synergy.backend.global.common.QBaseEntity _super = new com.synergy.backend.global.common.QBaseEntity(this);

    public final StringPath company = createString("company");

    public final com.synergy.backend.domain.conference.entity.QConference conference;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedTime = _super.modifiedTime;

    public final StringPath recruiterAuthCode = createString("recruiterAuthCode");

    public final StringPath responsibility = createString("responsibility");

    public QRecruiter(String variable) {
        this(Recruiter.class, forVariable(variable), INITS);
    }

    public QRecruiter(Path<? extends Recruiter> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecruiter(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecruiter(PathMetadata metadata, PathInits inits) {
        this(Recruiter.class, metadata, inits);
    }

    public QRecruiter(Class<? extends Recruiter> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.conference = inits.isInitialized("conference") ? new com.synergy.backend.domain.conference.entity.QConference(forProperty("conference"), inits.get("conference")) : null;
    }

}

