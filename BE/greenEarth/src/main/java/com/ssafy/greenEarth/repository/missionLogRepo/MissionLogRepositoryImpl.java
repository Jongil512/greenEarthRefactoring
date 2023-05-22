package com.ssafy.greenEarth.repository.missionLogRepo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.greenEarth.domain.MissionLog;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.ssafy.greenEarth.domain.QMission.mission;
import static com.ssafy.greenEarth.domain.QMissionLog.missionLog;

@RequiredArgsConstructor
public class MissionLogRepositoryImpl implements MissionLogRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<MissionLog> findMissionLogById(int id) {
        return Optional.ofNullable(queryFactory.selectFrom(missionLog)
                .join(missionLog.mission, mission).fetchJoin()
                .where(missionLog.id.eq(id))
                .fetchOne());
    }

    //    @Query("select ml from MissionLog ml
    //    join fetch ml.mission m
    //    where ml.id = :id")

}
