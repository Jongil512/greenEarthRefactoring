package com.ssafy.greenEarth.repository.greenEarthRepo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.ssafy.greenEarth.domain.QGreenEarth.greenEarth;

@RequiredArgsConstructor
public class GreenEarthRepositoryImpl implements GreenEarthRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public int findFirstByMileageCondition(int mileage) {
        return queryFactory.selectFrom(greenEarth)
                .where(greenEarth.mileageCondition.loe(mileage))
                .orderBy(greenEarth.id.desc())
                .limit(1)
                .fetchOne().getId();
    }
//    @Query(value = "SELECT g.green_earth_id " +
//            "FROM green_earth g " +
//            "WHERE g.mileage_condition <= :mileage " +
//            "ORDER BY g.green_earth_id " +
//            "DESC LIMIT 1",
//            nativeQuery = true)
}
