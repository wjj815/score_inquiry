package com.wangjj.scoreinquirywxback.pojo.bo;

import com.wangjj.scoreinquirywxback.pojo.entity.Course;
import com.wangjj.scoreinquirywxback.pojo.entity.Exam;
import com.wangjj.scoreinquirywxback.pojo.entity.Student;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName : StudentCourse
 * @Author : wangJJ
 * @Date : 2020/2/22 15:55
 * @Description : TODO
 */
@Data
public class StudentCourse {

	private Map<Long,Student> studentMap;

	private Course course;

	private Exam exam;
}
