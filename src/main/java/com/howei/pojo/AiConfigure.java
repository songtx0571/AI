package com.howei.pojo;

/**
 * 树莓派
 */
public class AiConfigure {

    private int id;
    private String address;//设备名称
    private int worked;//运行与否
    private String Inquiry;//配置
    private String type;//类型

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWorked() {
        return worked;
    }

    public void setWorked(int worked) {
        this.worked = worked;
    }

    public String getInquiry() {
        return Inquiry;
    }

    public void setInquiry(String inquiry) {
        Inquiry = inquiry;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
