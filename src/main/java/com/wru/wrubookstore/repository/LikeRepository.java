package com.wru.wrubookstore.repository;

import com.wru.wrubookstore.dto.LikeDto;

import java.util.Map;

public interface LikeRepository {
    // 해당 책의 좋아요 수 조회
    Integer likeCount(Integer bookId) throws Exception;
    // 현재 유저가 해당 책을 좋아요 눌렀는지 확인
    Integer selectLikeMember(LikeDto likeDto) throws Exception;
    // 해당 책을 좋아요에 추가
    void insertLike(LikeDto likeDto) throws Exception;
    // 해당 책에 누른 좋아요 삭제
    void deleteLike(LikeDto likeDto) throws Exception;
}
