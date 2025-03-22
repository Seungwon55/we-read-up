package com.wru.wrubookstore.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyPageController {

    @GetMapping("/myPage")
    public String myPage(HttpServletRequest request) {
        // 로그인 하지 않았다면 로그인 화면으로 이동
        if(!loginCheck(request))
            return "redirect:/login/login?redirectUrl="+request.getRequestURL();

        return "/myPage/my-page";
    }

    // 세션에 userId 저장되어 있는지 확인
    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("userId") != null;
    }

}
