package com.wangjj.scoreinquirywxback.pojo.dto;

import lombok.Data;

/**
 * @ClassName : CourseScoreDTO
 * @Author : wangJJ
 * @Date : 2020/2/14 20:21
 * @Description : TODO
 */
@Data
public class CourseScoreDTO {

	private Long id;

	private Long courseId;

	private String courseName;

	private Integer score;

	private Long examId;

	private Long studentId;

	private Long teacherId;

}