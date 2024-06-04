package com.hms.controller;
import com.hms.entity.Course;
import com.hms.entity.CourseSelection;
import com.hms.entity.CourseType;
import com.hms.entity.Homework;
import com.hms.entity.HomeworkAttachment;
import com.hms.entity.HomeworkRecord;
import com.hms.entity.HomeworkStatus;
import com.hms.entity.Student;
import com.hms.entity.Teacher;
import com.hms.entity.User;
import com.hms.pojo.vo.CourseSelectionVO;
import com.hms.pojo.vo.CourseVO;
import com.hms.pojo.vo.HomeworkRecordVO;
import com.hms.pojo.vo.HomeworkVO;
import com.hms.pojo.vo.StudentVO;
import com.hms.pojo.vo.TeacherVO;
import com.hms.service.CourseSelectionService;
import com.hms.service.CourseService;
import com.hms.service.CourseTypeService;
import com.hms.service.HomeworkAttachmentService;
import com.hms.service.HomeworkRecordService;
import com.hms.service.HomeworkService;
import com.hms.service.HomeworkStatusService;
import com.hms.service.StudentService;
import com.hms.service.TeacherService;
import com.hms.service.UserService;
import com.hms.utils.ValidUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class TeacherController {
    @Resource
    private UserService userService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;
    @Resource
    private CourseTypeService courseTypeService;
    @Resource
    private CourseService courseService;
    @Resource
    private CourseSelectionService courseSelectionService;
    @Resource
    private HomeworkService homeworkService;
    @Resource
    private HomeworkStatusService homeworkStatusService;
    @Resource
    private HomeworkAttachmentService homeworkAttachmentService;
    @Resource
    private HomeworkRecordService homeworkRecordService;
    @RequestMapping(
            value = "/teacher/index",
            method = RequestMethod.GET
    )
    public String index(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.selectTeacherByUserId(user.getId());
        List<CourseSelection> courseSelectionList = courseSelectionService.selectCourseSelectionByTeacherId(teacher.getId());
        List<Course> courseList = courseService.selectCourseByTeacherId(teacher.getId());
        List<Homework> homeworkList = homeworkService.selectHomeworkByTeacherId(teacher.getId());
        model.addAttribute("studentNum", courseSelectionList.size());
        model.addAttribute("courseNum", courseList.size());
        model.addAttribute("homeworkNum", homeworkList.size());
        return "/teacher/index";
    }
    @RequestMapping(
            value = "/teacher/update-my-teacher",
            method = RequestMethod.POST
    )
    public String updateMyTeacher(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "no", required = false) String no,
            @RequestParam(value = "name", required = false) String name,
            Model model
    ) {
        if (ValidUtils.isValidName(name) == false) {
            model.addAttribute("nameMessage", "姓名长度为1~20个字符");
        }
        if (model.asMap().isEmpty() == false) {
            Teacher teacher = teacherService.selectTeacherById(id);
            User user = userService.selectUserById(teacher.getUserId());
            TeacherVO teacherVO = new TeacherVO();
            teacherVO.setId(teacher.getId());
            teacherVO.setUsername(user.getUsername());
            teacherVO.setNo(teacher.getNo());
            teacherVO.setName(teacher.getName());
            model.addAttribute("teacherVO", teacherVO);
            return "/teacher/teacher/select-my-teacher";
        }
        Teacher teacher = teacherService.selectTeacherById(id);
        teacher.setName(name);
        teacherService.updateTeacherById(teacher);
        return "redirect:/teacher/select-my-teacher";
    }
    @RequestMapping(
            value = "/teacher/select-my-teacher",
            method = RequestMethod.GET
    )
    public String selectMyTeacher(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.selectTeacherByUserId(user.getId());
        TeacherVO teacherVO = new TeacherVO();
        BeanUtils.copyProperties(user, teacherVO);
        BeanUtils.copyProperties(teacher, teacherVO);
        model.addAttribute("teacherVO", teacherVO);
        return "/teacher/teacher/select-my-teacher";
    }
    @RequestMapping(
            value = "/teacher/insert-course",
            method = RequestMethod.GET
    )
    public String insertCourse(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.selectTeacherByUserId(user.getId());
        List<CourseType> courseTypeList = courseTypeService.selectAllCourseType();
        List<Teacher> teacherList = new ArrayList<>(Arrays.asList(teacher));
        model.addAttribute("courseTypeList", courseTypeList);
        model.addAttribute("teacherList", teacherList);
        return "/teacher/course/insert-course";
    }
    @RequestMapping(
            value = "/teacher/insert-course",
            method = RequestMethod.POST
    )
    public String insertCourse(
            @RequestParam("no") String no,
            @RequestParam("title") String title,
            @RequestParam("courseTypeTitle") String courseTypeTitle,
            @RequestParam("teacherNo") String teacherNo,
            @RequestParam("detail") String detail,
            HttpSession session,
            Model model
    ) {
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.selectTeacherByUserId(user.getId());
        if (ValidUtils.isValidCourseNo(no) == false) {
            model.addAttribute("noMessage", "课程编号必须同时包含数字和英文字母，长度为6个字符");
        }
        if (ValidUtils.isValidCourseTitle(title) == false) {
            model.addAttribute("titleMessage", "课程名称长度为1~20个字符");
        }
        if (ValidUtils.isValidCourseTypeTitle(courseTypeTitle) == false) {
            model.addAttribute("courseTypeTitleMessage", "课程类型错误");
        }
        if (ValidUtils.isValidTeacherNo(teacherNo) == false) {
            model.addAttribute("teacherNoMessage", "教师编号错误");
        }
        if (model.asMap().isEmpty() == false) {
            List<CourseType> courseTypeList = courseTypeService.selectAllCourseType();
            List<Teacher> teacherList = new ArrayList<>(Arrays.asList(teacher));
            model.addAttribute("courseTypeList", courseTypeList);
            model.addAttribute("teacherList", teacherList);
            model.addAttribute("no", no);
            model.addAttribute("title", title);
            model.addAttribute("courseTypeTitle", courseTypeTitle);
            model.addAttribute("teacherNo", teacherNo);
            model.addAttribute("detail", detail);
            return "/teacher/course/insert-course";
        }
        CourseType courseType = courseTypeService.selectCourseTypeByTitle(courseTypeTitle);
        Course course = new Course();
        course.setCourseTypeId(courseType.getId());
        course.setNo(no);
        course.setTitle(title);
        course.setTeacherId(teacher.getId());
        course.setDetail(detail);
        courseService.insertCourse(course);
        return "redirect:/teacher/select-course";
    }
    @RequestMapping(
            value = "/teacher/delete-course",
            method = RequestMethod.GET
    )
    public String deleteCourse(
            @RequestParam("id") Integer id
    ) {
        courseService.deleteCourseById(id);
        return "redirect:/teacher/select-course";
    }
    @RequestMapping(
            value = "/teacher/update-course",
            method = RequestMethod.GET
    )
    public String updateCourse(
            @RequestParam("id") Integer id,
            Model model
    ) {
        Course course = courseService.selectCourseById(id);
        CourseType courseType = courseTypeService.selectCourseTypeById(course.getCourseTypeId());
        Teacher teacher = teacherService.selectTeacherById(course.getTeacherId());
        CourseVO courseVO = new CourseVO();
        courseVO.setId(course.getId());
        courseVO.setNo(course.getNo());
        courseVO.setTitle(course.getTitle());
        courseVO.setCourseTypeTitle(courseType.getTitle());
        courseVO.setTeacherName(teacher.getName());
        courseVO.setDetail(course.getDetail());
        List<CourseType> courseTypeList = courseTypeService.selectAllCourseType();
        List<Teacher> teacherList = new ArrayList<>(Arrays.asList(teacher));
        model.addAttribute("courseTypeList", courseTypeList);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("courseVO", courseVO);
        return "/teacher/course/update-course";
    }
    @RequestMapping(
            value = "/teacher/update-course",
            method = RequestMethod.POST
    )
    public String updateCourse(
            @RequestParam("id") Integer id,
            @RequestParam(value = "no", required = false) String no,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "courseTypeTitle", required = false) String courseTypeTitle,
            @RequestParam(value = "teacherNo", required = false) String teacherNo,
            @RequestParam(value = "detail", required = false) String detail,
            Model model
    ) {
        if (ValidUtils.isValidCourseTitle(title) == false) {
            model.addAttribute("titleMessage", "课程名称长度为1~20个字符");
        }
        if (ValidUtils.isValidCourseTypeTitle(courseTypeTitle) == false) {
            model.addAttribute("courseTypeTitleMessage", "课程类型错误");
        }
        if (model.asMap().isEmpty() == false) {
            return updateCourse(id, model);
        }
        CourseType courseType = courseTypeService.selectCourseTypeByTitle(courseTypeTitle);
        Course course = courseService.selectCourseById(id);
        course.setTitle(title);
        course.setCourseTypeId(courseType.getId());
        course.setDetail(detail);
        courseService.updateCourseById(course);
        return "redirect:/teacher/select-course";
    }
    @RequestMapping(
            value = "/teacher/select-course",
            method = RequestMethod.GET
    )
    public String selectCourse(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.selectTeacherByUserId(user.getId());
        List<CourseVO> courseVOList = courseService
                .selectCourseByTeacherId(teacher.getId())
                .stream()
                .map((Course course)->{
                    CourseType courseType = courseTypeService.selectCourseTypeById(course.getCourseTypeId());
                    CourseVO courseVO = new CourseVO();
                    courseVO.setId(course.getId());
                    courseVO.setNo(course.getNo());
                    courseVO.setTitle(course.getTitle());
                    courseVO.setCourseTypeTitle(courseType.getTitle());
                    courseVO.setTeacherName(teacher.getName());
                    courseVO.setDetail(course.getDetail());
                    return courseVO;
                })
                .collect(Collectors.toList());
        model.addAttribute("courseVOList", courseVOList);
        return "/teacher/course/select-course";
    }
    @RequestMapping(
            value = "/teacher/delete-course-selection",
            method = RequestMethod.GET
    )
    public String deleteCourseSelection(
            @RequestParam("id") Integer id,
            RedirectAttributes redirectAttributes
    ) {
        CourseSelection courseSelection = courseSelectionService.selectCourseSelectionById(id);
        courseSelectionService.deleteCourseSelectionById(id);
        redirectAttributes.addAttribute("id", courseSelection.getCourseId());
        return "redirect:/teacher/select-course-selection";
    }
    @RequestMapping(
            value = "/teacher/select-course-selection",
            method = RequestMethod.GET
    )
    public String selectCourseSelection(
            @RequestParam("id") Integer id,
            HttpSession session,
            Model model) {
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.selectTeacherByUserId(user.getId());
        Course course = courseService.selectCourseById(id);
        List<CourseSelectionVO> courseSelectionVOList = courseSelectionService
                .selectAllCourseSelection()
                .stream()
                .filter((CourseSelection courseSelection)->{
                    return Objects.equals(courseSelection.getCourseId(), course.getId()) && Objects.equals(courseSelection.getTeacherId(), teacher.getId());
                })
                .map((CourseSelection courseSelection)->{
                    CourseVO courseVO = new CourseVO();
                    courseVO.setNo(course.getNo());
                    courseVO.setTitle(course.getTitle());
                    TeacherVO teacherVO = new TeacherVO();
                    teacherVO.setNo(teacher.getNo());
                    teacherVO.setName(teacher.getName());
                    StudentVO studentVO = new StudentVO();
                    Student student = studentService.selectStudentById(courseSelection.getStudentId());
                    studentVO.setNo(student.getNo());
                    studentVO.setName(student.getName());
                    CourseSelectionVO courseSelectionVO = new CourseSelectionVO();
                    courseSelectionVO.setId(courseSelection.getId());
                    courseSelectionVO.setCourseVO(courseVO);
                    courseSelectionVO.setTeacherVO(teacherVO);
                    courseSelectionVO.setStudentVO(studentVO);
                    courseSelectionVO.setCreateTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(courseSelection.getCreateTime()));
                    return courseSelectionVO;
                })
                .collect(Collectors.toList());
        model.addAttribute("courseSelectionVOList", courseSelectionVOList);
        return "/teacher/course/select-course-selection";
    }
    @RequestMapping(
            value = "/teacher/insert-homework",
            method = RequestMethod.GET
    )
    public String insertHomework(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.selectTeacherByUserId(user.getId());
        List<Course> courseList = courseService.selectCourseByTeacherId(teacher.getId());
        List<Teacher> teacherList = new ArrayList<>(Arrays.asList(teacher));
        model.addAttribute("courseList", courseList);
        model.addAttribute("teacherList", teacherList);
        return "/teacher/homework/insert-homework";
    }
    @RequestMapping(
            value = "/teacher/insert-homework",
            method = RequestMethod.POST
    )
    public String insertHomework(
            @RequestParam("courseNo") String courseNo,
            @RequestParam("teacherNo") String teacherNo,
            @RequestParam("deadline") LocalDateTime deadline,
            HttpSession session,
            Model model
    ) {
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.selectTeacherByUserId(user.getId());
        if (ValidUtils.isValidCourseNo(courseNo) == false) {
            model.addAttribute("courseNoMessage", "课程编号必须同时包含数字和英文字母，长度为6个字符");
        }
        if (ValidUtils.isValidTeacherNo(teacherNo) == false) {
            model.addAttribute("teacherNoMessage", "教师编号错误");
        }
        if (ValidUtils.isValidDeadline(deadline) == false) {
            model.addAttribute("deadlineMessage", "截止时间错误");
        }
        if (model.asMap().isEmpty() == false) {
            List<Course> courseList = courseService.selectCourseByTeacherId(teacher.getId());
            List<Teacher> teacherList = new ArrayList<>(Arrays.asList(teacher));
            model.addAttribute("courseList", courseList);
            model.addAttribute("teacherList", teacherList);
            model.addAttribute("deadline", deadline);
            return "/teacher/homework/insert-homework";
        }
        Course course = courseService.selectCourseByNo(courseNo);
        List<CourseSelection> courseSelectionList = courseSelectionService
                .selectAllCourseSelection()
                .stream()
                .filter((CourseSelection courseSelection)->{
                    return Objects.equals(courseSelection.getCourseId(), course.getId()) && Objects.equals(courseSelection.getTeacherId(), teacher.getId());
                })
                .collect(Collectors.toList());
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < courseSelectionList.size(); i++) {
            CourseSelection courseSelection = courseSelectionList.get(i);
            Homework homework = new Homework();
            homework.setCourseId(courseSelection.getCourseId());
            homework.setTeacherId(courseSelection.getTeacherId());
            homework.setStudentId(courseSelection.getStudentId());
            homework.setHomeworkStatusId(1);
            homework.setCreateTime(now);
            homework.setUpdateTime(now);
            homework.setDeadline(deadline);
            homeworkService.insertHomework(homework);
        }
        return "redirect:/teacher/select-homework";
    }
    @RequestMapping(
            value = "/teacher/delete-homework",
            method = RequestMethod.GET
    )
    public String deleteHomework(
            @RequestParam("id") Integer id
    ) {
        homeworkService.deleteHomeworkById(id);
        return "redirect:/teacher/select-homework";
    }
    @RequestMapping(
            value = "/teacher/update-homework",
            method = RequestMethod.GET
    )
    public String updateHomework(
            @RequestParam("id") Integer id,
            Model model
    ) {
        Homework homework = homeworkService.selectHomeworkById(id);
        Course course = courseService.selectCourseById(homework.getCourseId());
        Teacher teacher = teacherService.selectTeacherById(homework.getTeacherId());
        HomeworkStatus homeworkStatus = homeworkStatusService.selectHomeworkStatusById(homework.getHomeworkStatusId());
        HomeworkAttachment homeworkAttachment = Optional.ofNullable(homeworkAttachmentService.selectHomeworkAttachmentByHomeworkId(id)).orElse(new HomeworkAttachment());
        HomeworkVO homeworkVO = new HomeworkVO();
        homeworkVO.setId(homework.getId());
        homeworkVO.setCourseTitle(course.getTitle());
        homeworkVO.setTeacherName(teacher.getName());
        homeworkVO.setStatus(homeworkStatus.getTitle());
        homeworkVO.setContent(homework.getContent());
        homeworkVO.setAttachment(homeworkAttachment.getTitle());
        homeworkVO.setDeadline(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(homework.getDeadline()));
        HomeworkRecord homeworkRecord = Optional.ofNullable(homeworkRecordService.selectHomeworkRecordByHomeworkId(id)).orElse(new HomeworkRecord());
        HomeworkRecordVO homeworkRecordVO = new HomeworkRecordVO();
        BeanUtils.copyProperties(homeworkRecord, homeworkRecordVO);
        model.addAttribute("homeworkVO", homeworkVO);
        model.addAttribute("homeworkRecordVO", homeworkRecordVO);
        return "/teacher/homework/update-homework";
    }
    @RequestMapping(
            value = "/teacher/update-homework",
            method = RequestMethod.POST
    )
    public String updateHomework(
            @RequestParam("id") Integer id,
            @RequestParam(value = "score") String score,
            @RequestParam(value = "teacherComment", required = false) String teacherComment,
            Model model
    ) {
        if (ValidUtils.isValidScore(score) == false) {
            model.addAttribute("scoreMessage", "分数必须在0~100之间，至多保留2位小数");
        }
        if (model.asMap().isEmpty() == false) {
            return updateHomework(id, model);
        }
        HomeworkRecord homeworkRecord = homeworkRecordService.selectHomeworkRecordByHomeworkId(id);
        if (homeworkRecord == null) {
            homeworkRecord = new HomeworkRecord();
            homeworkRecord.setHomeworkId(id);
            homeworkRecord.setScore(Double.valueOf(score));
            homeworkRecord.setTeacherComment(teacherComment);
            homeworkRecordService.insertHomeworkRecord(homeworkRecord);
        }
        else {
            homeworkRecord.setScore(Double.valueOf(score));
            homeworkRecord.setTeacherComment(teacherComment);
            homeworkRecordService.updateHomeworkRecordById(homeworkRecord);
        }
        Homework homework = homeworkService.selectHomeworkById(id);
        homework.setHomeworkStatusId(3);
        homeworkService.updateHomeworkById(homework);
        return "redirect:/teacher/select-homework";
    }
    @RequestMapping(
            value = "/teacher/select-homework",
            method = RequestMethod.GET
    )
    public String selectHomework(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.selectTeacherByUserId(user.getId());
        List<HomeworkVO> homeworkVOList = homeworkService
                .selectHomeworkByTeacherId(teacher.getId())
                .stream()
                .map((Homework homework)->{
                    Course course = courseService.selectCourseById(homework.getCourseId());
                    HomeworkStatus homeworkStatus = homeworkStatusService.selectHomeworkStatusById(homework.getHomeworkStatusId());
                    HomeworkVO homeworkVO = new HomeworkVO();
                    homeworkVO.setId(homework.getId());
                    homeworkVO.setCourseTitle(course.getTitle());
                    homeworkVO.setTeacherName(teacher.getName());
                    homeworkVO.setStatus(homeworkStatus.getTitle());
                    homeworkVO.setDeadline(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(homework.getDeadline()));
                    return homeworkVO;
                })
                .collect(Collectors.toList());
        model.addAttribute("homeworkVOList", homeworkVOList);
        return "/teacher/homework/select-homework";
    }
}