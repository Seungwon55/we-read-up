package com.wru.wrubookstore.controller;

import com.wru.wrubookstore.dto.MemberDto;
import com.wru.wrubookstore.dto.UserDto;
import com.wru.wrubookstore.service.MemberService;
import com.wru.wrubookstore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;
    private final MemberService memberService;

    public RegisterController(UserService userService, MemberService memberService) {
        this.userService = userService;
        this.memberService = memberService;
    }

    @GetMapping("/form")
    public String registerForm() {

        return "login/register";
    }

    @PostMapping("/user")
    public String registerUser(UserDto userDto, RedirectAttributes rattr, Model model) {
        try {
            int rowCnt = userService.insert(userDto);

            if(rowCnt != 1)
                throw new Exception("비회원 계정 생성 실패");

            rattr.addFlashAttribute("msg", "USER_SIGNUP_OK");
        } catch (Exception e) {
            model.addAttribute("userDto", userDto);
            model.addAttribute("msg", "USER_SIGNUP_ERR");
            return "login/register";
        }
        return "redirect:/";
    }

    @PostMapping("/member")
    public String registerMember(UserDto userDto, MemberDto memberDto, RedirectAttributes rattr, Model model) {
        try {
            memberService.insert(userDto, memberDto);
            rattr.addFlashAttribute("msg", "MEMBER_SIGNUP_OK");
        } catch (Exception e) {
            model.addAttribute("memberDto", memberDto);
            model.addAttribute("msg", "MEMBER_SIGNUP_ERR");
            return "login/register";
        }
        return "redirect:/";
    }
}
