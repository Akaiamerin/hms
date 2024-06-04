package com.hms.pojo.vo;
public class StudentVO {
    private Integer id;
    private String username;
    private String no;
    private String name;
    public StudentVO() {

    }
    public StudentVO(Integer id, String username, String no, String name) {
        this.id = id;
        this.username = username;
        this.no = no;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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