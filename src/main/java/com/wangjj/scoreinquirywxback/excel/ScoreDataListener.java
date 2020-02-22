package com.wangjj.scoreinquirywxback.excel;

import com.wangjj.scoreinquirywxback.dao.CourseScoreRepository;
import com.wangjj.scoreinquirywxback.pojo.bo.StudentCourse;
import com.wangjj.scoreinquirywxback.pojo.entity.CourseScore;

/**
 * @ClassName : ScoreDataListener
 * @Author : wangJJ
 * @Date : 2020/2/22 15:53
 * @Description : TODO
 */
public class ScoreDataListener extends BaseDataListener<CourseScore> {


	private final StudentCourse studentCourse;

	public ScoreDataListener(CourseScoreRepository courseScoreRepository, StudentCourse studentCourse) {

		super(courseScoreRepository);
		this.studentCourse = studentCourse;
	}

	@Override
	protected void wrapperData(CourseScore data) {
		StringBuilder id = new StringBuilder();
		id.append(studentCourse.getExam().getId()).append(data.getStudentId()).append(studentCourse.getCourse().getId());
		data.setId(Long.parseLong(id.toString()));
		data.setStudent(studentCourse.getStudentMap().get(data.getStudentId()));
		data.setCourse(studentCourse.getCourse());
		data.setExam(studentCourse.getExam());
	}
}
