package com.wru.wrubookstore.controller;

import com.wru.wrubookstore.dto.LikeDto;
import com.wru.wrubookstore.dto.MemberDto;
import com.wru.wrubookstore.repository.LikeRepository;
import com.wru.wrubookstore.service.BookService;
import com.wru.wrubookstore.service.LikeService;
import com.wru.wrubookstore.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class likeController {
    LikeService likeService;
    MemberService memberService;

    likeController(LikeService likeService, MemberService memberService) {
        this.likeService = likeService;
        this.memberService = memberService;
    }

    @GetMapping("/myPage/likeList")
    public String likeList() {

        return "myPage/like-list";
    }

    @PostMapping("/book/like")
    @ResponseBody
    public String likeList(@RequestBody LikeDto likeDto, HttpSession session, Model m){
        // book-detail.html에서 like를 눌러서 bookId와 session에 등록된 id를 넘겨주면
        // [세션에 등록된 id에서 member_id를 조인해와서]
        // 검증... like에 member_id중 book_id가 이미 있다면 like 삭제
        // like에 member_id중 book_id가 없다면 like추가

        try{
            System.out.println("likeDto = " + likeDto);

            int isLikeUser = likeService.selectLikeMember(likeDto);
            m.addAttribute("isLikeUser", isLikeUser);

            // member_id가 book_id를 like하지 않음 - like 추가
            if (isLikeUser == 0) {
                System.out.println(session.getAttribute("name")+"좋아요 누름" + isLikeUser);
                likeService.insertLike(likeDto);
            } else {
                System.out.println(session.getAttribute("name")+"좋아요 삭제함" + isLikeUser);
                likeService.deleteLike(likeDto);
            }

            System.out.println("isLikeUser = " + isLikeUser);

            return "success";

        } catch(Exception e){
            e.printStackTrace();
            return "error";
        }

    }
}
