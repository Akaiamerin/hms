package com.hms.entity;
public class Course {
    private Integer id;
    private Integer courseTypeId;
    private String no;
    private String title;
    private Integer teacherId;
    private String detail;
    public Course() {

    }
    public Course(Integer id, Integer courseTypeId, String no, String title, Integer teacherId, String detail) {
        this.id = id;
        this.courseTypeId = courseTypeId;
        this.no = no;
        this.title = title;
        this.teacherId = teacherId;
        this.detail = detail;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCourseTypeId() {
        return courseTypeId;
    }
    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
}