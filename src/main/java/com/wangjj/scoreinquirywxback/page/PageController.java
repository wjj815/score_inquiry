package com.wangjj.scoreinquirywxback.page;

import com.alibaba.fastjson.JSON;
import com.wangjj.scoreinquirywxback.entity.Clazz;
import com.wangjj.scoreinquirywxback.entity.Grade;
import com.wangjj.scoreinquirywxback.entity.Student;
import com.wangjj.scoreinquirywxback.entity.User;
import com.wangjj.scoreinquirywxback.service.ClazzService;
import com.wangjj.scoreinquirywxback.service.GradeService;
import com.wangjj.scoreinquirywxback.service.StudentService;
import com.wangjj.scoreinquirywxback.util.HttpClientUtil;
import com.wangjj.scoreinquirywxback.util.SessionUtils;
import com.wangjj.scoreinquirywxback.vo.response.APIResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName : PageController
 * @Author : wangJJ
 * @Date : 2020/1/1 13:56
 * @Description : 页面的控制器
 */
@Slf4j
@Api(description = "页面类")
@Controller
public class PageController {

	private final StudentService studentService;
	private final GradeService gradeService;
	private final ClazzService clazzService;

	@Autowired
	public PageController(StudentService studentService, GradeService gradeService, ClazzService clazzService) {
		this.studentService = studentService;
		this.gradeService = gradeService;
		this.clazzService = clazzService;
	}

	@ApiOperation(value = "主页面")
	@GetMapping("/index")
	public String index(Model model) {
		User user = SessionUtils.getUser();
		model.addAttribute("user",user);
		return "admin/index";
	}
	@ApiOperation(value = "欢迎页面")
	@GetMapping("/welcome")
	public String welcome(Model model) {
		User user = SessionUtils.getUser();
		model.addAttribute("user",user);
		return "welcome";
	}



	@ApiOperation(value = "登录页面")
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@ApiOperation(value = "班级信息页面")
	@GetMapping("/clazz")
	public String clazzList() {
		return "clazz/clazzList";
	}

	@ApiOperation(value = "学生信息页面")
	@GetMapping("/student")
	public String studentList() {
		return "student/studentList";
	}

	@ApiOperation(value = "课程信息页面")
	@GetMapping("/course")
	public String courseList() {
		return "course/courseList";
	}

	@ApiOperation(value = "家长信息详情页面")
	@GetMapping("/parent")
	public String studentParent() {
		return "student/parentDetail";
	}

	@GetMapping("/student/{id}")
	public String studentDetail(@PathVariable Long id,Model model) {
		Student student = studentService.findStudentById(id);
		List<Grade> gradeList = gradeService.findGradeList();
//		log.info("student:{}", JSON.toJSON(student));
		model.addAttribute("gradeList",gradeList);
		List<Clazz> clazzList = clazzService.getClazzList(Clazz.builder().gradeId(student.getGradeId()).build());
		model.addAttribute("clazzList",clazzList);
		model.addAttribute("student",student);
		return "student/studentDetail";
	}
}
