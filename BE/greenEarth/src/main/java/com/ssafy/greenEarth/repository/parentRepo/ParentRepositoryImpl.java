package com.ssafy.greenEarth.repository.parentRepo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.greenEarth.domain.Parent;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.ssafy.greenEarth.domain.QParent.parent;

@RequiredArgsConstructor
public class ParentRepositoryImpl implements ParentRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Parent> findById(int parentId) {
        return Optional.ofNullable(queryFactory.selectFrom(parent)
                .leftJoin(parent.childList).fetchJoin()
                .where(parent.id.eq(parentId))
                .fetchOne());
    }

}
