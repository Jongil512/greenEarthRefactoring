package com.ssafy.greenEarth.dto.Auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
public class LoginDto {

    @NotBlank(message = "닉네임을 입력해주세요")
    @Schema(description = "아이 로그인 닉네임")
    private String nickname;

    @NotEmpty(message = "비밀번호를 입력해주세요")
    @Schema(description = "아이 로그인 비밀번호")
    private String password;
}
