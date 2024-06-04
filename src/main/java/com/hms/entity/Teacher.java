package com.hms.entity;
public class Teacher {
    private Integer id;
    private Integer userId;
    private String no;
    private String name;
    public Teacher() {

    }
    public Teacher(Integer id, Integer userId, String no, String name) {
        this.id = id;
        this.userId = userId;
        this.no = no;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}