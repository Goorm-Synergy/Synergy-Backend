package com.synergy.backend.domain.booth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBooth is a Querydsl query type for Booth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBooth extends EntityPathBase<Booth> {

    private static final long serialVersionUID = -2043503521L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBooth booth = new QBooth("booth");

    public final SetPath<com.synergy.backend.domain.member.entity.Admin, com.synergy.backend.domain.member.entity.QAdmin> admins = this.<com.synergy.backend.domain.member.entity.Admin, com.synergy.backend.domain.member.entity.QAdmin>createSet("admins", com.synergy.backend.domain.member.entity.Admin.class, com.synergy.backend.domain.member.entity.QAdmin.class, PathInits.DIRECT2);

    public final StringPath company = createString("company");

    public final com.synergy.backend.domain.conference.entity.QConference conference;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final StringPath name = createString("name");

    public QBooth(String variable) {
        this(Booth.class, forVariable(variable), INITS);
    }

    public QBooth(Path<? extends Booth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBooth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBooth(PathMetadata metadata, PathInits inits) {
        this(Booth.class, metadata, inits);
    }

    public QBooth(Class<? extends Booth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.conference = inits.isInitialized("conference") ? new com.synergy.backend.domain.conference.entity.QConference(forProperty("conference"), inits.get("conference")) : null;
    }

}

