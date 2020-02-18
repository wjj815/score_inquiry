package com.wangjj.scoreinquirywxback.controller;

import com.wangjj.scoreinquirywxback.pojo.entity.Parent;
import com.wangjj.scoreinquirywxback.pojo.entity.Student;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.service.StudentService;
import com.wangjj.scoreinquirywxback.util.ExcelUtils;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
import com.wangjj.scoreinquirywxback.pojo.dto.response.APIResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;


/**
 * @ClassName : StudentController
 * @Author : wangJJ
 * @Date : 2019/12/26 20:26
 * @Description : 学生的控制器
 *
 */
@Slf4j
@Api(description = "学生类")
@RestController
@RequestMapping("/student")
public class StudentController {


	@Autowired
	private StudentService studentService;

	@PostMapping
	@ApiOperation(value = "增加/修改学生信息")
	@ApiImplicitParam(name = "student",value = "学生信息", dataType = "Student")
	public APIResultBean saveStudent(@RequestBody Student student) {
		//如果id为空，则该学生不存在，执行新增操作，如果id不为空，则进行局部更新操作
		Student originalStudent = Objects.isNull(student.getId()) ?
				new Student() : studentService.findStudentById(student.getId());
		BeanUtils.copyProperties(student,originalStudent, PropertyUtils.getNullPropertyNames(student));
//		studentService.saveStudent(originalStudent);
		return APIResultBean.ok("操作成功！").build();
	}

	@PostMapping("/excel")
	@ApiOperation(value = "导入学生信息excel")
	@ApiImplicitParam(name = "file" ,value = "excel文件(最大允许上传文件大小为100M)", dataTypeClass = MultipartFile.class)
	public APIResultBean importStudentList(@RequestParam("file") MultipartFile multipartFile) {
		log.info("导入文件名:{}",multipartFile.getOriginalFilename());
		//检查文件是否合法
		ExcelUtils.check(multipartFile);
		try {
			String s = studentService.importStudentList(multipartFile.getInputStream());
			return APIResultBean.ok("导入成功!"+s).build();
		} catch (IOException e) {
			e.printStackTrace();
			throw new GlobalException("导入失败,请检查您的导入数据");
		}
	}



	@GetMapping("/excel")
	@ApiOperation(value = "导出学生信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name="clazzId",value = "班级id",dataType = "Long"),
			@ApiImplicitParam(name="gradeId",value = "年级id",dataType = "Long")
	})
	public APIResultBean exportStudentList(@RequestParam(required = false) Long clazzId,
										   @RequestParam(required = false) Long gradeId) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = Objects.requireNonNull(requestAttributes).getResponse();
		Objects.requireNonNull(response).setContentType("application/force-download");
		response.setCharacterEncoding("utf-8");
		try {
			// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
			String fileName = URLEncoder.encode("学生信息", "UTF-8");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new GlobalException("导出Excel异常");
		}
//		List<Student> studentList = studentService.findStudent(new Student().toBuilder()
//				.clazzId(clazzId)
//				/*.gradeId(gradeId)*/
//				.build());
//		try {
////			EasyExcel.write(response.getOutputStream(),Student.class).sheet().doWrite(studentList);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return APIResultBean.error("导出失败").build();
//		}
		return APIResultBean.ok("导出成功").build();
	}


	@GetMapping("/list")
	@ApiOperation(value = "分页获得学生信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "分页参数:页码(从1开始)", dataType = "Integer",defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "分页参数:页大小", dataType = "Integer",defaultValue = "10"),
			@ApiImplicitParam(name="clazzId",value = "班级id",dataType = "Long"),
			@ApiImplicitParam(name="gradeId",value = "年级id",dataType = "Long")
	})
	public APIResultBean studentList(@RequestParam Integer page,
							 @RequestParam Integer limit,
							 @RequestParam(required = false) Long clazzId,
							 @RequestParam(required = false) Long gradeId) {
		PageRequest pageRequest = PageRequest.of(page - 1,limit);

//		Page<Student> studentPage = studentService.findStudent(
//				new Student().toBuilder()
//						.clazzId(clazzId)
//						/*.gradeId(gradeId)*/
//						.build(),pageRequest);
		return APIResultBean.ok(/*studentPage*/).build();
	}

	@GetMapping("/{studentId}")
	@ApiOperation(value = "根据id获取学生详情")
	@ApiImplicitParam(name = "studentId",value = "学生学号",dataType = "Long")
	public APIResultBean getStudent(@PathVariable Long studentId) {
		Student student = studentService.findStudentById(studentId);
		return APIResultBean.ok(student).build();
	}

	/*@PostMapping("{studentId}")
	@ApiOperation(value = "根据id修改学生信息")
	@ApiImplicitParam(name = "studentId",value = "学生学号",dataType = "Long")
	public APIResultBean getStudent(@PathVariable Long studentId,@RequestBody Student student) {
		Student student = studentService.findStudentById(studentId);
		return APIResultBean.ok(student).build();
	}*/

	@ApiOperation(value = "根据学生id保存学生家长")
	@PostMapping("/{studentId}/parent")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "studentId",value = "学生id",dataType = "Long"),
			@ApiImplicitParam(name = "parent", value = "家长信息", dataType = "Parent")
	})
	public APIResultBean saveStudentParent(@PathVariable("studentId") Long studentId,
										  @RequestBody Parent parent) {
		//如果id为空，则该学生不存在，执行新增操作，如果id不为空，则进行局部更新操作
		Parent originalParent = Objects.nonNull(parent.getId()) ?
				studentService.findParentByStudentIdAndParentId(studentId,parent.getId()) : new Parent();

		BeanUtils.copyProperties(parent,originalParent, PropertyUtils.getNullPropertyNames(parent));
//		studentService.saveRelevanceOfStudentAndParent(studentId,originalParent);
		return APIResultBean.ok("操作成功！").build();
	}


	@ApiOperation(value = "根据学生id查询学生家长")
	@GetMapping("/{studentId}/parent")
	@ApiImplicitParam(name = "studentId",value = "学生id",dataType = "Long")
	public APIResultBean findStudentParent(@PathVariable("studentId") Long studentId) {
		/*List<Parent> parents = studentService.findParentOfStudent(studentId);*/
		return APIResultBean.ok(/*parents*/).build();
	}

	@ApiOperation(value = "根据学生id和家长id查询学生家长")
	@GetMapping("/{studentId}/parent/{parentId}")
	@ApiImplicitParam(name = "studentId",value = "学生id",dataType = "Long")
	public APIResultBean findStudentParent(@PathVariable("studentId") Long studentId,
										   @PathVariable Long parentId) {
		Parent parent = studentService.findParentByStudentIdAndParentId(studentId,parentId);
		return APIResultBean.ok(parent).build();
	}

}
