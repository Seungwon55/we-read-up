package com.wru.wrubookstore.repository;

import com.wru.wrubookstore.dto.BookDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BookRepository {
    private final SqlSessionTemplate session;
    private final String namespace = "hello.board.board.mapper.BookMapper.";

    public BookRepository(SqlSessionTemplate session) {
        this.session = session;
    }

    // 책 번호로 한개 조회
    public BookDto select(int bookId) {
        return session.selectOne(namespace + "select", bookId);
    }
    // LIMIT로 N개 조회 category(카테고리 소), offset, limit
    public List<BookDto> selectRegList(Map map) { return session.selectList(namespace + "selectRegList", map);}
    // 카테고리에 있는 책 수 조회
    public int sCategoryCnt(String category){ return session.selectOne(namespace + "sCategoryCnt", category);}
}
