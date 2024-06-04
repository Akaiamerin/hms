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
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class StudentController {
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
            value = "/student/index",
            method = RequestMethod.GET
    )
    public String index(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Student student = studentService.selectStudentByUserId(user.getId());
        List<CourseSelection> courseSelectionList = courseSelectionService.selectCourseSelectionByStudentId(student.getId());
        List<Homework> homeworkList = homeworkService.selectHomeworkByStudentId(student.getId());
        model.addAttribute("courseSelectionNum", courseSelectionList.size());
        model.addAttribute("homeworkNum", homeworkList.size());
        return "/student/index";
    }
    @RequestMapping(
            value = "/student/update-my-student",
            method = RequestMethod.POST
    )
    public String updateMyStudent(
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
            Student student = studentService.selectStudentById(id);
            User user = userService.selectUserById(student.getUserId());
            StudentVO studentVO = new StudentVO();
            studentVO.setId(student.getId());
            studentVO.setUsername(user.getUsername());
            studentVO.setNo(student.getNo());
            studentVO.setName(student.getName());
            model.addAttribute("studentVO", studentVO);
            return "/student/student/select-my-student";
        }
        Student student = studentService.selectStudentById(id);
        student.setName(name);
        studentService.updateStudentById(student);
        return "redirect:/student/select-my-student";
    }
    @RequestMapping(
            value = "/student/select-my-student",
            method = RequestMethod.GET
    )
    public String selectMyStudent(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Student student = studentService.selectStudentByUserId(user.getId());
        StudentVO studentVO = new StudentVO();
        BeanUtils.copyProperties(user, studentVO);
        BeanUtils.copyProperties(student, studentVO);
        model.addAttribute("studentVO", studentVO);
        return "/student/student/select-my-student";
    }
    @RequestMapping(
            value = "/student/insert-course",
            method = RequestMethod.GET
    )
    public String insertCourse() {
        return "/student/course/insert-course";
    }
    @RequestMapping(
            value = "/student/insert-course",
            method = RequestMethod.POST
    )
    public String insertCourse(
            @RequestParam("no") String no,
            HttpSession session,
            Model model
    ) {
        User user = (User) session.getAttribute("user");
        Student student = studentService.selectStudentByUserId(user.getId());
        Course course = courseService.selectCourseByNo(no);
        if (course == null) {
            model.addAttribute("noMessage", "课程编号错误");
        }
        else {
            Optional<CourseSelection> courseSelectionOptional = courseSelectionService
                    .selectCourseSelectionByCourseId(course.getId())
                    .stream()
                    .filter((CourseSelection courseSelection)->{
                        return Objects.equals(courseSelection.getStudentId(), student.getId()) == true;
                    })
                    .findFirst();
            if (courseSelectionOptional.isPresent() == true) {
                model.addAttribute("noMessage", "已加入课程");
            }
        }
        if (model.asMap().isEmpty() == false) {
            model.addAttribute("no", no);
            return "/student/course/insert-course";
        }
        CourseSelection courseSelection = new CourseSelection();
        courseSelection.setCourseId(course.getId());
        courseSelection.setTeacherId(course.getTeacherId());
        courseSelection.setStudentId(student.getId());
        courseSelection.setCreateTime(LocalDateTime.now());
        courseSelectionService.insertCourseSelection(courseSelection);
        return "redirect:/student/select-course";
    }
    @RequestMapping(
            value = "/student/select-course",
            method = RequestMethod.GET
    )
    public String selectCourse(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Student student = studentService.selectStudentByUserId(user.getId());
        List<CourseVO> courseVOList = courseSelectionService
                .selectCourseSelectionByStudentId(student.getId())
                .stream()
                .map((CourseSelection courseSelection)->{
                    Course course = courseService.selectCourseById(courseSelection.getCourseId());
                    CourseType courseType = courseTypeService.selectCourseTypeById(course.getCourseTypeId());
                    Teacher teacher = teacherService.selectTeacherById(courseSelection.getTeacherId());
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
        return "/student/course/select-course";
    }
    @RequestMapping(
            value = "/student/select-course-selection",
            method = RequestMethod.GET
    )
    public String selectCourseSelection(
            @RequestParam("id") Integer id,
            HttpSession session,
            Model model) {
        User user = (User) session.getAttribute("user");
        Student student = studentService.selectStudentByUserId(user.getId());
        Course course = courseService.selectCourseById(id);
        Optional<CourseSelectionVO> courseSelectionVOOptional = courseSelectionService
                .selectCourseSelectionByCourseId(course.getId())
                .stream()
                .filter((CourseSelection courseSelection)->{
                    return Objects.equals(courseSelection.getStudentId(), student.getId()) == true;
                })
                .map((CourseSelection courseSelection)->{
                    CourseVO courseVO = new CourseVO();
                    courseVO.setNo(course.getNo());
                    courseVO.setTitle(course.getTitle());
                    Teacher teacher = teacherService.selectTeacherById(course.getTeacherId());
                    TeacherVO teacherVO = new TeacherVO();
                    teacherVO.setNo(teacher.getNo());
                    teacherVO.setName(teacher.getName());
                    StudentVO studentVO = new StudentVO();
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
                .findFirst();
        CourseSelectionVO courseSelectionVO = courseSelectionVOOptional.get();
        model.addAttribute("courseSelectionVO", courseSelectionVO);
        return "/student/course/select-course-selection";
    }
    @RequestMapping(
            value = "/student/update-homework",
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
        return "/student/homework/update-homework";
    }
    @RequestMapping(
            value = "/student/update-homework",
            method = RequestMethod.POST
    )
    public String updateHomework(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "attachment", required = false) MultipartFile attachment,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes
    ) throws IOException {
        User user = (User) session.getAttribute("user");
        Student student = studentService.selectStudentByUserId(user.getId());
        Homework homework = homeworkService.selectHomeworkById(id);
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(homework.getDeadline()) == true) {
            model.addAttribute("message", "提交错误，超过截止时间");
            return updateHomework(id, model);
        }
        homework.setContent(content);
        homework.setHomeworkStatusId(2);
        if (attachment.isEmpty() == false) {
            String title = attachment.getOriginalFilename();
            String pathTitle = String.valueOf(new StringBuilder().append(student.getId()).append('-').append(now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))).append(title.substring(title.lastIndexOf('.'), title.length())));
            String savePath = System.getProperty("user.dir") + '\\' + pathTitle;
            attachment.transferTo(new File(savePath));
            HomeworkAttachment homeworkAttachment = new HomeworkAttachment();
            homeworkAttachment.setHomeworkId(id);
            homeworkAttachment.setTitle(title);
            homeworkAttachment.setAttachment(savePath);
            homeworkAttachment.setCreateTime(now);
            homeworkAttachmentService.insertHomeworkAttachment(homeworkAttachment);
        }
        homeworkService.updateHomeworkById(homework);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/student/update-homework";
    }
    @RequestMapping(
            value = "/student/select-homework",
            method = RequestMethod.GET
    )
    public String selectHomework(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Student student = studentService.selectStudentByUserId(user.getId());
        List<HomeworkVO> homeworkVOList = homeworkService
                .selectHomeworkByStudentId(student.getId())
                .stream()
                .map((Homework homework)->{
                    Course course = courseService.selectCourseById(homework.getCourseId());
                    Teacher teacher = teacherService.selectTeacherById(homework.getTeacherId());
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
        return "/student/homework/select-homework";
    }
}