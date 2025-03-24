package com.wru.wrubookstore.service;

import com.wru.wrubookstore.domain.MainSearchCondition;
import com.wru.wrubookstore.dto.BookDto;
import com.wru.wrubookstore.dto.response.book.BookListResponse;

import java.util.List;
import java.util.Map;

public interface BookService {

    // 관리자용
    int countAllByAdmin() throws Exception;
    List<BookDto> selectAllByAdmin() throws Exception;
    void deleteAllByAdmin() throws Exception;
    void updateByAdmin(BookListResponse bookListResponse) throws Exception;
    int countQuantityZeroByAdmin() throws Exception;
    void deleteByAdmin(BookListResponse bookListResponse) throws Exception;
    List<BookDto> selectBook(Map map) throws Exception;

    // 책 번호로 한개 조회
    BookDto select(Integer bookId) throws Exception;

    // LIMIT로 N개 조회 category(카테고리 소), offset, limit
    List<BookDto> selectRegList(Map map) throws Exception;

    // 카테고리에 있는 책 수 조회
    int sCategoryCnt(String category) throws Exception;

    // 테스트용 insert
    int insert(BookDto book) throws Exception;

    // 각 책의 지은이들을 조회
    List<String> selectWriter(Integer bookId) throws Exception;

    // 각 책의 출판사를 조회
    String selectPublisher(Integer bookId) throws Exception;

    //  도서 제목과 저자 이름으로 통합 검색
    List<BookDto> selectByAll(MainSearchCondition sc) throws Exception;

    // 도서 제목으로 검색
    List<BookDto> selectByTitle(MainSearchCondition sc) throws Exception;

    // 저자 이름으로 검색
    List<BookDto> selectByWriter(MainSearchCondition sc) throws Exception;

    // 검색 결과 개수 조회
    int selectSearchCnt(MainSearchCondition sc) throws Exception;

}
