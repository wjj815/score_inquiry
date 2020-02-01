package com.wangjj.scoreinquirywxback.controller;

import com.wangjj.scoreinquirywxback.entity.Grade;
import com.wangjj.scoreinquirywxback.service.GradeService;
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
 * @ClassName : GradeController
 * @Author : wangJJ
 * @Date : 2020/1/26 20:36
 * @Description : 年级的请求处理器
 */
@RestController
@RequestMapping("/grade")
@Api(description = "年级类")
public class GradeController {

	@Autowired
	private GradeService gradeService;

	@PostMapping
	@ApiOperation(value = "增加年级信息")
	@ApiImplicitParam(name = "grade",value ="年级信息",dataType = "Grade")
	public APIResultBean addGrade(@RequestBody Grade grade) {
		gradeService.addGrade(grade);
		return APIResultBean.ok("添加成功").build();
	}

	@DeleteMapping
	@ApiOperation(value = "根据id删除年级信息(慎用)")
	@ApiImplicitParam(name = "id",value ="年级id",dataType = "Long")
	public APIResultBean deleteGrade(Long id) {
		gradeService.deleteGradeById(id);
		return APIResultBean.ok("删除成功！").build();
	}

	@GetMapping
	@ApiOperation(value = "查询年级信息是否存在")
	@ApiImplicitParam(name = "gradeName",value ="年级名称",dataType = "String")
	public APIResultBean findGrade(@RequestParam String gradeName) {
		Grade grade = gradeService.findByGradeName(gradeName);
		return APIResultBean.ok(grade).build();
	}

	@GetMapping("/page")
	@ApiOperation(value = "分页获取年级信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "分页参数:页码(从1开始)", defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "分页参数:页大小", defaultValue = "10"),
			@ApiImplicitParam(name = "gradeId", value = "年级id")
	})
	public APIResultBean findGradePage(@RequestParam Integer page,
									   @RequestParam Integer limit,
									   @RequestParam(required = false) Long gradeId) {
		Page<Grade> gradePage = gradeService.findGradePage(
				Grade.builder().id(gradeId).build(),
				PageRequest.of(page - 1, limit));
		return APIResultBean.ok(gradePage).build();
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取年级列表")
	public APIResultBean findGradeList() {
		List<Grade> gradeList = gradeService.findGradeList();
		return APIResultBean.ok(gradeList).build();
	}
}
