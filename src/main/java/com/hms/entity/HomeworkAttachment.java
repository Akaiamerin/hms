package com.hms.entity;
import java.time.LocalDateTime;
public class HomeworkAttachment {
    private Integer id;
    private Integer homeworkId;
    private String title;
    private String attachment;
    private LocalDateTime createTime;
    public HomeworkAttachment() {

    }
    public HomeworkAttachment(Integer id, Integer homeworkId, String title, String attachment, LocalDateTime createTime) {
        this.id = id;
        this.homeworkId = homeworkId;
        this.title = title;
        this.attachment = attachment;
        this.createTime = createTime;
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
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAttachment() {
        return attachment;
    }
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}