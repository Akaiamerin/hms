package com.hms.pojo.vo;
public class HomeworkRecordVO {
    private Integer id;
    private Integer homeworkId;
    private Double score;
    private String teacherComment;
    public HomeworkRecordVO() {

    }
    public HomeworkRecordVO(Integer id, Integer homeworkId, Double score, String teacherComment) {
        this.id = id;
        this.homeworkId = homeworkId;
        this.score = score;
        this.teacherComment = teacherComment;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getHomeworkId() {
        return homeworkId;
    }
    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }
    public String getTeacherComment() {
        return teacherComment;
    }
    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }
}