package com.hms.utils;
import com.hms.entity.CourseType;
import com.hms.service.CourseTypeService;
import com.hms.service.TeacherService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import org.springframework.stereotype.Component;
@Component
public class ValidUtils {
    @Resource
    private TeacherService defaultTeacherService;
    private static TeacherService teacherService;
    @Resource
    private CourseTypeService defaultCourseTypeService;
    private static CourseTypeService courseTypeService;
    private ValidUtils() {

    }
    @PostConstruct
    public void init() {
        teacherService = defaultTeacherService;
        courseTypeService = defaultCourseTypeService;
    }
    public static boolean isValidUsername(String username) {
        return username.matches("^[0-9a-zA-Z]{6,20}$");
    }
    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{6,20}$");
    }
    public static boolean isValidRole(String role) {
        return Arrays.asList("admin", "teacher", "student").contains(role);
    }
    public static boolean isValidNo(String no) {
        return no.matches("^[0-9a-zA-Z]{1,20}$");
    }
    public static boolean isValidName(String name) {
        return name.matches("^.{1,20}$");
    }
    public static boolean isValidCourseNo(String courseNo) {
        return courseNo.matches("^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{6}$");
    }
    public static boolean isValidCourseTitle(String courseTitle) {
        return courseTitle.matches("^.{1,20}$");
    }
    public static boolean isValidCourseTypeTitle(String courseTypeTitle) {
        return courseTypeService
                .selectAllCourseType()
                .stream()
                .map(CourseType::getTitle)
                .anyMatch((String str)->{
                    return Objects.equals(str, courseTypeTitle);
                });
    }
    public static boolean isValidTeacherNo(String teacherNo) {
        return teacherService.selectTeacherByNo(teacherNo) != null;
    }
    public static boolean isValidDeadline(LocalDateTime deadline) {
        return deadline.isAfter(LocalDateTime.now());
    }
    public static boolean isValidScore(String score) {
        return score.matches("^(100(\\.0{1,2})?|([0-9]{1,2}(\\.[0-9]{1,2})?|0(\\.[1-9][0-9])?))$");
    }
}