package com.wru.wrubookstore.dao;

import com.wru.wrubookstore.dto.NoticeDto;
import com.wru.wrubookstore.domain.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NoticeDao {

    @Autowired
    SqlSessionTemplate session;
    String namespace = "com.wru.wrubookstore.dao.noticeMapper.";

    public NoticeDto select(Integer noticeId) throws Exception {
        return session.selectOne(namespace + "select", noticeId);
    } // T selectOne(String statement, Object parameter)

    public List<NoticeDto> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    } // List<E> selectList(String statement)

    public int insert(NoticeDto dto) throws Exception {
        return session.insert(namespace + "insert", dto);
    } // int insert(String statement, Object parameter)

    public int delete(Integer noticeId, String employeeId) throws Exception {
        Map map = new HashMap();
        map.put("noticeId", noticeId);
        map.put("employeeId", employeeId);
        return session.delete(namespace + "delete", map);
    } // int delete(String statement, Object parameter)

    public int deleteAll() throws Exception{
        return session.delete(namespace+"deleteAll");
    } // int delete(String statement)

    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    } // T selectOne(String statement)

    public List<NoticeDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage", map);
    } // List<E> selectList(String statement, Object parameter)

    public int update(NoticeDto dto) throws Exception {
        return session.update(namespace+"update", dto);
    } // int update(String statement, Object parameter)

    public int increaseViewCnt(Integer noticeId) throws Exception {
        return session.update(namespace+"increaseViewCnt", noticeId);
    }
    public int searchResultCnt(SearchCondition sc) throws Exception {
        System.out.println("sc in searchResultCnt() = " + sc);
        System.out.println("session = " + session);
        return session.selectOne(namespace+"searchResultCnt", sc);
    } // T selectOne(String statement, Object parameter)

    public List<NoticeDto> searchSelectPage(SearchCondition sc) throws Exception {
        return session.selectList(namespace+"searchSelectPage", sc);
    } // List<E> selectList(String statement, Object parameter)

}







