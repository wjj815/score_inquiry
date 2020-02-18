package com.wangjj.scoreinquirywxback.controller;

import com.wangjj.scoreinquirywxback.pojo.dto.GradeDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.pojo.entity.Grade;
import com.wangjj.scoreinquirywxback.pojo.dto.response.APIResultBean;
import com.wangjj.scoreinquirywxback.service.GradeService;
import com.wangjj.scoreinquirywxback.valid.AddGroup;
import com.wangjj.scoreinquirywxback.valid.DeleteGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
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
	@ApiOperation(value = "保存年级信息")
	public APIResultBean addGrade(@RequestBody @Validated({AddGroup.class}) GradeDTO gradeDTO) {
		gradeService.saveGrade(gradeDTO);
		return APIResultBean.ok("添加成功").build();
	}

	@DeleteMapping
	@ApiOperation(value = "根据id删除年级信息(慎用)")
	public APIResultBean deleteGrade(@Validated({DeleteGroup.class}) GradeDTO gradeDTO) {
		gradeService.deleteGradeById(gradeDTO.getId());
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
									   GradeDTO gradeDTO) {

		PageRequest of = PageRequest.of(page - 1, limit);
		PageResult<GradeDTO> gradePage = gradeService.findGradePage(gradeDTO, of);
		return APIResultBean.ok(gradePage).build();
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取年级列表")
	public APIResultBean findGradeList(GradeDTO gradeDTO) {
		List<GradeDTO> gradeList = gradeService.findGradeList(gradeDTO);
		return APIResultBean.ok(gradeList).build();
	}
}
