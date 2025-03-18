package com.wru.wrubookstore.service;

import com.wru.wrubookstore.dto.BookDto;

import java.util.List;
import java.util.Map;

public interface BookService {
    // 책 번호로 한개 조회
    BookDto select(Integer bookId) throws Exception;

    // LIMIT로 N개 조회 category(카테고리 소), offset, limit
    List<BookDto> selectRegList(Map map) throws Exception;

    // 카테고리에 있는 책 수 조회
    int sCategoryCnt(String category) throws Exception;

    // 테스트용 insert
    void insert(BookDto book) throws Exception;
}
