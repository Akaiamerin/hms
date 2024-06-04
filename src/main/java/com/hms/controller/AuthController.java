package com.hms.controller;
import com.hms.entity.Admin;
import com.hms.entity.Student;
import com.hms.entity.Teacher;
import com.hms.entity.User;
import com.hms.service.AdminService;
import com.hms.service.StudentService;
import com.hms.service.TeacherService;
import com.hms.service.UserService;
import jakarta.annotation.Resource;
import java.util.Objects;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class AuthController {
    @Resource
    private UserService userService;
    @Resource
    private AdminService adminService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public String index() {
        return "/index";
    }
    @RequestMapping(
            value = "/auth/login",
            method = RequestMethod.GET
    )
    public String login() {
        return "/login";
    }
    @RequestMapping(
            value = "/auth/register",
            method = RequestMethod.GET
    )
    public String register() {
        return "/register";
    }
    @RequestMapping(
            value = "/auth/register",
            method = RequestMethod.POST
    )
    public String register(
            @RequestParam("role") String role,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("no") String no,
            @RequestParam("name") String name,
            Model model
    ) {
        if (userService.selectUserByUsername(username) != null) {
            model.addAttribute("role", role);
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            model.addAttribute("no", no);
            model.addAttribute("name", name);
            model.addAttribute("message", "用户名已存在");
            return "/register";
        }
        if (Objects.equals(role, "admin") == true) {
            Admin admin = adminService.selectAdminByNo(no);
            if (admin == null || admin.getUserId() != 1 || Objects.equals(name, admin.getName()) == false) {
                model.addAttribute("role", role);
                model.addAttribute("username", username);
                model.addAttribute("password", password);
                model.addAttribute("no", no);
                model.addAttribute("name", name);
                model.addAttribute("message", "编号或姓名错误");
                return "/register";
            }
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);
            userService.insertUser(user);
            user = userService.selectUserByUsername(username);
            admin.setUserId(user.getId());
            adminService.updateAdminById(admin);
        }
        else if (Objects.equals(role, "teacher") == true) {
            Teacher teacher = teacherService.selectTeacherByNo(no);
            if (teacher == null || teacher.getUserId() != -1 || Objects.equals(name, teacher.getName()) == false) {
                model.addAttribute("role", role);
                model.addAttribute("username", username);
                model.addAttribute("password", password);
                model.addAttribute("no", no);
                model.addAttribute("name", name);
                model.addAttribute("message", "编号或姓名错误");
                return "/register";
            }
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);
            userService.insertUser(user);
            user = userService.selectUserByUsername(username);
            teacher.setUserId(user.getId());
            teacherService.updateTeacherById(teacher);
        }
        else if (Objects.equals(role, "student") == true) {
            Student student = studentService.selectStudentByNo(no);
            if (student == null || student.getUserId() != -1 || Objects.equals(name, student.getName()) == false) {
                model.addAttribute("role", role);
                model.addAttribute("username", username);
                model.addAttribute("password", password);
                model.addAttribute("no", no);
                model.addAttribute("name", name);
                model.addAttribute("message", "编号或姓名错误");
                return "/register";
            }
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);
            userService.insertUser(user);
            user = userService.selectUserByUsername(username);
            student.setUserId(user.getId());
            studentService.updateStudentById(student);
        }
        return "redirect:/auth/login";
    }
    @RequestMapping(
            value = "/auth/forget",
            method = RequestMethod.GET
    )
    public String forget() {
        return "/forget";
    }
    @RequestMapping(
            value = "/auth/forget",
            method = RequestMethod.POST
    )
    public String forget(
            @RequestParam("role") String role,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("no") String no,
            @RequestParam("name") String name,
            Model model
    ) {
        User user = userService.selectUserByUsername(username);
        if (user == null) {
            model.addAttribute("role", role);
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            model.addAttribute("no", no);
            model.addAttribute("name", name);
            model.addAttribute("message", "用户名不存在");
            return "/forget";
        }
        if (Objects.equals(role, "admin") == true) {
            Admin admin = adminService.selectAdminByNo(no);
            if (admin == null || Objects.equals(admin.getUserId(), user.getId()) == false || Objects.equals(name, admin.getName()) == false) {
                model.addAttribute("role", role);
                model.addAttribute("username", username);
                model.addAttribute("password", password);
                model.addAttribute("no", no);
                model.addAttribute("name", name);
                model.addAttribute("message", "用户名或编号或姓名错误");
                return "/forget";
            }
        }
        else if (Objects.equals(role, "teacher") == true) {
            Teacher teacher = teacherService.selectTeacherByNo(no);
            if (teacher == null || Objects.equals(teacher.getUserId(), user.getId()) == false || Objects.equals(name, teacher.getName()) == false) {
                model.addAttribute("role", role);
                model.addAttribute("username", username);
                model.addAttribute("password", password);
                model.addAttribute("no", no);
                model.addAttribute("name", name);
                model.addAttribute("message", "用户名或编号或姓名错误");
                return "/forget";
            }
        }
        else if (Objects.equals(role, "student") == true) {
            Student student = studentService.selectStudentByNo(no);
            if (student == null || Objects.equals(student.getUserId(), user.getId()) == false || Objects.equals(name, student.getName()) == false) {
                model.addAttribute("role", role);
                model.addAttribute("username", username);
                model.addAttribute("password", password);
                model.addAttribute("no", no);
                model.addAttribute("name", name);
                model.addAttribute("message", "用户名或编号或姓名错误");
                return "/forget";
            }
        }
        user.setPassword(passwordEncoder.encode(password));
        userService.updateUserById(user);
        return "redirect:/auth/login";
    }
}