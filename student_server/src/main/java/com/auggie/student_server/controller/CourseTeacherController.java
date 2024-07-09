package com.auggie.student_server.controller;

import com.auggie.student_server.entity.Course;
import com.auggie.student_server.entity.CourseTeacher;
import com.auggie.student_server.entity.CourseTeacherInfo;
import com.auggie.student_server.service.CourseTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


// 标注为一个REST控制器，用于处理HTTP请求
@RestController
// 允许所有来源的跨域请求
@CrossOrigin("*")
// 将请求路径"/courseTeacher"映射到这个控制器
@RequestMapping("/courseTeacher")
public class CourseTeacherController {
    // 自动注入CourseTeacherService实例，用于处理业务逻辑
    @Autowired
    private CourseTeacherService courseTeacherService;
    /**
     * 插入新的课程教师关联
     * @param cid 课程ID
     * @param tid 教师ID
     * @param term 学期
     * @return 插入成功返回true，若已存在相同记录则返回false
     */
    @GetMapping("/insert/{cid}/{tid}/{term}")
    public boolean insert(@PathVariable Integer cid, @PathVariable Integer tid, @PathVariable String term) {
        // 检查是否已存在相同的课程教师记录，若存在则返回false
        if (courseTeacherService.findBySearch(cid, tid, term).size() != 0) {
            return false;
        }
        // 插入新的课程教师记录
        return courseTeacherService.insertCourseTeacher(cid, tid, term);
    }

    /**
     * 查找教师的课程
     * @param tid 教师ID
     * @param term 学期
     * @return 该教师在指定学期内的课程列表
     */
    @GetMapping("/findMyCourse/{tid}/{term}")
    public List<Course> findMyCourse(@PathVariable Integer tid, @PathVariable String term) {
        // 打印查询信息用于调试
        System.out.println("查询教师课程：" + tid + " " + term);
        // 查找并返回教师在指定学期内的课程
        return courseTeacherService.findMyCourse(tid, term);
    }

    /**
     * 根据条件查找课程教师信息
     * @param map 包含查询条件的键值对
     * @return 符合条件的课程教师信息列表
     */
    @PostMapping("/findCourseTeacherInfo")
    public List<CourseTeacherInfo> findCourseTeacherInfo(@RequestBody Map<String, String> map) {
        // 查找并返回符合条件的课程教师信息
        return courseTeacherService.findCourseTeacherInfo(map);
    }

    /**
     * 删除课程教师关联
     * @param courseTeacher 包含课程ID和教师ID的课程教师对象
     * @return 删除成功返回true，失败返回false
     */
    @PostMapping("/deleteById")
    public boolean deleteById(@RequestBody CourseTeacher courseTeacher) {
        // 删除指定的课程教师关联
        return courseTeacherService.deleteById(courseTeacher);
    }
}