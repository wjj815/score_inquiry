package com.wangjj.scoreinquirywxback.controller;

import com.alibaba.excel.EasyExcel;
import com.wangjj.scoreinquirywxback.pojo.entity.Teacher;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.service.TeacherService;
import com.wangjj.scoreinquirywxback.util.ExcelUtils;
import com.wangjj.scoreinquirywxback.util.HttpUtils;
import com.wangjj.scoreinquirywxback.pojo.dto.request.IdsParameter;
import com.wangjj.scoreinquirywxback.pojo.dto.response.APIResultBean;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName : TeacherController
 * @Author : wangJJ
 * @Date : 2020/1/31 11:47
 * @Description : 老师请求处理器
 */
@RestController
@Api(description = "老师类")
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@ApiOperation(value = "新增/修改教师")
	@PostMapping
	@ApiImplicitParam(name = "teacher",dataType = "Teacher")
	public APIResultBean saveCourse(@RequestBody Teacher teacher) {
		/*teacherService.saveTeacher(teacher);*/
		return APIResultBean.ok("操作成功！").build();
	}

	@ApiOperation(value = "分页得到老师信息")
	@GetMapping("/page")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "分页参数:页码(从1开始)", dataType = "Integer",defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "分页参数:页大小", dataType = "Integer",defaultValue = "10"),
	})
	public APIResultBean coursePage(@RequestParam Integer page,
									@RequestParam Integer limit) {
		/*Page<Teacher> teacherPage = teacherService.getTeacherPage(
				Teacher.builder().build(), PageRequest.of(page - 1, limit));*/
		return APIResultBean.ok(/*teacherPage*/).build();
	}

	@PostMapping("/excel")
	@ApiOperation(value = "导入教师信息excel")
	@ApiImplicitParam(name = "file" ,value = "excel文件(最大允许上传文件大小为100M)", dataTypeClass = MultipartFile.class)
	public APIResultBean importStudentList(@RequestParam("file") MultipartFile multipartFile) {

		//检查文件是否合法
		ExcelUtils.check(multipartFile);
		try {
			String s = teacherService.importTeacherList(multipartFile.getInputStream());
			return APIResultBean.ok("导入成功!"+s).build();
		} catch (IOException e) {
			e.printStackTrace();
			throw new GlobalException("导入失败,请检查您的导入数据");
		}
	}

	@GetMapping("/excel")
	@ApiOperation(value = "导出教师信息")
	@ApiImplicitParams({
	})
	public APIResultBean exportTeacherList() {
		HttpServletResponse response = HttpUtils.getExcelResponse("教师信息");
		List<Teacher> teacherList=null; /*= teacherService.getTeacherList(null);*/
		try {
			EasyExcel.write(response.getOutputStream(),Teacher.class).sheet().doWrite(teacherList);
		} catch (IOException e) {
			e.printStackTrace();
			return APIResultBean.error("导出失败").build();
		}
		return APIResultBean.ok("导出成功").build();
	}


	@GetMapping("/{id}")
	@ApiOperation(value = "根据教师id查询详细信息")
	@ApiImplicitParam(name = "id",value = "教师编号",dataType = "Long")
	public APIResultBean getTeacher(@PathVariable Long id) {
		Teacher teacherById = teacherService.getTeacherById(id);
		return APIResultBean.ok(teacherById).build();
	}


	@ApiOperation(value = "删除老师信息")
	@DeleteMapping
	@ApiImplicitParam(name = "ids",value = "课程id集合",dataType = "IdsParameter")
	public APIResultBean courseList(@RequestBody IdsParameter ids) {
		teacherService.deleteTeacher(ids.getIds());
		return APIResultBean.ok("删除成功！").build();
	}

	/*@ApiOperation(value = "保存关联老师对应的班级课程")
	@PostMapping("/clazzCourse")
	@ApiImplicitParam(name = "clazzCourseTeacher",value = "关联数据",dataType = "ClazzCourseTeacher")
	public APIResultBean saveClazzCourseTeacher(@RequestBody ClazzCourseTeacher clazzCourseTeacher){
		teacherService.saveClazzCourse(clazzCourseTeacher);
		return APIResultBean.ok("操作成功！").build();
	}*/

/*	@ApiOperation(value = "查询关联老师对应的班级课程")
	@GetMapping("/clazzCourse/page")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "分页参数:页码(从1开始)", dataType = "Integer",defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "分页参数:页大小", dataType = "Integer",defaultValue = "10"),
			@ApiImplicitParam(name = "teacherId",value = "教师编号",dataType = "Long")
	})
	public APIResultBean findClazzCourseTeacher(@RequestParam Integer page,
												@RequestParam Integer limit,
												@RequestParam(required = false) Long teacherId){
		PageResult<ClazzCourseTeacher> pageResult = teacherService.findClazzCoursePageByTeacherId(teacherId, PageRequest.of(page - 1, limit));
		return APIResultBean.ok(pageResult).build();
	}*/
}
