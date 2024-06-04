package com.hms.entity;
import java.time.LocalDateTime;
public class CourseSelection {
    private Integer id;
    private Integer courseId;
    private Integer teacherId;
    private Integer studentId;
    private LocalDateTime createTime;
    public CourseSelection() {

    }
    public CourseSelection(Integer id, Integer courseId, Integer teacherId, Integer studentId, LocalDateTime createTime) {
        this.id = id;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.createTime = createTime;
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
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}