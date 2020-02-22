package com.wangjj.scoreinquirywxback.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName : StudentScoreDTO
 * @Author : wangJJ
 * @Date : 2020/2/14 20:23
 * @Description : TODO
 */
@Data
public class StudentScoreDTO {

	private StudentDTO studentDTO;

	private List<CourseScoreDTO> scoreDTOList;

	private Integer totalScore;

	private Integer GradeRank;

	private Integer ClazzRank;

}
