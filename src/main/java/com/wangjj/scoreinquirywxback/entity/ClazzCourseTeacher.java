package com.wangjj.scoreinquirywxback.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName : ClazzCourseTeacher
 * @Author : wangJJ
 * @Date : 2020/1/23 14:12
 * @Description : 班级课程教师关系实体表
 */
@Entity
@ToString
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_clazz_course_teacher")
public class ClazzCourseTeacher {
	@Id
	@GeneratedValue
	private Long id;
	/**班级id*/
	private Long clazzId;
	/**课程id*/
	private Long courseId;
	/**老师id*/
	private Long teacherId;
}
