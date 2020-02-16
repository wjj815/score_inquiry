package com.wangjj.scoreinquirywxback.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName : ExamDTO
 * @Author : wangJJ
 * @Date : 2020/2/14 18:44
 * @Description : 考试数据传输对象
 */
@ApiModel(description = "考试类")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ExamDTO {

	@ApiModelProperty(hidden = true)
	private Long id; //ID
	/**
	 * 考试名称
	 */
	@ApiModelProperty(name = "examName", value = "考试名称", example = "2019级第一次期中考试")
	private String examName;
	/**
	 * 考试时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(name = "examTime", value = "考试时间", example = "2019-12-1", dataType = "Date")
	private Date examTime;
	/**
	 * 考试备注
	 */
	@ApiModelProperty(name = "remark", value = "考试备注")
	private String remark;
	/**
	 * 考试类型
	 */
	@ApiModelProperty(name = "examType", value = "考试类型:班级平时考试（0）,年级考试(1):月考、期中考、期末考", example = "1")
	private Integer examType;
	/**
	 * 班级id
	 */
	@ApiModelProperty(name = "clazzId", value = "班级Id", example = "201901")
	private Long clazzId; //考试的班级: 平时考试涉及到某个班级，统考则为所有班级

	@ApiModelProperty(name = "gradeId", value = "年级Id", example = "2019")
	private Long gradeId;//年级Id

	private Long studentId;

	private Long teacherId;
}
