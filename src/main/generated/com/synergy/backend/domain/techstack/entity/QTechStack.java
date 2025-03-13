package com.synergy.backend.domain.techstack.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTechStack is a Querydsl query type for TechStack
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTechStack extends EntityPathBase<TechStack> {

    private static final long serialVersionUID = -1603872449L;

    public static final QTechStack techStack = new QTechStack("techStack");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QTechStack(String variable) {
        super(TechStack.class, forVariable(variable));
    }

    public QTechStack(Path<? extends TechStack> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTechStack(PathMetadata metadata) {
        super(TechStack.class, metadata);
    }

}

