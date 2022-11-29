package com.jvision.admin.web;

import com.jvision.admin.config.auth.dto.SessionUser;
import com.jvision.admin.service.posts.PostsService;
import com.jvision.admin.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class indexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/") //localhost:8080
    public String index(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
        SessionUser user = (SessionUser)httpSession.getAttribute("user");
        if(user!=null)
            model.addAttribute("userName",user.getName());
        return "index"; //index.mustache
    }

    @GetMapping("/posts/save")
    public String PostsSave(){
        return "posts-save";
    }
    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}