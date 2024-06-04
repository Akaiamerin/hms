CREATE DATABASE IF NOT EXISTS hms;

USE hms;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    username VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码',
    role VARCHAR(20) NOT NULL COMMENT '角色',
    PRIMARY KEY (id)
) COMMENT '用户表';

TRUNCATE TABLE user;

DROP TABLE IF EXISTS admin;

CREATE TABLE admin (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    user_id INT NOT NULL COMMENT '用户 id',
    no VARCHAR(20) NOT NULL UNIQUE COMMENT '管理员编号',
    name VARCHAR(20) NOT NULL COMMENT '管理员姓名',
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
) COMMENT '管理员表';

TRUNCATE TABLE admin;

DROP TABLE IF EXISTS teacher;

CREATE TABLE teacher (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    user_id INT NOT NULL COMMENT '用户 id',
    no VARCHAR(20) NOT NULL UNIQUE COMMENT '教师编号',
    name VARCHAR(20) NOT NULL COMMENT '教师姓名',
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
) COMMENT '教师表';

TRUNCATE TABLE teacher;

DROP TABLE IF EXISTS student;

CREATE TABLE student (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    user_id INT NOT NULL COMMENT '用户 id',
    no VARCHAR(20) NOT NULL UNIQUE COMMENT '学生编号',
    name VARCHAR(20) NOT NULL COMMENT '学生姓名',
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
) COMMENT '学生表';

TRUNCATE TABLE student;

DROP TABLE IF EXISTS course_type;

CREATE TABLE course_type (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    title VARCHAR(20) NOT NULL UNIQUE COMMENT '课程类型名称',
    PRIMARY KEY (id)
) COMMENT '课程类型表';

TRUNCATE TABLE course_type;

DROP TABLE IF EXISTS course;

CREATE TABLE course (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    course_type_id INT NOT NULL COMMENT '课程类型 id',
    no VARCHAR(20) NOT NULL UNIQUE COMMENT '课程编号',
    title VARCHAR(20) NOT NULL COMMENT '课程名称',
    teacher_id INT NOT NULL COMMENT '教师 id',
    detail TEXT DEFAULT NULL COMMENT '课程详情',
    PRIMARY KEY (id),
    FOREIGN KEY (course_type_id) REFERENCES course_type (id),
    FOREIGN KEY (teacher_id) REFERENCES teacher (id)
) COMMENT '课程表';

TRUNCATE TABLE course;

DROP TABLE IF EXISTS course_selection;

CREATE TABLE course_selection (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    course_id INT NOT NULL COMMENT '课程 id',
    teacher_id INT NOT NULL COMMENT '教师 id',
    student_id INT NOT NULL COMMENT '学生 id',
    create_time DATETIME DEFAULT NOW() COMMENT '创建时间',
    PRIMARY KEY (id)
) COMMENT '选课表';

TRUNCATE TABLE course_selection;

DROP TABLE IF EXISTS homework_status;

CREATE TABLE homework_status (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    title VARCHAR(20) NOT NULL UNIQUE COMMENT '作业状态名称',
    PRIMARY KEY (id)
) COMMENT '作业状态表';

TRUNCATE TABLE homework_status;

DROP TABLE IF EXISTS homework;

CREATE TABLE homework (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    course_id INT NOT NULL COMMENT '课程 id',
    teacher_id INT NOT NULL COMMENT '教师 id',
    student_id INT NOT NULL COMMENT '学生 id',
    homework_status_id INT NOT NULL COMMENT '作业状态 id',
    content TEXT DEFAULT NULL COMMENT '内容',
    create_time DATETIME DEFAULT NOW() COMMENT '创建时间',
    update_time DATETIME DEFAULT NOW() ON UPDATE NOW() COMMENT '更新时间',
    deadline DATETIME DEFAULT NOW() COMMENT '截止时间',
    PRIMARY KEY (id),
    FOREIGN KEY (course_id) REFERENCES course (id),
    FOREIGN KEY (teacher_id) REFERENCES teacher (id),
    FOREIGN KEY (student_id) REFERENCES student (id),
    FOREIGN KEY (homework_status_id) REFERENCES homework_status (id)
) COMMENT '作业表';

TRUNCATE TABLE homework;

DROP TABLE IF EXISTS homework_attachment;

CREATE TABLE homework_attachment (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    homework_id INT NOT NULL COMMENT '作业 id',
    title VARCHAR(255) NOT NULL COMMENT '附件名称',
    attachment VARCHAR(255) NOT NULL UNIQUE COMMENT '附件地址',
    create_time DATETIME DEFAULT NOW() COMMENT '创建时间',
    PRIMARY KEY (id),
    FOREIGN KEY (homework_id) REFERENCES homework (id)
) COMMENT '作业附件表';

TRUNCATE TABLE homework_attachment;

DROP TABLE IF EXISTS homework_record;

CREATE TABLE homework_record (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    homework_id INT NOT NULL COMMENT '作业 id',
    score DOUBLE(4, 2) DEFAULT 0.00 COMMENT '分数',
    teacher_comment TEXT DEFAULT NULL COMMENT '教师评语',
    PRIMARY KEY (id),
    FOREIGN KEY (homework_id) REFERENCES homework (id)
) COMMENT '作业记录表';

TRUNCATE TABLE homework_record;

DROP TABLE IF EXISTS persistent_logins;

CREATE TABLE persistent_logins (
    username VARCHAR (64) NOT NULL,
    series VARCHAR (64) NOT NULL,
    token VARCHAR (64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);

TRUNCATE TABLE persistent_logins;

SET FOREIGN_KEY_CHECKS = 1;