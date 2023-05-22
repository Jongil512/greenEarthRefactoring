package com.ssafy.greenEarth.repository.childRepo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.greenEarth.domain.Child;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.ssafy.greenEarth.domain.QChild.child;
import static com.ssafy.greenEarth.domain.QMission.mission;
import static com.ssafy.greenEarth.domain.QMissionLog.missionLog;
import static com.ssafy.greenEarth.domain.QParent.parent;

@RequiredArgsConstructor
public class ChildRepositoryImpl implements ChildRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public Optional<Child> findChildById(int childId) {
        return Optional.ofNullable(queryFactory.selectFrom(child)
                .join(child.parent, parent).fetchJoin()
                .leftJoin(child.missionLogList, missionLog).fetchJoin()
                .leftJoin(missionLog.mission, mission).fetchJoin()
                .where(child.id.eq(childId))
                .fetchOne());
    }
//    @Query("select c from Child c " +
//            "join fetch c.parent " +
//            "left join fetch c.missionLogList ml " +
//            "left join fetch ml.mission " +
//            "where c.id = :id")
}
