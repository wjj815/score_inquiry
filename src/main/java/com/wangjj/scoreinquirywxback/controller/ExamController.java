package com.wangjj.scoreinquirywxback.controller;

import com.wangjj.scoreinquirywxback.entity.Exam;
import com.wangjj.scoreinquirywxback.entity.ExamScore;
import com.wangjj.scoreinquirywxback.service.ExamScoreService;
import com.wangjj.scoreinquirywxback.service.ExamService;
import com.wangjj.scoreinquirywxback.vo.request.IdsParameter;
import com.wangjj.scoreinquirywxback.vo.response.APIResultBean;
import com.wangjj.scoreinquirywxback.vo.response.PageResult;
import com.wangjj.scoreinquirywxback.vo.response.StudentScore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
	private ExamScoreService examScoreService;

	@PostMapping
	@ApiOperation(value = "增加考试信息")
	@ApiImplicitParam(name = "exam",value ="年级信息",dataType = "Exam")
	public APIResultBean saveExam(@RequestBody Exam exam) {
		examService.saveExam(exam);
		return APIResultBean.ok("添加成功").build();
	}

	@DeleteMapping
	@ApiOperation(value = "根据id删除考试信息(慎用)")
	@ApiImplicitParam(name = "idsParameter",value ="考试ids",dataType = "IdsParameter")
	public APIResultBean deleteExam(@RequestBody IdsParameter idsParameter) {
//		examService.deleteExam();
		return APIResultBean.ok("删除成功！").build();
	}


	@GetMapping("/page")
	@ApiOperation(value = "分页获取考试列表页面")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "分页参数:页码(从1开始)", defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "分页参数:页大小", defaultValue = "10"),
	})
	public APIResultBean findExamPage(@RequestParam(required=false) Integer page,
									   @RequestParam(required=false) Integer limit,
									   Exam exam) {
		Page<Exam> examPage = examService.getExamPage(exam, PageRequest.of(page - 1, limit));
		return APIResultBean.ok(examPage).build();
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取考试列表")
	public APIResultBean findExamList(Exam exam) {
		List<Exam> examList = examService.getExamList(exam);
		return APIResultBean.ok(examList).build();
	}

	@GetMapping("/{examId}/{studentId}")
	@ApiOperation(value = "查询学生在考试中的成绩")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "examId",value = "考试id"),
			@ApiImplicitParam(name = "studentId",value = "学生Id")
	})
	public APIResultBean findStudentExamScore(@PathVariable Long examId,@PathVariable Long studentId){
		StudentScore examScoreListByStudentIdAndExamId = examScoreService.getExamScoreListByStudentIdAndExamId(examId, studentId);
		return APIResultBean.ok(examScoreListByStudentIdAndExamId).build();
	}

	@GetMapping("/{examId}")
	@ApiOperation(value = "分页查询当前考试的学生考试成绩")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "分页参数:页码(从1开始)", defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "分页参数:页大小", defaultValue = "10"),
			@ApiImplicitParam(name = "examId", value = "考试id", dataType = "Long")
	})
	public APIResultBean findExamScorePage(@RequestParam(required=false) Integer page,
										   @RequestParam(required=false) Integer limit,
										   @PathVariable Long examId){

		PageResult<Map<String, Object>> examScoreListByPage = examScoreService.getExamScoreListByPage(examId, page - 1, limit);
		return APIResultBean.ok(examScoreListByPage).build();
	}

	@PostMapping("/score")
	@ApiOperation(value = "老师添加考试的课程成绩")
	public APIResultBean saveExamCourseScore(@RequestBody ExamScore examScore) {


		return APIResultBean.ok().build();
	}

}
