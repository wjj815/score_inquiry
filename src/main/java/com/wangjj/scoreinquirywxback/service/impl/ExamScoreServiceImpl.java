package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.dao.*;
import com.wangjj.scoreinquirywxback.entity.ExamScore;
import com.wangjj.scoreinquirywxback.entity.Student;
import com.wangjj.scoreinquirywxback.service.ExamScoreService;
import com.wangjj.scoreinquirywxback.vo.response.PageResult;
import com.wangjj.scoreinquirywxback.vo.response.StudentScore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName : ExamScoreServiceImpl
 * @Author : wangJJ
 * @Date : 2020/2/12 17:50
 * @Description : 考试成绩
 */
@Slf4j
@Service
public class ExamScoreServiceImpl implements ExamScoreService {

	@Autowired
	private ExamScoreRepository examScoreRepository;

	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private GradeCourseRepository gradeCourseRepository;

	/*考试成绩分页显示，根据考试来进行区分显示，
	 * 过滤条件1、examId,表明是哪场考试的考试成绩
	 * 2、studentId,表明是哪个学生的考试
	 * 学生的成绩组成为各科目的成绩，各科目成绩的总分，以及这次考试中年级的排名
	 * 和班级的排名。
	 * 需要重新抽象数据对象
	 * 分析：一场考试的范围是整个年级，所以外类可以表示考试id
	 * 根据考试id从examScore表中查出若干条数据，然后通过studentId进行分组，每一组为每个学生的科目成绩
	 * 其中需要考虑的情况：1. 是否存在某学生的某科目无成绩，则分组查出的科目成绩会不会少一个，怎么构建正确的数据
	 * 设计思路：
	 * 1、根据examId为第一个条件查询数据，然后根据studentId来进行分组后得到student的科目成绩，然后根据courseId进行排序
	 * 来得到科目的顺序显示
	 *
	 * select studentId, from examScore where examId = 1 group by studentId orderBy courseId
	 * */
	@Override
	public Page<ExamScore> getExamScorePage(ExamScore examScore, Pageable pageable) {
		return null;
	}


	@Override
	public PageResult<Map<String, Object>> getExamScoreListByPage(Long examId, int page, int pageSize) {
		//得到参加考试的学生id
		List<Long> studentIdList = examScoreRepository.getExamStudent(examId, page, pageSize);
		log.info("studentIdList:{}", studentIdList);
		//得到参加考试的学生人数
		int examStudentCount = examScoreRepository.getExamStudentCount(examId);
		PageResult<Map<String,Object>> scorePageResult = new PageResult<>();
		scorePageResult.setTotalElements(examStudentCount);
		log.info("count:{}", examStudentCount);
		//封装学生考试成绩信息
		List<Map<String,Object>> studentExamScores = new ArrayList<>();

		studentIdList.forEach(id ->{
			Map<String,Object> map = new HashMap<> ();
			StudentScore examScoreList = getExamScoreListByStudentIdAndExamId(examId, id);
			map.put("clazzId",examScoreList.getStudent().getClazzId());
			map.put("studentId",examScoreList.getStudent().getId());
			map.put("studentName",examScoreList.getStudent().getStudentName());
			map.put("totalScore",examScoreList.getTotalScore());
			map.put("clazzRank",examScoreList.getClazzRank());
			map.put("gradeRank",examScoreList.getGradeRank());
			for (ExamScore examScore : examScoreList.getExamScoreList()) {
				map.put(String.valueOf(examScore.getCourseId()),examScore.getScore());
			}
			studentExamScores.add(map);
		});
		scorePageResult.setContent(studentExamScores);
		return scorePageResult;
	}

	/*查询某个学生的考试成绩*/
	/** SELECT t1.courses_id,t2.score FROM t_exam_courses t1 LEFT JOIN (
	 SELECT student_id,course_id,score FROM t_exam_score WHERE student_id=20190101
	 ) t2 ON t1.courses_id=t2.course_id
	 */


	/**
	 * 获取学生考试成绩
	 *
	 * @param examId    考试id
	 * @param studentId 学生id
	 * @return 学生的考试信息
	 */
	@Override
	public StudentScore getExamScoreListByStudentIdAndExamId(Long examId, Long studentId) {
		log.info("查询 {} 的 {} 考试成绩", examId, studentId);
		ExamScore build = ExamScore.builder().examId(examId).studentId(studentId).build();
		Student student = studentRepository.getOne(studentId);
		List<ExamScore> examScoreList = examScoreRepository.findAll(Example.of(build), Sort.by(Sort.Order.asc("courseId")));
		AtomicInteger totalScore = new AtomicInteger();
		examScoreList.forEach(e -> {
			e.setCourse(courseRepository.getOne(e.getCourseId()));
			totalScore.addAndGet(Objects.isNull(e.getScore()) ? 0 : e.getScore());
		});
		int gradeRank = examScoreRepository.getStudentGradeRankByExamIdAndStudentId(examId, studentId);
		int clazzRank = examScoreRepository.getStudentClazzRankByExamIdAndClazzIdAndStudentId(examId, student.getClazzId(), studentId);


		return StudentScore.builder().student(student)
				.examScoreList(examScoreList)
				.gradeRank(gradeRank)
				.clazzRank(clazzRank)
				.totalScore(totalScore.get())
				.build();
	}


	/**
	 * 查询年级排名的sql
	 * SELECT COUNT(1)+1 AS rank FROM (SELECT student_id,SUM(score) AS t_score FROM t_exam_score WHERE exam_id=39 GROUP BY student_id) u
	 * WHERE u.t_score > (SELECT SUM(score) FROM t_exam_score WHERE exam_id=39 AND student_id = 20190101)
	 */


	@Override
	public void saveExamScore(ExamScore examScore) {
		examScoreRepository.save(examScore);
	}

	@Override
	public void saveExamScoreList(List<ExamScore> examScoreList) {
		examScoreRepository.saveAll(examScoreList);
	}

	@Override
	public void deleteExamScore(Long id) {

	}
}
