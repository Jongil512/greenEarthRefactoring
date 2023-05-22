package com.ssafy.greenEarth.repository.rewardRepo;

import com.ssafy.greenEarth.domain.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RewardRepository extends JpaRepository<Reward, Integer>, RewardRepositoryCustom{

    Optional<Reward> findRewardById(int id);

}
