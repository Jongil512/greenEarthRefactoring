package com.ssafy.greenEarth.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mission_logs")
public class MissionLog extends CreatedTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int id;

    private LocalDateTime clearedAt;

    private boolean isPermitted;

    @Column(length = 25)
    private String parentNickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id")
    private Child child;

    public void setPermitted(boolean permitted) {
        isPermitted = permitted;
    }

    public void setClearedAt(LocalDateTime clearedAt) {
        this.clearedAt = clearedAt;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public MissionLog(Child child, Mission mission, Parent parent){
        this.child = child;
        this.mission = mission;
        this.isPermitted = false;
        this.parentNickname = parent.getNickname();
    }
}
