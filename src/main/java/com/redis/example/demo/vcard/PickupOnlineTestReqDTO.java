package com.redis.example.demo.vcard;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @Author: mzd
 * @Date: 2020/7/29 10:31
 */
public class PickupOnlineTestReqDTO implements Serializable {
    private static final long serialVersionUID = -1L;
    /**
     * 姓名 非空
     */
    private String name;

    @JSONField(name = "med_org_code")
    private String medOrgCode;

    @JSONField(name = "idcard_value")
    private String idCardNo;

    @JSONField(name = "idcard_type")
    private String idCardType;

    @JSONField(name = "request_url")
    private String requestUrl;

    @JSONField(name = "order_id")
    private String orderId;

    @JSONField(name = "order_no")
    private String orderNo;

    @JSONField(name = "bq_trade_no")
    private String bqTradeNo;

    @JSONField(name = "pay_channel_trade_no")
    private String payChannelTradeNo;

    @JSONField(name = "pay_channel")
    private String payChannel;

    @JSONField(name = "pay_time")
    private String payTime;

    @JSONField(name = "pay_amount")
    private String payAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMedOrgCode() {
        return medOrgCode;
    }

    public void setMedOrgCode(String medOrgCode) {
        this.medOrgCode = medOrgCode;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBqTradeNo() {
        return bqTradeNo;
    }

    public void setBqTradeNo(String bqTradeNo) {
        this.bqTradeNo = bqTradeNo;
    }

    public String getPayChannelTradeNo() {
        return payChannelTradeNo;
    }

    public void setPayChannelTradeNo(String payChannelTradeNo) {
        this.payChannelTradeNo = payChannelTradeNo;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    @Override
    public String toString() {
        return "PickupOnlineTestReqDTO{" +
                "name='" + name + '\'' +
                ", medOrgCode='" + medOrgCode + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", idCardType='" + idCardType + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", bqTradeNo='" + bqTradeNo + '\'' +
                ", payChannelTradeNo='" + payChannelTradeNo + '\'' +
                ", payChannel='" + payChannel + '\'' +
                ", payTime='" + payTime + '\'' +
                ", payAmount='" + payAmount + '\'' +
                '}';
    }
}
