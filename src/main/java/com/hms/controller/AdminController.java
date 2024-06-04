package com.hms.controller;
import com.hms.entity.Admin;
import com.hms.entity.Course;
import com.hms.entity.CourseSelection;
import com.hms.entity.CourseType;
import com.hms.entity.Student;
import com.hms.entity.Teacher;
import com.hms.entity.User;
import com.hms.pojo.vo.AdminVO;
import com.hms.pojo.vo.CourseVO;
import com.hms.pojo.vo.StudentVO;
import com.hms.pojo.vo.TeacherVO;
import com.hms.service.AdminService;
import com.hms.service.CourseSelectionService;
import com.hms.service.CourseService;
import com.hms.service.CourseTypeService;
import com.hms.service.StudentService;
import com.hms.service.TeacherService;
import com.hms.service.UserService;
import com.hms.utils.ValidUtils;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class AdminController {
    @Resource
    private UserService userService;
    @Resource
    private AdminService adminService;
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
    private PasswordEncoder passwordEncoder;
    @RequestMapping(
            value = "/admin/index",
            method = RequestMethod.GET
    )
    public String index(Model model) {
        List<User> userList = userService.selectAllUser();
        List<Course> courseList = courseService.selectAllCourse();
        model.addAttribute("userNum", userList.size());
        model.addAttribute("courseNum", courseList.size());
        return "/admin/index";
    }
    @RequestMapping(
            value = "/admin/insert-admin",
            method = RequestMethod.GET
    )
    public String insertAdmin() {
        return "/admin/admin/insert-admin";
    }
    @RequestMapping(
            value = "/admin/insert-admin",
            method = RequestMethod.POST
    )
    public String insertAdmin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("no") String no,
            @RequestParam("name") String name,
            @RequestParam("role") String role,
            Model model
    ) {
        if (ValidUtils.isValidUsername(username) == false) {
            model.addAttribute("usernameMessage", "用户名必须包含数字或英文字母，长度为6~20个字符");
        }
        if (userService.selectUserByUsername(username) != null) {
            model.addAttribute("usernameMessage", "用户名已存在");
        }
        if (ValidUtils.isValidPassword(password) == false) {
            model.addAttribute("passwordMessage", "密码必须同时包含数字和英文字母，长度为6~20个字符");
        }
        if (ValidUtils.isValidNo(no) == false) {
            model.addAttribute("noMessage", "编号长度为1~20个字符");
        }
        if (adminService.selectAdminByNo(no) != null) {
            model.addAttribute("noMessage", "编号已存在");
        }
        if (ValidUtils.isValidName(name) == false) {
            model.addAttribute("nameMessage", "姓名长度为1~20个字符");
        }
        if (ValidUtils.isValidRole(role) == false) {
            model.addAttribute("roleMessage", "管理角色错误");
        }
        if (model.asMap().isEmpty() == false) {
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            model.addAttribute("no", no);
            model.addAttribute("name", name);
            return "/admin/admin/insert-admin";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userService.insertUser(user);
        user = userService.selectUserByUsername(username);
        Admin admin = new Admin();
        admin.setUserId(user.getId());
        admin.setNo(no);
        admin.setName(name);
        adminService.insertAdmin(admin);
        return "redirect:/admin/select-admin";
    }
    @RequestMapping(
            value = "/admin/delete-admin",
            method = RequestMethod.GET
    )
    public String deleteAdmin(
            @RequestParam("id") Integer id
    ) {
        Admin admin = adminService.selectAdminById(id);
        adminService.deleteAdminById(id);
        userService.deleteUserById(admin.getUserId());
        return "redirect:/admin/select-admin";
    }
    @RequestMapping(
            value = "/admin/update-admin",
            method = RequestMethod.GET
    )
    public String updateAdmin(
            @RequestParam("id") Integer id,
            Model model
    ) {
        Admin admin = adminService.selectAdminById(id);
        User user = userService.selectUserById(admin.getUserId());
        AdminVO adminVO = new AdminVO();
        adminVO.setId(admin.getId());
        adminVO.setUsername(user.getUsername());
        adminVO.setNo(admin.getNo());
        adminVO.setName(admin.getName());
        model.addAttribute("adminVO", adminVO);
        return "/admin/admin/update-admin";
    }
    @RequestMapping(
            value = "/admin/update-admin",
            method = RequestMethod.POST
    )
    public String updateAdmin(
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
            return updateAdmin(id, model);
        }
        Admin admin = adminService.selectAdminById(id);
        admin.setName(name);
        adminService.updateAdminById(admin);
        return "redirect:/admin/select-admin";
    }
    @RequestMapping(
            value = "/admin/select-admin",
            method = RequestMethod.GET
    )
    public String selectAdmin(Model model) {
        List<AdminVO> adminVOList = adminService
                .selectAllAdmin()
                .stream()
                .map((Admin admin)->{
                    User user = userService.selectUserById(admin.getUserId());
                    AdminVO adminVO = new AdminVO();
                    adminVO.setId(admin.getId());
                    adminVO.setUsername(user.getUsername());
                    adminVO.setNo(admin.getNo());
                    adminVO.setName(admin.getName());
                    return adminVO;
                })
                .collect(Collectors.toList());
        model.addAttribute("adminVOList", adminVOList);
        return "/admin/admin/select-admin";
    }
    @RequestMapping(
            value = "/admin/insert-teacher",
            method = RequestMethod.GET
    )
    public String insertTeacher() {
        return "/admin/teacher/insert-teacher";
    }
    @RequestMapping(
            value = "/admin/insert-teacher",
            method = RequestMethod.POST
    )
    public String insertTeacher(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("no") String no,
            @RequestParam("name") String name,
            @RequestParam("role") String role,
            Model model
    ) {
        if (ValidUtils.isValidUsername(username) == false) {
            model.addAttribute("usernameMessage", "用户名必须包含数字或英文字母，长度为6~20个字符");
        }
        if (userService.selectUserByUsername(username) != null) {
            model.addAttribute("usernameMessage", "用户名已存在");
        }
        if (ValidUtils.isValidPassword(password) == false) {
            model.addAttribute("passwordMessage", "密码必须同时包含数字和英文字母，长度为6~20个字符");
        }
        if (ValidUtils.isValidNo(no) == false) {
            model.addAttribute("noMessage", "编号长度为1~20个字符");
        }
        if (adminService.selectAdminByNo(no) != null) {
            model.addAttribute("noMessage", "编号已存在");
        }
        if (ValidUtils.isValidName(name) == false) {
            model.addAttribute("nameMessage", "姓名长度为1~20个字符");
        }
        if (ValidUtils.isValidRole(role) == false) {
            model.addAttribute("roleMessage", "管理角色错误");
        }
        if (model.asMap().isEmpty() == false) {
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            model.addAttribute("no", no);
            model.addAttribute("name", name);
            return "/admin/teacher/insert-teacher";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userService.insertUser(user);
        user = userService.selectUserByUsername(username);
        Teacher teacher = new Teacher();
        teacher.setUserId(user.getId());
        teacher.setNo(no);
        teacher.setName(name);
        teacherService.insertTeacher(teacher);
        return "redirect:/admin/select-teacher";
    }
    @RequestMapping(
            value = "/admin/delete-teacher",
            method = RequestMethod.GET
    )
    public String deleteTeacher(
            @RequestParam("id") Integer id
    ) {
        Teacher teacher = teacherService.selectTeacherById(id);
        teacherService.deleteTeacherById(id);
        userService.deleteUserById(teacher.getUserId());
        return "redirect:/admin/select-admin";
    }
    @RequestMapping(
            value = "/admin/update-teacher",
            method = RequestMethod.GET
    )
    public String updateTeacher(
            @RequestParam("id") Integer id,
            Model model
    ) {
        Teacher teacher = teacherService.selectTeacherById(id);
        User user = userService.selectUserById(teacher.getUserId());
        TeacherVO teacherVO = new TeacherVO();
        teacherVO.setId(teacher.getId());
        teacherVO.setUsername(user.getUsername());
        teacherVO.setNo(teacher.getNo());
        teacherVO.setName(teacher.getName());
        model.addAttribute("teacherVO", teacherVO);
        return "/admin/teacher/update-teacher";
    }
    @RequestMapping(
            value = "/admin/update-teacher",
            method = RequestMethod.POST
    )
    public String updateTeacher(
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
            return updateTeacher(id, model);
        }
        Teacher teacher = teacherService.selectTeacherById(id);
        teacher.setName(name);
        teacherService.updateTeacherById(teacher);
        return "redirect:/admin/select-teacher";
    }
    @RequestMapping(
            value = "/admin/select-teacher",
            method = RequestMethod.GET
    )
    public String selectTeacher(Model model) {
        List<TeacherVO> teacherVOList = teacherService
                .selectAllTeacher()
                .stream()
                .map((Teacher teacher)->{
                    User user = userService.selectUserById(teacher.getUserId());
                    TeacherVO teacherVO = new TeacherVO();
                    teacherVO.setId(teacher.getId());
                    teacherVO.setUsername(user.getUsername());
                    teacherVO.setNo(teacher.getNo());
                    teacherVO.setName(teacher.getName());
                    return teacherVO;
                })
                .collect(Collectors.toList());
        model.addAttribute("teacherVOList", teacherVOList);
        return "/admin/teacher/select-teacher";
    }
    @RequestMapping(
            value = "/admin/insert-student",
            method = RequestMethod.GET
    )
    public String insertStudent() {
        return "/admin/student/insert-student";
    }
    @RequestMapping(
            value = "/admin/insert-student",
            method = RequestMethod.POST
    )
    public String insertStudent(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("no") String no,
            @RequestParam("name") String name,
            @RequestParam("role") String role,
            Model model
    ) {
        if (ValidUtils.isValidUsername(username) == false) {
            model.addAttribute("usernameMessage", "用户名必须包含数字或英文字母，长度为6~20个字符");
        }
        if (userService.selectUserByUsername(username) != null) {
            model.addAttribute("usernameMessage", "用户名已存在");
        }
        if (ValidUtils.isValidPassword(password) == false) {
            model.addAttribute("passwordMessage", "密码必须同时包含数字和英文字母，长度为6~20个字符");
        }
        if (ValidUtils.isValidNo(no) == false) {
            model.addAttribute("noMessage", "编号长度为1~20个字符");
        }
        if (adminService.selectAdminByNo(no) != null) {
            model.addAttribute("noMessage", "编号已存在");
        }
        if (ValidUtils.isValidName(name) == false) {
            model.addAttribute("nameMessage", "姓名长度为1~20个字符");
        }
        if (ValidUtils.isValidRole(role) == false) {
            model.addAttribute("roleMessage", "管理角色错误");
        }
        if (model.asMap().isEmpty() == false) {
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            model.addAttribute("no", no);
            model.addAttribute("name", name);
            return "/admin/student/insert-student";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userService.insertUser(user);
        user = userService.selectUserByUsername(username);
        Student student = new Student();
        student.setUserId(user.getId());
        student.setNo(no);
        student.setName(name);
        studentService.insertStudent(student);
        return "redirect:/admin/select-student";
    }
    @RequestMapping(
            value = "/admin/delete-student",
            method = RequestMethod.GET
    )
    public String deleteStudent(
            @RequestParam("id") Integer id
    ) {
        Student student = studentService.selectStudentById(id);
        studentService.deleteStudentById(id);
        userService.deleteUserById(student.getUserId());
        return "redirect:/admin/select-student";
    }
    @RequestMapping(
            value = "/admin/update-student",
            method = RequestMethod.GET
    )
    public String updateStudent(
            @RequestParam("id") Integer id,
            Model model
    ) {
        Student student = studentService.selectStudentById(id);
        User user = userService.selectUserById(student.getUserId());
        StudentVO studentVO = new StudentVO();
        studentVO.setId(student.getId());
        studentVO.setUsername(user.getUsername());
        studentVO.setNo(student.getNo());
        studentVO.setName(student.getName());
        model.addAttribute("studentVO", studentVO);
        return "/admin/student/update-student";
    }
    @RequestMapping(
            value = "/admin/update-student",
            method = RequestMethod.POST
    )
    public String updateStudent(
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
            return updateStudent(id, model);
        }
        Student student = studentService.selectStudentById(id);
        student.setName(name);
        studentService.updateStudentById(student);
        return "redirect:/admin/select-student";
    }
    @RequestMapping(
            value = "/admin/select-student",
            method = RequestMethod.GET
    )
    public String selectStudent(Model model) {
        List<StudentVO> studentVOList = studentService
                .selectAllStudent()
                .stream()
                .map((Student student)->{
                    User user = userService.selectUserById(student.getUserId());
                    StudentVO studentVO = new StudentVO();
                    studentVO.setId(student.getId());
                    studentVO.setUsername(user.getUsername());
                    studentVO.setNo(student.getNo());
                    studentVO.setName(student.getName());
                    return studentVO;
                })
                .collect(Collectors.toList());
        model.addAttribute("studentVOList", studentVOList);
        return "/admin/student/select-student";
    }
    @RequestMapping(
            value = "/admin/insert-course",
            method = RequestMethod.GET
    )
    public String insertCourse(Model model) {
        List<CourseType> courseTypeList = courseTypeService.selectAllCourseType();
        List<Teacher> teacherList = teacherService.selectAllTeacher();
        model.addAttribute("courseTypeList", courseTypeList);
        model.addAttribute("teacherList", teacherList);
        return "/admin/course/insert-course";
    }
    @RequestMapping(
            value = "/admin/insert-course",
            method = RequestMethod.POST
    )
    public String insertCourse(
            @RequestParam("no") String no,
            @RequestParam("title") String title,
            @RequestParam("courseTypeTitle") String courseTypeTitle,
            @RequestParam("teacherNo") String teacherNo,
            @RequestParam("detail") String detail,
            Model model
    ) {
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
            List<Teacher> teacherList = teacherService.selectAllTeacher();
            model.addAttribute("courseTypeList", courseTypeList);
            model.addAttribute("teacherList", teacherList);
            model.addAttribute("no", no);
            model.addAttribute("title", title);
            model.addAttribute("courseTypeTitle", courseTypeTitle);
            model.addAttribute("teacherNo", teacherNo);
            model.addAttribute("detail", detail);
            return "/admin/course/insert-course";
        }
        CourseType courseType = courseTypeService.selectCourseTypeByTitle(courseTypeTitle);
        Teacher teacher = teacherService.selectTeacherByNo(teacherNo);
        Course course = new Course();
        course.setCourseTypeId(courseType.getId());
        course.setNo(no);
        course.setTitle(title);
        course.setTeacherId(teacher.getId());
        course.setDetail(detail);
        courseService.insertCourse(course);
        return "redirect:/admin/select-course";
    }
    @RequestMapping(
            value = "/admin/delete-course",
            method = RequestMethod.GET
    )
    public String deleteCourse(
            @RequestParam("id") Integer id
    ) {
        courseService.deleteCourseById(id);
        return "redirect:/admin/select-course";
    }
    @RequestMapping(
            value = "/admin/update-course",
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
        List<Teacher> teacherList = teacherService.selectAllTeacher();
        model.addAttribute("courseTypeList", courseTypeList);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("courseVO", courseVO);
        return "/admin/course/update-course";
    }
    @RequestMapping(
            value = "/admin/update-course",
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
        if (ValidUtils.isValidTeacherNo(teacherNo) == false) {
            model.addAttribute("teacherNoMessage", "教师编号错误");
        }
        if (model.asMap().isEmpty() == false) {
            return updateCourse(id, model);
        }
        CourseType courseType = courseTypeService.selectCourseTypeByTitle(courseTypeTitle);
        Teacher teacher = teacherService.selectTeacherByNo(teacherNo);
        Course course = courseService.selectCourseById(id);
        course.setTitle(title);
        course.setCourseTypeId(courseType.getId());
        course.setTeacherId(teacher.getId());
        course.setDetail(detail);
        courseService.updateCourseById(course);
        List<CourseSelection> courseSelectionList = courseSelectionService.selectCourseSelectionByCourseId(course.getId());
        for (int i = 0; i < courseSelectionList.size(); i++) {
            CourseSelection courseSelection = courseSelectionList.get(i);
            courseSelection.setTeacherId(teacher.getId());
            courseSelectionService.updateCourseSelectionById(courseSelection);
        }
        return "redirect:/admin/select-course";
    }
    @RequestMapping(
            value = "/admin/select-course",
            method = RequestMethod.GET
    )
    public String selectCourse(Model model) {
        List<CourseVO> courseVOList = courseService
                .selectAllCourse()
                .stream()
                .map((Course course)->{
                    CourseType courseType = courseTypeService.selectCourseTypeById(course.getCourseTypeId());
                    Teacher teacher = teacherService.selectTeacherById(course.getTeacherId());
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
        return "/admin/course/select-course";
    }
}