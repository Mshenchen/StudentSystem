/*import Vue from 'vue';
import VueRouter from 'vue-router';
import login from '../views/login/index';
import student from "@/views/Student/index";
import studentHome from "@/views/Student/home";
import studentSelectCourseManage from "@/views/Student/selectCourse/index";
import selectCourse from "@/views/Student/selectCourse/selectCourse";
import querySelectedCourse from "@/views/Student/selectCourse/querySelectedCourse";
import studentCourseGrade from "@/views/Student/courseGrade/index";
import queryCourseGrade from "@/views/Student/courseGrade/queryCourseGrade";
import updateInfo from "@/components/updateInfo";*/

import Vue from 'vue';
import VueRouter from 'vue-router';
import login from '../views/login/index';
import teacher from "@/views/Teacher/index";
import student from "@/views/Student/index";
import offerCourse from "@/views/Teacher/offerCourse";
import teacherHome from "@/views/Teacher/home";
import setCourse from "@/views/Teacher/setCourse";
import studentHome from "@/views/Student/home";
import myOfferCourse from "@/views/Teacher/myOfferCourse";
import studentSelectCourseManage from "@/views/Student/selectCourse/index";
import selectCourse from "@/views/Student/selectCourse/selectCourse";
import querySelectedCourse from "@/views/Student/selectCourse/querySelectedCourse";
import studentCourseGrade from "@/views/Student/courseGrade/index";
import queryCourseGrade from "@/views/Student/courseGrade/queryCourseGrade";
import teacherGradeCourseManage from "@/views/Teacher/teacherGradeCourseManage/index";
import teacherQueryGradeCourse from "@/views/Teacher/teacherGradeCourseManage/teacherQueryGradeCourse";
import teacherEditorGradeCourse from "@/views/Teacher/teacherGradeCourseManage/teacherEditorGradeCourse";
import updateInfo from "@/components/updateInfo";


Vue.use(VueRouter)

const routes = [
  {
    // 随便定义的首页
    path: '/',
    name: 'index',
    component: login,
    redirect: '/login'
  },
  {
    // 登陆页
    path: '/login',
    name: 'login',
    component: login
  },
  {
    path: '/teacher',
    name: 'teacher',
    component: teacher,
    redirect: '/teacherHome',
    meta: {requireAuth: true},
    children: [
      {
        path: '/teacherHome',
        name: 'Hi! teacher',
        meta: {requireAuth: true},
        component: teacherHome,
        children: [
          {
            path: '/teacherHome',
            name: '教师主页',
            meta: {requireAuth: true},
            component: teacherHome
          },
        ]
      },
      {
        path: '/updateInfo',
        name: '教师编辑',
        component: updateInfo,
        meta: {requireAuth: true},
        children: [
          {
            path: '/updateInfoHome',
            name: '编辑教师信息',
            component: updateInfo,
            meta: {requireAuth: true}
          }
        ]
      },
      {
        path: '/courseManage',
        name: '课程设置',
        meta: {requireAuth: true},
        component: setCourse,
        children: [
          {
            path: '/myOfferCourse',
            name: '我开设的课程',
            component: myOfferCourse,
            meta: {requireAuth: true}
          },
          {
            path: '/offerCourse',
            name: '开设课程',
            component: offerCourse,
            meta: {requireAuth: true}
          },
        ]
      },
      {
        name: '教师成绩管理',
        path: '/teacherQueryGradeCourseManage',
        component: teacherGradeCourseManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/teacherQueryGradeCourseManage',
            name: '成绩管理',
            component: teacherQueryGradeCourse,
            meta: {requireAuth: true}
          },
          {
            path: '/teacherEditorGradeCourse',
            name: '编辑成绩',
            component: teacherEditorGradeCourse,
            meta: {requireAuth: true}
          }
        ]
      }
    ]
  },
  {
    path: '/student',
    name: 'student',
    component: student,
    redirect: '/studentHome',
    meta: {requireAuth: true},
    children: [
      {
        path: '/student',
        name: 'hi! student',
        component: studentHome,
        // 只有经过身份验证的用户才能访问
        meta: {requireAuth: true},
        children: [
          {
            path: '/studentHome',
            name: '学生主页',
            component: studentHome,
            meta: {requireAuth: true},
          },
        ],
      },
      {
        path: '/updateInfo',
        name: '学生编辑',
        component: updateInfo,
        meta: {requireAuth: true},
        children: [
          {
            path: '/updateInfoHome',
            name: '编辑学生信息',
            component: updateInfo,
            meta: {requireAuth: true}
          }
        ]
      },
      {
        path: '/studentSelectCourseManage',
        name: '选课管理',
        component: studentSelectCourseManage,
        meta: {requireAuth: true},
        children: [
          {
            path: '/studentSelectCourse',
            name: '选课',
            component: selectCourse,
            meta: {requireAuth: true}
          },
          {
            path: '/querySelectedCourse',
            name: '查询课表',
            component: querySelectedCourse,
            meta: {requireAuth: true}
          }
        ]
      },
      {
        path: '/courseGrade',
        name: '学生成绩管理',
        component: studentCourseGrade,
        meta: {requireAuth: true},
        children: [
          {
            path: '/queryCourseGrade',
            name: '成绩查询',
            component: queryCourseGrade,
            meta: {requireAuth: true}
          },
        ]
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

/*
  session 设置：
    1. token
    2. name
    3. type
    4. tid
    5. sid
    5. 系统信息 info
 */
router.beforeEach((to, from, next) => {
  console.log(from.path + ' ====> ' + to.path)
  if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
    if (sessionStorage.getItem("token") === 'true') { // 判断本地是否存在token
      next()
    } else {
      // 未登录,跳转到登陆页面
      next({
        path: '/login',
        query: {redirect: to.fullPath}
      })
    }
  } else {
    // 不需要登陆权限的页面，如果已经登陆，则跳转主页面
    if(sessionStorage.getItem("token") === 'true'){
      console.log('检查拦截器配置，大概率出现漏网之鱼')
      const t = sessionStorage.getItem("type")
      next('/' + t);
    }else{
      next();
    }
  }
});