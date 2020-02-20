package com.wangjj.scoreinquirywxback.pojo.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangjj.scoreinquirywxback.pojo.entity.Clazz;
import com.wangjj.scoreinquirywxback.pojo.entity.Grade;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName : StudentDTO
 * @Author : wangJJ
 * @Date : 2020/2/14 20:27
 * @Description : 学生数据传输对象
 */
@Data
public class StudentDTO {

	/** 学生学号 */
	@ApiModelProperty(name = "id", value = "学生id",example = "201610001")
	@ExcelProperty(value = "学号")
	private Long id;
	/** 学生姓名 */
	@ApiModelProperty(name = "studentName", value = "学生姓名",example = "张三")
	@ExcelProperty(value = "姓名")
	private String studentName ;
	/** 性别 */
	@ApiModelProperty(name = "sex", value = "性别",example = "男")
	@ExcelProperty(value = "性别")
	private String sex ;
	/** 年龄 */
	@ApiModelProperty(name = "age", value = "年龄",example = "10")
//	@ExcelProperty(value = "班级编号")
	@ExcelProperty("年龄")
	private Integer age;
	@ApiModelProperty(name = "idCardNo", value = "身份证号",example = "610404199203442233")
	/** 身份证号 */
	@ExcelProperty(value = "身份证号")
	private String idCardNo ;
	/** 生日 */
	@ApiModelProperty(name = "birthday", value = "生日",example = "2013-2-4")
	@ExcelProperty("出生日期")
	@DateTimeFormat(value = "yyyy/MM/dd")
	private Date birthday ;
	/** 微信 */
	@ApiModelProperty(name = "weiXin", value = "微信号",example = "w12345")
	@ExcelProperty(value = "微信")
	private String weiXin;
	/** 手机号 */
	@ApiModelProperty(name = "mobilePhone", value = "手机号",example = "12345678901")
	@ExcelProperty(value = "手机号")
	private String mobilePhone ;
	/** 籍贯 */
	@ApiModelProperty(name = "domicilePlace", value = "籍贯",example = "陕西省")
	@ExcelProperty(value = "籍贯")
	private String domicilePlace ;
	/** 居住地址 */
	@ExcelProperty(value = "居住地址")
	@ApiModelProperty(name = "presentAddress", value = "居住地址",example = "陕西省渭城区周岭镇的大街")
	private String presentAddress ;
	/** 状态 */
	@ExcelIgnore
	@ApiParam(hidden = true)
	private String status ;

	/** 所在班级 */
	@ApiModelProperty(name = "clazzId", value = "班级Id",example = "班级id")
	@ExcelProperty(value = "班级编号")
	private Long clazzId;//班级id

	/*所在年级id*/
	@ApiModelProperty(name = "gradeId", value = "年级Id",example = "年级id")
	@ExcelProperty(value = "年级编号")
	private Long gradeId;


}
