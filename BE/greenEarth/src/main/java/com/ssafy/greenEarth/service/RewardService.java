package com.ssafy.greenEarth.service;

import com.ssafy.greenEarth.domain.Child;
import com.ssafy.greenEarth.domain.Reward;
import com.ssafy.greenEarth.dto.Reward.*;
import com.ssafy.greenEarth.exception.BusinessException;
import com.ssafy.greenEarth.repository.childRepo.ChildRepository;
import com.ssafy.greenEarth.repository.rewardRepo.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.ssafy.greenEarth.exception.ErrorCode.NOT_EXIST_ACCOUNT;
import static com.ssafy.greenEarth.exception.ErrorCode.NOT_EXIST_REWARD;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RewardService {

    private final ChildRepository childRepository;

    private final RewardRepository rewardRepository;

    // 보상 목록 조회
    public List<RewardResDto> getRewards(int childId){
        Child child = childRepository.findChildById(childId).orElseThrow(
                () -> new BusinessException(NOT_EXIST_ACCOUNT)
        );
        return child.getRewardList().stream()
                .map(RewardResDto::new).collect(Collectors.toList());
    }

    // 보상 작성
    @Transactional
    public RewardResDto createReward(int childId, RewardReqDto rewardReqDto){
        Child child = childRepository.findChildById(childId).orElseThrow(
                () -> new BusinessException(NOT_EXIST_ACCOUNT)
        );
        return new RewardResDto(rewardRepository.save(rewardReqDto.toEntity(child)));
    }

    // 보상 수정
    @Transactional
    public RewardResDto updateReward(int rewardId, RewardPutDto rewardPutDto){
        Reward reward = rewardRepository.findRewardById(rewardId).orElseThrow(
                () -> new BusinessException(NOT_EXIST_REWARD)
        );
        Child child = childRepository.findChildById(rewardPutDto.getChildId()).orElseThrow(
                () -> new BusinessException(NOT_EXIST_ACCOUNT)
        );
        reward.updateReward(rewardPutDto, child);
        return new RewardResDto(rewardRepository.save(reward));
    }

    // 보상 삭제
    @Transactional
    public void deleteReward(int rewardId){
        Reward reward = rewardRepository.findRewardById(rewardId).orElseThrow(
                () -> new BusinessException(NOT_EXIST_REWARD)
        );
        rewardRepository.delete(reward);
    }

    // 보상 지급 완료
    @Transactional
    public RewardResDto paidReward(int rewardId){
        Reward reward = rewardRepository.findRewardById(rewardId).orElseThrow(
                () -> new BusinessException(NOT_EXIST_REWARD)
        );
        reward.setPaid();
        return new RewardResDto(rewardRepository.save(reward));
    }

}
