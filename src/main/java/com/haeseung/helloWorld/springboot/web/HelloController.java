package com.haeseung.helloWorld.springboot.web;

import com.haeseung.helloWorld.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//간단한 API 만들기
@RestController
//컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 줌. ResponseBody를 메소드마다 선언했던 것을 한번에 사용할 수 있게 해주는 걸로 생각
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){

        return new HelloResponseDto(name, amount);
    }
}
