package com.haeseung.helloWorld.springboot.web;

import com.haeseung.helloWorld.springboot.config.auth.LoginUser;
import com.haeseung.helloWorld.springboot.config.auth.dto.SessionUser;
import com.haeseung.helloWorld.springboot.service.posts.PostsService;
import com.haeseung.helloWorld.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

        //앞서 작성된 CustomOAuth2UserService 에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성하였음
        //@LoginUser 를 통해 반복되는 코드 개선
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){

            model.addAttribute("memberName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
