package com.ssafy.greenEarth.domain;

import com.ssafy.greenEarth.dto.Reward.RewardPutDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "rewards")
public class Reward extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reward_id")
    private int id;

    @Column(length = 128)
    private String name;

    private int rewardCondition;

    @Column(length = 25)
    private String parentNickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id")
    private Child child;

    private Boolean isPaid;

    public void setPaid() {
        this.isPaid = true;
    }

    public void updateReward(RewardPutDto rewardPutDto, Child child) {
        this.name = rewardPutDto.getRewardName();
        this.rewardCondition = rewardPutDto.getRewardCondition();
        this.child = child;
    }
}
