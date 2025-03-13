package com.synergy.backend.domain.point.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPoint is a Querydsl query type for Point
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPoint extends EntityPathBase<Point> {

    private static final long serialVersionUID = 2026368671L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPoint point = new QPoint("point");

    public final com.synergy.backend.global.common.QBaseEntity _super = new com.synergy.backend.global.common.QBaseEntity(this);

    public final com.synergy.backend.domain.member.entity.QAttendee attendee;

    public final NumberPath<Long> boothId = createNumber("boothId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedTime = _super.modifiedTime;

    public final EnumPath<PointType> pointType = createEnum("pointType", PointType.class);

    public final NumberPath<Long> recruiterId = createNumber("recruiterId", Long.class);

    public final NumberPath<Long> sessionId = createNumber("sessionId", Long.class);

    public final NumberPath<Long> sessionQnAId = createNumber("sessionQnAId", Long.class);

    public QPoint(String variable) {
        this(Point.class, forVariable(variable), INITS);
    }

    public QPoint(Path<? extends Point> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPoint(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPoint(PathMetadata metadata, PathInits inits) {
        this(Point.class, metadata, inits);
    }

    public QPoint(Class<? extends Point> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attendee = inits.isInitialized("attendee") ? new com.synergy.backend.domain.member.entity.QAttendee(forProperty("attendee"), inits.get("attendee")) : null;
    }

}

