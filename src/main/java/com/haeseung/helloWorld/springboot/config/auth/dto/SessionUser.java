package com.haeseung.helloWorld.springboot.config.auth.dto;

import com.haeseung.helloWorld.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    /*SessionUser 에는 인증된 사용자 정보만 필요함.*/
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
