package com.wangjj.scoreinquirywxback.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;

/**
 * @ClassName : ClazzCourseTeacher
 * @Author : wangJJ
 * @Date : 2020/1/23 14:12
 * @Description : 班级课程教师关系实体表
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@ApiModel(description = "班级课程教师关联类")
@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_clazz_course_teacher")
public class ClazzCourseTeacher {
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue
	private Long id;
	/**班级id*/
	@ApiModelProperty(name = "clazzId",value = "班级Id",required = true)
	private Long clazzId;
	@ApiModelProperty(hidden = true)
	@Transient
	private Clazz clazz;
	/**课程id*/
	@ApiModelProperty(name = "courseId",value = "课程Id",required = true)
	private Long courseId;
	@ApiModelProperty(hidden = true)
	@Transient
	private Course course;
	/**老师id*/
	@ApiModelProperty(name = "teacherId",value = "老师Id",required = true)
	private Long teacherId;
	@ApiModelProperty(hidden = true)
	@Transient
	private Teacher teacher;

}
