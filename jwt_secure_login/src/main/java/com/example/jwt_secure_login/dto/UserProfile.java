package com.example.jwt_secure_login.dto;

import lombok.Getter;
import com.example.jwt_secure_login.model.User;

@Getter
public class UserProfile {

    private String username; // 사용자 이름
    private String provider; // 로그인한 서비스
    private String email; // 사용자의 이메일
    private String profileImage;

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // DTO 파일을 통하여 Entity를 생성하는 메소드
    public User toEntity() {
        return User.builder()
                .username(this.username)
                .email(this.email)
                .provider(this.provider)
                .build();
    }

}
