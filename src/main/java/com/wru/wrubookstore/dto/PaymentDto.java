package com.wru.wrubookstore.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter
@ToString
public class PaymentDto {

    private Integer paymentId;
    private Integer memberCouponId;
    private Integer orderId;
    private String statusId;
    private Date paymentDate;
    private String paymentMethod;
    private int totalPrice;
    private int mileageDiscount;
    private int couponDiscount;
    private int actualPrice;
}
