package com.wangjj.scoreinquirywxback.page;

import com.wangjj.scoreinquirywxback.pojo.dto.MenuDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.UserDTO;
import com.wangjj.scoreinquirywxback.service.*;
import com.wangjj.scoreinquirywxback.util.SessionUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @ClassName : PageController
 * @Author : wangJJ
 * @Date : 2020/1/1 13:56
 * @Description : 页面的控制器
 */
@Slf4j
@ApiIgnore
@Controller
public class PageController {

	private final MenuService menuService;


	@Autowired
	public PageController(MenuService menuService) {
		this.menuService = menuService;
	}

	@ApiOperation(value = "主页面")
	@GetMapping("/index")
	public String index(Model model) {
		UserDTO user = SessionUtils.getUser();
		if (user != null) {

			MenuDTO menuDTO = new MenuDTO();
			menuDTO.setRoleId(user.getRoleId());
			List<MenuDTO> menuList = menuService.buildMenuTree(menuDTO);
			model.addAttribute("menuList",menuList);
		}
		model.addAttribute("user",user);
		return "admin/index";
	}
	@ApiOperation(value = "欢迎页面")
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}



	@GetMapping("/login")
	public String login() {
		return "login";
	}


	@GetMapping("/clazz")
	public String clazzList() {
		return "clazz/clazzList";
	}


	@GetMapping("/student")
	public String studentList() {
		return "student/studentList";
	}


	@GetMapping("/course")
	public String courseList() {
		return "course/courseList";
	}

	@GetMapping("/teacher")
	public String teacherList() {
		return "teacher/teacherList";
	}

	@GetMapping("/teacherDetail")
	public String teacherDetail() {
		return "teacher/teacherDetail";
	}


	@GetMapping("/parent")
	public String studentParent() {
		return "parent/parentDetail";
	}

	@GetMapping("/gradeDetail")
	public String gradeDetail() {
		return "grade/gradeDetail";
	}

	@GetMapping("/clazzDetail")
	public String clazzDetail() {
		return "clazz/clazzDetail";
	}

	@GetMapping("/grade")
	public String grade() {
		return "grade/gradeList";
	}

	@GetMapping("/clazzCourseTeacher")
	public String courseTeacher() {
		return "clazz/clazzCourseTeacher";
	}

	@GetMapping("/uploadForm")
	public String uploadForm() {
		return "upload/uploadForm";
	}


	@GetMapping("/studentDetail")
	public String studentDetail() {
		return "student/studentDetail";
	}

	@GetMapping("/examDetail")
	public String examDetail() {
		return "exam/examDetail";
	}

	@GetMapping("/exam")
	public String exam() {
		return "exam/examList";
	}

	@GetMapping("/examScore")
	public String examScore() {
		return "exam/examScore";
	}

	@GetMapping("/enterScore")
	public String enterScore() {
		return "teacher/enterScore";
	}
	@GetMapping("/enterScoreDetail")
	public String enterScoreDetail() {
		return "teacher/enterScoreDetail";
	}

	@GetMapping("/userList")
	public String userList(){
		return "user/userList";
	}
}
