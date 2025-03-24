package com.wru.wrubookstore.repository;

import com.wru.wrubookstore.domain.OrderSearchCondition;
import com.wru.wrubookstore.dto.request.order.OrderBookRequest;
import com.wru.wrubookstore.dto.OrderDto;
import com.wru.wrubookstore.dto.request.order.OrderHistoryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryImplTest {

    @Autowired
    OrderRepository orderRepository;
    Integer userId = 1;
    Integer orderId = 1;

    @Test
    public void selectTest() throws Exception {
        OrderDto orderDto = orderRepository.select(orderId);

        assertEquals(orderDto.getUserName(), "홍길동");
        assertEquals(orderDto.getUserId(), 1);
        assertEquals(orderDto.getStatusId(), "DS1");
    }

    @Test
    public void selectListTest() throws Exception {
        String startDate = "2025-03-16";
        String endDate = "2025-03-21";

        List<OrderDto> orderDtoList = orderRepository.selectList(Map.of("userId", userId, "startDate", startDate, "endDate", endDate));

        assertEquals(orderDtoList.size(), 1);
    }

    @Test
    public void selectListByOscTest() throws Exception {
        OrderSearchCondition osc = new OrderSearchCondition();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("osc", osc);

        List<OrderHistoryRequest> orderHistory1 = orderRepository.selectListByOsc(map);
        OrderHistoryRequest order = orderHistory1.getFirst();

        assertEquals(order.getOrderId(), 1);
        assertEquals(order.getUserName(), "홍길동");
        assertEquals(order.getBookName(), "인생 뭐, 야구");
    }

    @Test
    public void selectListByOscTest2() throws Exception {
        OrderSearchCondition osc = new OrderSearchCondition();
        osc.setStatusId("DS2");
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("osc", osc);

        List<OrderHistoryRequest> orderHistory = orderRepository.selectListByOsc(map);

        assertEquals(orderHistory.size(), 0);
    }

    @Test
    public void selectCntTest() throws Exception {
        int orderCnt = orderRepository.selectCnt(userId);

        assertEquals(orderCnt, 1);
    }

    @Test
    public void selectCntByOscTest() throws Exception {
        Integer userId = 3;
        String statusId = "A";
        String startDate = "2025-03-18";
        String endDate = "2025-03-20";
        OrderSearchCondition osc = new OrderSearchCondition(startDate, endDate, statusId);

        int orderCnt = orderRepository.selectCntByOsc(Map.of("userId", userId, "osc", osc));

        assertEquals(orderCnt, 15);
    }

    @Test
    public void selectOrderBookTest() throws Exception {
        List<OrderBookRequest> orderBookRequestList = orderRepository.selectOrderBook(orderId);
        OrderBookRequest firstOrderBookRequest = orderBookRequestList.getFirst();
        OrderBookRequest lastOrderBookRequest = orderBookRequestList.getLast();

        assertEquals(orderBookRequestList.size(), 2);
        assertEquals(firstOrderBookRequest.getName(), "인생은 순간이다");
        assertEquals(firstOrderBookRequest.getOrderPrice(), 17820);
        assertEquals(firstOrderBookRequest.getQuantity(), 1);
        assertEquals(lastOrderBookRequest.getName(), "인생 뭐, 야구");
        assertEquals(lastOrderBookRequest.getOrderPrice(), 84150);
        assertEquals(lastOrderBookRequest.getQuantity(), 5);
    }
}