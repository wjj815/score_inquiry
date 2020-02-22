package com.wangjj.scoreinquirywxback.pojo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName : ScoreExcel
 * @Author : wangJJ
 * @Date : 2020/2/22 15:50
 * @Description : TODO
 */
@Data
public class ScoreExcel {


	@ExcelProperty(value = "学号")
	private Long studentId;
	@ExcelProperty(value = "姓名")
	private String studentName;

	@ExcelProperty(value = "科目")
	private String courseName;
	@ExcelProperty(value = "成绩")
	private Integer score;
}
