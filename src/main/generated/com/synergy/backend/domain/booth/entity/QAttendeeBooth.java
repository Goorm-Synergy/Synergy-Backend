package com.synergy.backend.domain.booth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttendeeBooth is a Querydsl query type for AttendeeBooth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttendeeBooth extends EntityPathBase<AttendeeBooth> {

    private static final long serialVersionUID = 1508903621L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttendeeBooth attendeeBooth = new QAttendeeBooth("attendeeBooth");

    public final com.synergy.backend.domain.member.entity.QAttendee attendee;

    public final QBooth booth;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QAttendeeBooth(String variable) {
        this(AttendeeBooth.class, forVariable(variable), INITS);
    }

    public QAttendeeBooth(Path<? extends AttendeeBooth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttendeeBooth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttendeeBooth(PathMetadata metadata, PathInits inits) {
        this(AttendeeBooth.class, metadata, inits);
    }

    public QAttendeeBooth(Class<? extends AttendeeBooth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attendee = inits.isInitialized("attendee") ? new com.synergy.backend.domain.member.entity.QAttendee(forProperty("attendee"), inits.get("attendee")) : null;
        this.booth = inits.isInitialized("booth") ? new QBooth(forProperty("booth"), inits.get("booth")) : null;
    }

}

