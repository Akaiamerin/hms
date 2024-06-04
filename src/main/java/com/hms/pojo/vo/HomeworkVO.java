package com.hms.pojo.vo;
public class HomeworkVO {
    private Integer id;
    private String courseTitle;
    private String teacherName;
    private String status;
    private String content;
    private String attachment;
    private String deadline;
    public HomeworkVO() {

    }
    public HomeworkVO(Integer id, String courseTitle, String teacherName, String status, String content, String attachment, String deadline) {
        this.id = id;
        this.courseTitle = courseTitle;
        this.teacherName = teacherName;
        this.status = status;
        this.content = content;
        this.attachment = attachment;
        this.deadline = deadline;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCourseTitle() {
        return courseTitle;
    }
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
    public String getTeacherName() {
        return teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAttachment() {
        return attachment;
    }
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}