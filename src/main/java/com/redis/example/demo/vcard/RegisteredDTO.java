package com.redis.example.demo.vcard;


import java.io.Serializable;

/**
 * @Author: mzd
 * @Date: 2020/2/10 17:15
 */
public class RegisteredDTO implements Serializable {
    private static final long serialVersionUID = -1999736781830265747L;
    /**
     * 0 未取号  1 已取号  2取号中 3 取号失败
     */
    private Integer pickUpStatus = PickUpStatusEnum.WAIT_PICK_UP.getCode();

    /**
     * 预约id
     */
    private String orderId;
    /**
     * 预约号状态：
     * 0未取号  1已取号  2已取消 3全部
     */
    private String orderStaus;

    private String isPay;

    private String orderNo;
    /**
     * 患者姓名
     */
    private String name;
    /**
     * 门诊号
     */
    private String pId;

    private String orderType;
    /**
     * 科室名称
     */
    private String deptName;
    /**
     * 诊疗科室
     */
    private String treatmentName;
    /**
     * 就诊时间
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    private String schDate;
    /**
     * 就诊场次
     */
    private String ampm;
    /**
     * 医生姓名
     */
    private String doctorName;
    /**
     * 号类：1普通科 2名医  3专家科
     */
    private String regClass;
    /**
     * 挂号费（单位：元）
     */
    private String regFee;
    /**
     * 诊查费（单位：元）
     */
    private String fee;
    /**
     * 就诊序号
     */
    private String regNum;
    /**
     * 就诊地址
     */
    private String regAddr;
    /**
     * 备注
     */
    private String remark;
    /**
     * 院区编码
     */
    private String orgId;
    /**
     * 取号时间
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    private String operDate;

    private String medOrgName;
    private String medOrgCode;

    private String gmtCreate;

    public Integer getPickUpStatus() {
        return pickUpStatus;
    }

    public void setPickUpStatus(Integer pickUpStatus) {
        this.pickUpStatus = pickUpStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStaus() {
        return orderStaus;
    }

    public void setOrderStaus(String orderStaus) {
        this.orderStaus = orderStaus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public String getSchDate() {
        return schDate;
    }

    public void setSchDate(String schDate) {
        this.schDate = schDate;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getRegClass() {
        return regClass;
    }

    public void setRegClass(String regClass) {
        this.regClass = regClass;
    }

    public String getRegFee() {
        return regFee;
    }

    public void setRegFee(String regFee) {
        this.regFee = regFee;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOperDate() {
        return operDate;
    }

    public void setOperDate(String operDate) {
        this.operDate = operDate;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    public String getMedOrgName() {
        return medOrgName;
    }

    public void setMedOrgName(String medOrgName) {
        this.medOrgName = medOrgName;
    }

    public String getMedOrgCode() {
        return medOrgCode;
    }

    public void setMedOrgCode(String medOrgCode) {
        this.medOrgCode = medOrgCode;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
