package com.wangjj.scoreinquirywxback.controller;

import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.pojo.dto.CourseScoreDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.StudentScoreDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.APIResultBean;
import com.wangjj.scoreinquirywxback.service.CourseScoreService;
import com.wangjj.scoreinquirywxback.service.CourseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName : CourseScoreController
 * @Author : wangJJ
 * @Date : 2020/2/23 10:50
 * @Description : TODO
 */
@RestController
@RequestMapping("/examScore")
public class ExamScoreController {

	@Autowired
	private CourseScoreService courseScoreService;

	@PostMapping(value = "{id}",headers = "content-type=multipart/form-data",consumes = "multipart/*")
	@ApiOperation( value = "导入成绩")
	@ApiImplicitParam(name = "id",value = "导入成绩id:examId_clazzId_courseId")
	public APIResultBean enterScore(@ApiParam(value = "上传的文件",required = true) MultipartFile file,
									@PathVariable String id) {
		String[] arr = id.split("_");
		if(arr.length != 3) {
			return APIResultBean.ok("非法url参数").build();
		}
		CourseScoreDTO courseScoreDTO = new CourseScoreDTO();
		courseScoreDTO.setExamId(Long.parseLong(arr[0]));
		courseScoreDTO.setClazzId(Long.parseLong(arr[1]));
		courseScoreDTO.setCourseId(Long.parseLong(arr[2]));
		try {
			courseScoreService.importStudentScore(file.getInputStream(),courseScoreDTO);
		} catch (IOException e) {
			e.printStackTrace();
			throw new GlobalException("发生异常！");
		}
		return APIResultBean.ok(file.getOriginalFilename()).build();
	}

	@GetMapping("/scoreList")
	@ApiOperation("获得考试成绩")
	public APIResultBean getExamScoreList(CourseScoreDTO courseScoreDTO) {

		List<StudentScoreDTO> studentScoreDTOList = courseScoreService.getStudentScoreDTOList(courseScoreDTO);
		List<Map<String,Object>> maps = new ArrayList<>();
		studentScoreDTOList.forEach(studentScoreDTO -> {
			Map<String,Object> map = new HashMap<>();
			map.put("clazzId",studentScoreDTO.getStudentDTO().getClazzId());
			map.put("studentId",studentScoreDTO.getStudentDTO().getId());
			map.put("studentName",studentScoreDTO.getStudentDTO().getStudentName());
			Map<String, Integer> collect = studentScoreDTO.getScoreDTOList().stream().collect(Collectors.toMap(courseScoreDTO1 -> courseScoreDTO1.getCourseId()+"",CourseScoreDTO::getScore));
			map.putAll(collect);
			map.put("totalScore",studentScoreDTO.getTotalScore());
			maps.add(map);
		});
		return APIResultBean.ok(maps).build();
	}

	@GetMapping("/list")
	public APIResultBean getList(CourseScoreDTO courseScoreDTO) {
		List<CourseScoreDTO> courseScoreList = courseScoreService.getStudentScoreDTOListForCourse(courseScoreDTO);
		return APIResultBean.ok(courseScoreList).build();
	}



}
