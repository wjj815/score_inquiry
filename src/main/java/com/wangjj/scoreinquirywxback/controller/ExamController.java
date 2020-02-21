package com.wangjj.scoreinquirywxback.controller;

import com.wangjj.scoreinquirywxback.pojo.dto.ExamDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.request.IdsParameter;
import com.wangjj.scoreinquirywxback.pojo.dto.request.PageParameter;
import com.wangjj.scoreinquirywxback.pojo.dto.response.APIResultBean;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.service.CourseScoreService;
import com.wangjj.scoreinquirywxback.service.ExamService;
import com.wangjj.scoreinquirywxback.valid.DeleteGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName : ExamController
 * @Author : wangJJ
 * @Date : 2020/1/31 11:47
 * @Description : 处理考试请求控制器
 */
@Api(description = "考试类")
@RestController
@RequestMapping("/exam")
public class ExamController {

	@Autowired
	private ExamService examService;

	@Autowired
	private CourseScoreService examScoreService;

	@PostMapping
	@ApiOperation(value = "保存考试信息")
	public APIResultBean saveExam(@RequestBody ExamDTO examDTO) {
		examService.saveExam(examDTO);
		return APIResultBean.ok("添加成功").build();
	}

	@GetMapping("/{examId}")
	@ApiOperation(value = "通过examId查询考试信息")
	@ApiImplicitParam(name = "examId",value = "考试id", dataType = "Long")
	public APIResultBean getExam(@PathVariable Long examId) {
		ExamDTO examById = examService.getExamById(examId);
		return APIResultBean.ok(examById).build();
	}

	@DeleteMapping("/{examId}")
	@ApiOperation(value = "根据id删除考试信息(慎用)")
	@ApiImplicitParam(name = "examId",value ="考试id",dataType = "Long")
	public APIResultBean deleteExam(@PathVariable Long examId) {
		examService.deleteExamById(examId);
		return APIResultBean.ok("删除成功！").build();
	}


	@GetMapping("/page")
	@ApiOperation(value = "分页获取考试列表页面")
	public APIResultBean findExamPage(PageParameter pageParameter,
									   ExamDTO examDTO) {
		PageResult<ExamDTO> examPage = examService.getExamPage(examDTO, PageRequest.of(pageParameter.getPage() - 1, pageParameter.getLimit()));
		return APIResultBean.ok(examPage).build();
	}

	@DeleteMapping("/list")
	@ApiOperation(value = "根据ids批量删除考试")
	public APIResultBean deleteExamList(@RequestBody @Validated({DeleteGroup.class}) IdsParameter idsParameter) {
		examService.batchDeleteExamByIds(idsParameter.getIds());
		return APIResultBean.ok("删除成功！").build();
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取考试列表")
	public APIResultBean findExamList(ExamDTO examDTO) {
		List<ExamDTO> examList = examService.getExamList(examDTO);
		return APIResultBean.ok(examList).build();
	}

}
