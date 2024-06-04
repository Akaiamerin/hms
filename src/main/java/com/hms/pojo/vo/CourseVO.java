package com.hms.pojo.vo;
public class CourseVO {
    private Integer id;
    private String no;
    private String title;
    private String courseTypeTitle;
    private String teacherName;
    private String detail;
    public CourseVO() {

    }
    public CourseVO(Integer id, String no, String title, String courseTypeTitle, String teacherName, String detail) {
        this.id = id;
        this.no = no;
        this.title = title;
        this.courseTypeTitle = courseTypeTitle;
        this.teacherName = teacherName;
        this.detail = detail;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public String getCourseTypeTitle() {
        return courseTypeTitle;
    }
    public void setCourseTypeTitle(String courseTypeTitle) {
        this.courseTypeTitle = courseTypeTitle;
    }
    public String getTeacherName() {
        return teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
}