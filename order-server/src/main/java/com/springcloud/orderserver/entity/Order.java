package com.springcloud.orderserver.entity;


import lombok.*;

import java.io.Serializable;
import java.util.Date;

//@Data@AllArgsConstructor@NoArgsConstructor
public class Order implements Serializable {

    private Long userId;
    private String orderNo;
    private Date createTime;
    private String prodcutName;
    private int productPrice;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProdcutName() {
        return prodcutName;
    }

    public void setProdcutName(String prodcutName) {
        this.prodcutName = prodcutName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public Order() {
    }

    public Order(Long userId, String orderNo, Date createTime, String prodcutName, int productPrice) {
        this.userId = userId;
        this.orderNo = orderNo;
        this.createTime = createTime;
        this.prodcutName = prodcutName;
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", orderNo='" + orderNo + '\'' +
                ", createTime=" + createTime +
                ", prodcutName='" + prodcutName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
