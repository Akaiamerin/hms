package com.hms.pojo.vo;
public class CourseSelectionVO {
    private Integer id;
    private CourseVO courseVO;
    private TeacherVO teacherVO;
    private StudentVO studentVO;
    private String createTime;
    public CourseSelectionVO() {

    }
    public CourseSelectionVO(Integer id, CourseVO courseVO, TeacherVO teacherVO, StudentVO studentVO, String createTime) {
        this.id = id;
        this.courseVO = courseVO;
        this.teacherVO = teacherVO;
        this.studentVO = studentVO;
        this.createTime = createTime;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public CourseVO getCourseVO() {
        return courseVO;
    }
    public void setCourseVO(CourseVO courseVO) {
        this.courseVO = courseVO;
    }
    public TeacherVO getTeacherVO() {
        return teacherVO;
    }
    public void setTeacherVO(TeacherVO teacherVO) {
        this.teacherVO = teacherVO;
    }
    public StudentVO getStudentVO() {
        return studentVO;
    }
    public void setStudentVO(StudentVO studentVO) {
        this.studentVO = studentVO;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}