package com.ssafy.greenEarth.dto.Member;

import com.ssafy.greenEarth.domain.Child;
import com.ssafy.greenEarth.domain.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ChildProfileDto {

    @Schema(description = "아이 PK")
    private int childId;

    @Schema(description = "아이 실명")
    private String realName;

    @Schema(description = "아이 닉네임(로그인 ID)")
    private String nickname;

    @Schema(description = "아이 성별")
    private Gender gender;

    @Schema(description = "아이 마일리지")
    private int mileage;

    @Schema(description = "아이가 클리어한 미션 개수")
    private int clearedMission;

    @Schema(description = "아이의 지구 레벨")
    private int earthLevel;

    @Schema(description = "아이의 생일")
    private LocalDate birthday;

    @Schema(description = "아이의 아바타 번호")
    private int avatar;

    @Schema(description = "아이와 연결된 보호자 닉네임")
    private String parent;

    private Boolean isParent;

    public ChildProfileDto(Child child) {
        this.childId = child.getId();
        this.realName = child.getRealName();
        this.nickname = child.getNickname();
        this.gender = child.getGender();
        this.mileage = child.getMileage();
        this.clearedMission = child.getClearedMission();
        this.earthLevel = child.getEarthLevel();
        this.birthday = child.getBirthday();
        this.avatar = child.getAvatar();
        this.parent = child.getParent().getNickname();
        this.isParent = false;
    }
}
