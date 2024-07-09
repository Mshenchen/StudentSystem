package com.auggie.student_server.controller;

import com.auggie.student_server.entity.Course;
import com.auggie.student_server.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// 标注为一个REST控制器，用于处理HTTP请求
@RestController
// 允许所有来源的跨域请求
@CrossOrigin("*")
// 将请求路径"/course"映射到这个控制器
@RequestMapping("/course")
public class CourseController {
    // 自动注入CourseService实例，用于处理业务逻辑
    @Autowired
    private CourseService courseService;
    /**
     * 根据搜索条件查找课程
     * @param map 包含搜索条件的键值对
     * @return 符合搜索条件的课程列表
     */
    @PostMapping("/findBySearch")
    public List<Course> findBySearch(@RequestBody Map<String, String> map) {
        return courseService.findBySearch(map);
    }

    /**
     * 根据课程ID查找课程
     * @param cid 课程ID
     * @return 符合条件的课程列表
     */
    @GetMapping("/findById/{cid}")
    public List<Course> findById(@PathVariable Integer cid) {
        return courseService.findBySearch(cid);
    }
    /**
     * 保存新的课程
     * @param course 课程信息
     * @return 保存成功返回true，失败返回false
     */
    @PostMapping("/save")
    public boolean save(@RequestBody Course course) {
        System.out.println(course);
        return courseService.insertCourse(course);
    }
    /**
     * 根据课程ID删除课程
     * @param cid 课程ID
     * @return 删除成功返回true，失败返回false
     */
    @GetMapping("/deleteById/{cid}")
    public boolean deleteById(@PathVariable Integer cid) {
        System.out.println("正在删除课程 cid: " + cid);
        return courseService.deleteById(cid);
    }
    /**
     * 更新课程信息
     * @param course 课程信息
     * @return 更新成功返回true，失败返回false
     */
    @PostMapping("/updateCourse")
    public boolean updateCourse(@RequestBody Course course) {
        System.out.println("正在修改课程: " + course);
        return courseService.updateById(course);
    }

}
