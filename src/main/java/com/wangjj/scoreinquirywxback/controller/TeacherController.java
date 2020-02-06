package com.wangjj.scoreinquirywxback.controller;

import com.wangjj.scoreinquirywxback.entity.Teacher;
import com.wangjj.scoreinquirywxback.service.TeacherService;
import com.wangjj.scoreinquirywxback.vo.response.APIResultBean;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName : TeacherController
 * @Author : wangJJ
 * @Date : 2020/1/31 11:47
 * @Description : 老师请求处理器
 */
public class TeacherController {

	private TeacherService teacherService;

	@ApiOperation(value = "新增/修改教师")
	@PostMapping
	@ApiImplicitParam(name = "teacher",dataType = "Teacher")
	public APIResultBean saveCourse(@RequestBody Teacher teacher) {
		teacherService.saveTeacher(teacher);
		return APIResultBean.ok("操作成功！").build();
	}

	@ApiOperation(value = "分页得到老师信息")
	@GetMapping
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "分页参数:页码(从1开始)", dataType = "Integer",defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "分页参数:页大小", dataType = "Integer",defaultValue = "10"),
	})
	public APIResultBean coursePage(@RequestParam Integer page,
									@RequestParam Integer limit) {
		Page<Teacher> teacherPage = teacherService.getTeacherPage(PageRequest.of(page - 1, limit));
		return APIResultBean.ok(teacherPage).build();
	}



	@ApiOperation(value = "删除老师信息")
	@DeleteMapping
	@ApiImplicitParam(name = "id",value = "课程id")
	public APIResultBean courseList(@RequestBody Long id) {
		teacherService.deleteTeacher(id);
		return APIResultBean.ok("删除成功！").build();
	}
}
