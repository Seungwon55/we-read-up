package com.wru.wrubookstore.repository;

import com.wru.wrubookstore.domain.MainSearchCondition;
import com.wru.wrubookstore.dto.BookDto;
import com.wru.wrubookstore.dto.CategoryDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final SqlSessionTemplate session;
    private final String namespace = "com.wru.wrubookstore.mapper.BookMapper.";

    public BookRepositoryImpl(SqlSessionTemplate session) {
        this.session = session;
    }

    // 카테고리 정보 조회
    @Override
    public CategoryDto selectCategoryInfo(String category) throws Exception {
        CategoryDto categoryDto = (CategoryDto) session.selectList(namespace + "selectCategoryInfo", category).get(0);
        categoryDto.setCategoryType(category.substring(0, 2));
        return categoryDto;
    }

    // 카테고리에 속한 책들의 수 조회
    @Override
    public int selectByCategoryCnt(String category) throws Exception {
        return session.selectOne(namespace + "selectByCategoryCnt", category);
    }

    // 카테고리에 속한 책들의 정보 조회
    @Override
    public List<CategoryDto> selectByCategory(MainSearchCondition sc) throws Exception {
        List<CategoryDto> list = session.selectList(namespace + "selectByCategory", sc);
        list.get(0).setCategoryType(sc.getCategory().substring(0, 2));
        return list;
    }

    // 책 번호로 한 개 조회
    @Override
    public BookDto select(Integer bookId) throws Exception{
        return session.selectOne(namespace + "select", bookId);
    }

    // 테스트용 insert
    @Override
    public void insert(BookDto book)  throws Exception{
        session.insert(namespace + "insert", book);
    }

    // 각 책의 지은이들을 조회
    @Override
    public List<String> selectWriter(Integer bookId) throws Exception {
        return session.selectList(namespace + "selectWriter", bookId);
    }

    // 각 책의 출판사를 조회
    @Override
    public String selectPublisher(Integer bookId) throws Exception{
        return session.selectOne(namespace + "selectPublisher", bookId);
    }

    //  도서 제목과 저자 이름으로 통합 검색
    public List<BookDto> searchByAll(MainSearchCondition sc) throws Exception {
        return session.selectList(namespace + "searchByAll", sc);
    }

    // 도서 제목으로 검색
    public List<BookDto> searchByTitle(MainSearchCondition sc) throws Exception {
        return session.selectList(namespace + "searchByTitle", sc);
    }

    // 저자 이름으로 검색
    public List<BookDto> searchByWriter(MainSearchCondition sc) throws Exception {
        return session.selectList(namespace + "searchByWriter", sc);
    }

    // 검색 결과 개수 조회
    @Override
    public int selectSearchCnt(MainSearchCondition sc) throws Exception {
        return session.selectOne(namespace + "selectSearchCnt", sc);
    }
}
