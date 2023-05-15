package com.ssafy.greenEarth.service;

import com.ssafy.greenEarth.domain.Child;
import com.ssafy.greenEarth.domain.RefreshToken;
import com.ssafy.greenEarth.domain.RefreshTokenId;
import com.ssafy.greenEarth.domain.Role;
import com.ssafy.greenEarth.dto.Auth.LoginDto;
import com.ssafy.greenEarth.dto.Auth.TokenDto;
import com.ssafy.greenEarth.exception.BusinessException;
import com.ssafy.greenEarth.jwt.TokenProvider;
import com.ssafy.greenEarth.repository.childRepo.ChildRepository;
import com.ssafy.greenEarth.repository.parentRepo.ParentRepository;
import com.ssafy.greenEarth.repository.refreshTokenRepo.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static com.ssafy.greenEarth.exception.ErrorCode.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor()
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final ChildRepository childRepository;

    private final ParentRepository parentRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    public Boolean isCurAccountExist(int id, Role role) {
        // id 와 role 에 해당하는 계정 존재하는지 확인
        if (role == Role.ROLE_CHILD && childRepository.existsById(id)) {
            return true;
        } else return (role == Role.ROLE_PARENT && parentRepository.existsById(id));
    }

    public String createAccessToken(int id, Role role) {
        if (!isCurAccountExist(id, role)) {
            throw new BusinessException(NOT_EXIST_ACCOUNT);
        }
        return tokenProvider.createAccessToken(id, role);
    }

    public String createRefreshToken(int id, Role role) {
        // 로그인 상태인지 체크 후 로그아웃 진행
        if (refreshTokenRepository.existsById(new RefreshTokenId(id, role))) {
            logout(id, role);
        }
        if (!isCurAccountExist(id, role)) {
            throw new BusinessException(NOT_EXIST_ACCOUNT);
        }
        // refresh token 생성 및 저장
        String token = tokenProvider.createRefreshToken();
        RefreshToken refreshToken = refreshTokenRepository.save(new RefreshToken(token, id, role));
        return refreshToken.getToken();
    }

    public TokenDto childLogin(LoginDto loginDto) {
        // email id 비교
        Child child = childRepository.findByNickname(loginDto.getNickname())
                .orElseThrow(() -> new BusinessException(INVALID_ACCOUNT));
        // password 비교
        if (!passwordEncoder.matches(loginDto.getPassword(), child.getPassword())) {
            throw new BusinessException(INVALID_ACCOUNT);
        }
        Map<String, String> resMap = new HashMap<>();
        resMap.put("accessToken", createAccessToken(child.getId(), Role.ROLE_CHILD));
        resMap.put("refreshToken", createRefreshToken(child.getId(), Role.ROLE_CHILD));
        return new TokenDto(resMap);
    }

    public void logout(int id, Role role) {
        refreshTokenRepository.deleteById(new RefreshTokenId(id, role));
    }

    public TokenDto tokenReissue(TokenDto tokenIssueDto) {
        // access token 유효성 검증
        if (tokenProvider.isTokenValid(tokenIssueDto.getAccessToken()).equals("invalid")) {
            throw new BusinessException(INVALID_TOKEN);
        }

        // refresh token 비교
        RefreshToken refreshToken = refreshTokenRepository.findByToken(tokenIssueDto.getRefreshToken())
                .orElseThrow(() -> new BusinessException(NOT_EXIST_REFRESH_TOKEN));

        Map<String, String> resMap = new HashMap<>();

        // refresh token 비교 후 재발급
        resMap.put("accessToken", createAccessToken(refreshToken.getId().getSubjectId(), refreshToken.getId().getSubjectRole()));     // 재발급된 access token
        resMap.put("refreshToken", refreshToken.getToken());  // 기존 refresh token

        return new TokenDto(resMap);
    }
}
