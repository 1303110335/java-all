package com.redis.example.demo.vcard;

/**
 * @Author: mzd
 * @Date: 2020/6/19 14:05
 */
public enum PickUpStatusEnum {
    WAIT_PICK_UP(0, "未取号"),
    PICK_UP(1, "已取号"),
    PICK_UP_ING(2, "取号中"),
    PICK_UP_FAIL(3, "取号失败");

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    PickUpStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    PickUpStatusEnum() {
    }
}
