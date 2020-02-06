package com.wangjj.scoreinquirywxback.controller;

import com.wangjj.scoreinquirywxback.entity.Course;
import com.wangjj.scoreinquirywxback.service.CourseService;
import com.wangjj.scoreinquirywxback.vo.request.IdsParameter;
import com.wangjj.scoreinquirywxback.vo.response.APIResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName : CourseController
 * @Author : wangJJ
 * @Date : 2020/1/31 11:47
 * @Description : 课程类请求的处理器
 */
@RestController
@Api(description = "课程类")
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;


	@ApiOperation(value = "新增/修改课程")
	@PostMapping
	@ApiImplicitParam(name = "course",dataType = "Course")
	public APIResultBean saveCourse(@RequestBody Course course) {
		courseService.saveCourse(course);
		return APIResultBean.ok("操作成功！").build();
	}

	@ApiOperation(value = "分页得到课程信息")
	@GetMapping("/page")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "分页参数:页码(从1开始)", dataType = "Integer",defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "分页参数:页大小", dataType = "Integer",defaultValue = "10"),
	})
	public APIResultBean coursePage(@RequestParam Integer page,
									@RequestParam Integer limit) {
		Page<Course> coursePage = courseService.getCoursePage(PageRequest.of(page - 1, limit));
		return APIResultBean.ok(coursePage).build();
	}

	@ApiOperation(value = "得到课程信息列表")
	@GetMapping("/list")
	@ApiImplicitParams({})
	public APIResultBean courseList() {
		List<Course> courseList = courseService.getCourseList();
		return APIResultBean.ok(courseList).build();
	}

	@ApiOperation(value = "删除课程信息")
	@DeleteMapping
	@ApiImplicitParam(name = "idsParameter",value = "课程id集合",example = "IdsParameter")
	public APIResultBean courseList(@RequestBody IdsParameter idsParameter) {
		courseService.deleteCourse(idsParameter.getIds());
		return APIResultBean.ok("删除成功！").build();
	}
}
