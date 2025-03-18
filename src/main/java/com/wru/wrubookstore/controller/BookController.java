package com.wru.wrubookstore.controller;

import com.wru.wrubookstore.domain.PageHandler;
import com.wru.wrubookstore.dto.BookDto;
import com.wru.wrubookstore.repository.BookRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    BookRepository bookRepo;

    BookController(BookRepository bookRepo) {this.bookRepo = bookRepo;}

    @GetMapping("/bookList")
    public String bookList(String category, Integer page, Integer pageSize, Integer viewBookCnt,Model m) {
        // 홈페이지에서 카테고리를 눌러서 들어온다.
        // 카테고리 id가 넘어오고
        // 카테고리 id로 해당 카테고리의 bookList를 보여준다.

        // 1. 페이징
        // page가 null 이면 기본값추가
        if(page==null)
            page=1;
        // pageSize가 null 이면 기본값추가
        if(pageSize==null)
            pageSize=10;
        // viewBookCnt가 null 이면 기본값추가
        if(viewBookCnt==null)
            viewBookCnt=8;
        if(category==null)
            category="cs_2";

        try{
            // 카테고리 id에 있는 책의 수를 가져온다.
            int bookCnt = bookRepo.sCategoryCnt(category);
            // 페이지 만듬
            PageHandler pageHandler = new PageHandler(bookCnt, page, pageSize, viewBookCnt);

            // 2. 책 정보 날려주기
            // Map에 category, offset, limit를 put
            Map map = new HashMap();
            // PageHandler로 넘겨주기
            map.put("category", category);
            // 0-8-16-24
            map.put("offset", (page-1)*viewBookCnt);
            // pageSize 만큼 뽑기
            map.put("limit", viewBookCnt);
            List<BookDto> list = bookRepo.selectRegList(map);

            // 3. Model 에 담아서 view 로 넘기기
            m.addAttribute("ph", pageHandler);
            m.addAttribute("list", list);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "book/book-list";
    }

    @GetMapping("/bookDetail")
    public String bookDetail() {

        return "book/book-detail";
    }
}
