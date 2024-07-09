package com.auggie.student_server.service;

import com.auggie.student_server.entity.Course;
import com.auggie.student_server.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// 标注为一个服务类，用于处理课程相关的业务逻辑
@Service
public class CourseService {

    // 自动注入CourseMapper实例，用于与数据库交互
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 根据搜索条件查找课程
     * @param map 包含搜索条件的键值对
     * @return 符合搜索条件的课程列表
     */
    public List<Course> findBySearch(Map<String, String> map) {
        Integer cid = null;
        Integer lowBound = null;
        Integer highBound = null;
        Integer fuzzy = 0; // 模糊查询标志，0表示不进行模糊查询
        String cname = null;

        // 如果map包含"cid"键，将其值转换为Integer类型
        if (map.containsKey("cid")) {
            try {
                cid = Integer.parseInt(map.get("cid"));
            } catch (Exception e) {
                // 解析失败，不处理
            }
        }

        // 如果map包含"lowBound"键，将其值转换为Integer类型
        if (map.containsKey("lowBound")) {
            try {
                lowBound = Integer.parseInt(map.get("lowBound"));
            } catch (Exception e) {
                // 解析失败，不处理
            }
        }

        // 如果map包含"highBound"键，将其值转换为Integer类型
        if (map.containsKey("highBound")) {
            try {
                highBound = Integer.valueOf(map.get("highBound"));
            } catch (Exception e) {
                // 解析失败，不处理
            }
        }

        // 如果map包含"cname"键，获取其值
        if (map.containsKey("cname")) {
            cname = map.get("cname");
        }

        // 如果map包含"fuzzy"键，将其值转换为Integer类型，1表示进行模糊查询
        if (map.containsKey("fuzzy")) {
            fuzzy = (map.get("fuzzy").equals("true")) ? 1 : 0;
        }

        // 打印查询条件用于调试
        System.out.println("查询课程 map：" + map);
        System.out.println(cid + " " + cname + " " + fuzzy + " " + lowBound + " " + highBound);

        // 调用mapper的方法进行数据库查询
        return courseMapper.findBySearch(cid, cname, fuzzy, lowBound, highBound);
    }

    /**
     * 根据课程ID查找课程
     * @param cid 课程ID
     * @return 符合条件的课程列表
     */
    public List<Course> findBySearch(Integer cid) {
        return courseMapper.findBySearch(cid, null, 0, null, null);
    }

    /**
     * 根据课程ID查找课程
     * @param cid 课程ID
     * @return 符合条件的课程列表
     */
    public List<Course> findById(Integer cid) {
        HashMap<String, String> map = new HashMap<>();
        if (cid != null)
            map.put("cid", String.valueOf(cid));
        return findBySearch(map);
    }

    /**
     * 根据课程ID更新课程信息
     * @param course 课程信息
     * @return 更新成功返回true，失败返回false
     */
    public boolean updateById(Course course) {
        return courseMapper.updateById(course);
    }

    /**
     * 插入新的课程信息
     * @param course 课程信息
     * @return 插入成功返回true，失败返回false
     */
    public boolean insertCourse(Course course) {
        return courseMapper.insertCourse(course);
    }

    /**
     * 根据课程ID删除课程
     * @param cid 课程ID
     * @return 删除成功返回true，失败返回false
     */
    public boolean deleteById(Integer cid) {
        return courseMapper.deleteById(cid);
    }
}