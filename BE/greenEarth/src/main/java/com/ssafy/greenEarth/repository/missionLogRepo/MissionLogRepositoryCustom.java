package com.ssafy.greenEarth.repository.missionLogRepo;

import com.ssafy.greenEarth.domain.MissionLog;

import java.util.Optional;

public interface MissionLogRepositoryCustom {

    Optional<MissionLog> findMissionLogById(int id);
}
