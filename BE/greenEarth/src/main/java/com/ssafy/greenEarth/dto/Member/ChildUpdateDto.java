package com.ssafy.greenEarth.dto.Member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ChildUpdateDto {

    @NotBlank(message = "닉네임을 입력해주세요")
    @Schema(description = "닉네임 변경 요청")
    private String nickname;
}
