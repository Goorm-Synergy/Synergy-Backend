package com.synergy.backend.domain.conference.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimePeriod is a Querydsl query type for TimePeriod
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QTimePeriod extends BeanPath<TimePeriod> {

    private static final long serialVersionUID = -1226414589L;

    public static final QTimePeriod timePeriod = new QTimePeriod("timePeriod");

    public final DateTimePath<java.time.LocalDateTime> endDateTime = createDateTime("endDateTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> startDateTime = createDateTime("startDateTime", java.time.LocalDateTime.class);

    public QTimePeriod(String variable) {
        super(TimePeriod.class, forVariable(variable));
    }

    public QTimePeriod(Path<? extends TimePeriod> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimePeriod(PathMetadata metadata) {
        super(TimePeriod.class, metadata);
    }

}

