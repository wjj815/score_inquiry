package com.wangjj.scoreinquirywxback.pojo.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.wangjj.scoreinquirywxback.valid.AddGroup;
import com.wangjj.scoreinquirywxback.valid.DeleteGroup;
import com.wangjj.scoreinquirywxback.valid.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @ClassName : TeacherDTO
 * @Author : wangJJ
 * @Date : 2020/2/16 12:15
 * @Description : TODO
 */
@Data
public class TeacherDTO {

	@NotNull(message = "教师编号不能为空",groups = {AddGroup.class, UpdateGroup.class, DeleteGroup.class})
	@ApiModelProperty(name = "id",value = "教师编号",required = true,example = "2019001")
	@Id
//	@GeneratedValue
	private Long id; //Id
	/** 姓名 */
	@NotBlank(message = "教师姓名不能为空",groups = {AddGroup.class})
	@ApiModelProperty(name="teacherName",value = "教师姓名",required = true,example = "王老师")
	private String teacherName ;
	/** 性别 */
	@NotBlank(message = "教师性别不能为空",groups = {AddGroup.class})
	@ExcelProperty("性别")
	@ApiModelProperty(name="sex",value = "性别",required = true,example = "男")
	private String sex ;
	/** 年龄*/
	@ExcelProperty("年龄")
	@ColumnWidth(value = 10)
	private Integer age;
	/** 出生日期 */
	@NotBlank(message = "出生日期不能为空",groups = {AddGroup.class})
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(value = "yyyy/MM/dd")
	@ApiModelProperty(name="birthday",value = "出生日期",example = "1976-2-13",dataType = "Date")
	private Date birthday ;
	/** 身份证号 */
	@NotBlank(message = "身份证号不能为空",groups = {AddGroup.class})
	@Pattern(message = "身份证长度不合法",regexp = "^\\d{18}$",groups = {AddGroup.class})
	@ExcelProperty("身份证号")
	@ColumnWidth(value = 30)
	@ApiModelProperty(name="idCardNo",value = "身份证号",example = "18位数字")
	private String idCardNo ;
	/** 电话 */

	@Pattern(regexp = "^((13[0-9])|(17[0-1,6-8])|(15[^4,\\D])|(18[0-9]))\\d{8}$",message = "电话不合法",
	groups = {AddGroup.class})
	@ExcelProperty("联系电话")
	@ColumnWidth(value = 20)
	@ApiModelProperty(name = "phone",value = "联系电话", example = "11位手机号")
	private String phone ;
	/** 介绍 */
	@ExcelProperty("详细介绍")
	@ColumnWidth(value = 30)

	@ApiModelProperty(name = "introduction",value = "介绍")
	private String introduction ;
	/** 创建人 */
	@ExcelIgnore
	@ApiModelProperty(hidden = true)
	private String createdBy ;
	/** 创建时间 */
	@Temporal(TemporalType.DATE)
	@CreatedDate
	@ExcelProperty("创建时间")
	@ColumnWidth(value = 30)
	@ApiModelProperty(hidden = true)
	private Date createdTime ;
	/** 更新人 */
	@ExcelIgnore
	@ApiModelProperty(hidden = true)
	private String updatedBy ;
	/** 更新时间 */
	@ExcelIgnore
	@LastModifiedDate
	@ApiModelProperty(hidden = true)
	private Date updatedTime ;

	@ExcelIgnore
	/** 微信 */
	@ApiModelProperty(hidden = true)
	private String weiXin;



	private Long courseId;

	private Long clazzId;


}
