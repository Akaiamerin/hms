package com.hms.entity;
import java.time.LocalDateTime;
public class Homework {
    private Integer id;
    private Integer courseId;
    private Integer teacherId;
    private Integer studentId;
    private Integer homeworkStatusId;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime deadline;
    public Homework() {

    }
    public Homework(Integer id, Integer courseId, Integer teacherId, Integer studentId, Integer homeworkStatusId, String content, LocalDateTime createTime, LocalDateTime updateTime, LocalDateTime deadline) {
        this.id = id;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.homeworkStatusId = homeworkStatusId;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deadline = deadline;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCourseId() {
        return courseId;
    }
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    public Integer getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
    public Integer getStudentId() {
        return studentId;
    }
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public Integer getHomeworkStatusId() {
        return homeworkStatusId;
    }
    public void setHomeworkStatusId(Integer homeworkStatusId) {
        this.homeworkStatusId = homeworkStatusId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public LocalDateTime getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}