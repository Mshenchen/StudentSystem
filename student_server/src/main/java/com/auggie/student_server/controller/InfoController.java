package com.auggie.student_server.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// 标注这是一个控制器类，并将类中的方法的返回值直接作为HTTP响应体返回
@RestController
// 指定这个控制器的基础请求路径为 "/info"
@RequestMapping("/info")
// 允许所有来源的跨域请求访问此控制器
@CrossOrigin("*")
public class InfoController {
    // 定义一个常量来存储当前学期的名称
    private final String CURRENT_TERM = "22-春季学期";
    // 定义一个常量来指示是否禁止选课
    private final boolean FORBID_COURSE_SELECTION = false;

    // 定义一个方法来获取当前学期的名称
    // 将该方法映射到 "/getCurrentTerm" 路径的 HTTP 请求
    @RequestMapping("/getCurrentTerm")
    public String getCurrentTerm() {
        // 返回当前学期的名称
        return CURRENT_TERM;
    }

    // 定义一个方法来获取是否禁止选课的状态
    // 将该方法映射到 "/getForbidCourseSelection" 路径的 HTTP 请求
    @RequestMapping("/getForbidCourseSelection")
    public boolean getForbidCourseSelection() {
        // 返回是否禁止选课的状态
        return FORBID_COURSE_SELECTION;
    }
}
