package com.wru.wrubookstore.service;

import com.wru.wrubookstore.dto.CartDto;
import com.wru.wrubookstore.dto.response.cart.CartListResponse;

import java.util.List;

public interface CartService {

    // 관리자용
    int countAllByAdmin() throws Exception;
    List<CartDto> selectAllByAdmin() throws Exception;
    void deleteAllByAdmin() throws Exception;

    // 사용자용
    int countAllByUserId(int userId) throws Exception;
    List<CartDto> selectAllByUserId(int userId) throws Exception;
    List<CartListResponse> selectCartListByUserId(int userId) throws Exception;
    CartListResponse selectCartByCartId(int cartId)  throws Exception;
    CartDto selectByUserIdAndBookId(int userId, int bookId) throws Exception;
    String insert(CartDto cartDto) throws Exception;
    int update(CartDto cartDto) throws Exception;
    int delete(int cartId) throws Exception;

}
