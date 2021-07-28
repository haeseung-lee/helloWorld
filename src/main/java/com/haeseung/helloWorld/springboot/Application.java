package com.haeseung.helloWorld.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //내장 WAS 실행 -> 톰캣을 설치할 필요가 없음
        SpringApplication.run(Application.class, args);
    }
}
