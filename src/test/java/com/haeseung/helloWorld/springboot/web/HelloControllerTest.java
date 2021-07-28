package com.haeseung.helloWorld.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)//JUnit에 내장된 실행자가 아닌 스프링 실행자를 사용.
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;
    /* MockMvc
    * 웹 API를 테스트 할 때 사용
    * 스프링 MVC 테스트의 시작점
    * HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.
    * */

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))  //MockMvc를 통해 /hello주소로 HTTP GET 요청을 함. 체이닝 지원 -> 여러 검증 기능 이어서 선언가능
                .andExpect(status().isOk()) //mvc.perform의 결과를 검증, Header의 Status를 검증(200인지 아닌지)
                .andExpect(content().string(hello));//응답 본문의 내용을 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name", is(name)))
                        .andExpect(jsonPath("$.amount",is(amount)));


    }
}
