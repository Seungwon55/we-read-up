package com.wru.wrubookstore.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class CategoryDto {
    private String categoryType; // �з� Ÿ�� - ��/��/��

    private String categoryLargeId;
    private String categoryLargeName;
    private String categoryMediumId;
    private String categoryMediumName;
    private String categorySmallId;
    private String categorySmallName;

    private Integer bookId;             // å �ڵ�
    private String publisherId;         // ���ǻ� �ڵ�
    private String name;                // å �̸�
    private String translator;          // ����
    private Integer originalPrice;          // ����
    private BigDecimal discountPercent; // ������ 1 ~ 0.00
    private Integer discountPrice;          // ���εǴ� ����
    private Integer salePrice;              // �ǸŰ�
    private Date releaseDate;           // �����
    private Date regDate;               // �Ǹ���
    private String tableOfContent;      // ���� ����
    private String description;         // ���� �Ұ�
    private long isbn;                  // å ������ȣ
    private Integer stockQuantity;          // ��� ����
    private String size;                // ���� ������
    private Integer weight;                 // ���� ����
    private Integer page;                   // ���� ������ ��
    private String image;               // ������ �̹���

    public CategoryDto() {}

    public CategoryDto(String categoryLargeId, String categoryLargeName, String categoryMediumId, String categoryMediumName, String categorySmallId, String categorySmallName) {
        this.categoryLargeId = categoryLargeId;
        this.categoryLargeName = categoryLargeName;
        this.categoryMediumId = categoryMediumId;
        this.categoryMediumName = categoryMediumName;
        this.categorySmallId = categorySmallId;
        this.categorySmallName = categorySmallName;
    }
}
