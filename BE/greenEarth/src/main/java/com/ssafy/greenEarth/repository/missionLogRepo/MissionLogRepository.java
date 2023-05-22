package com.ssafy.greenEarth.repository.missionLogRepo;

import com.ssafy.greenEarth.domain.MissionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionLogRepository extends JpaRepository<MissionLog, Integer>, MissionLogRepositoryCustom {
    MissionLog findById(int id);

//    @Query("select ml from MissionLog ml join fetch ml.mission m where ml.id = :id")
//    Optional<MissionLog> findMissionLogById(@Param("id") int id);

}
