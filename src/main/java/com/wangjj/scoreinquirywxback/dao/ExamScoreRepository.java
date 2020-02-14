package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.entity.ExamScore;
import com.wangjj.scoreinquirywxback.vo.response.StudentExamScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName : ExamScoreRepository
 * @Author : wangJJ
 * @Date : 2020/1/21 20:03
 * @Description : 考试成绩表
 */
public interface ExamScoreRepository extends JpaRepository<ExamScore,Long>, JpaSpecificationExecutor<ExamScore> {

	int deleteByExamIdIn(List<Long> ids);


	@Query(value = "" +
			"SELECT COUNT(1)+1 AS rank FROM (SELECT student_id,SUM(score) AS t_score FROM t_exam_score WHERE exam_id=:examId GROUP BY student_id) u\n" +
			" WHERE u.t_score > (SELECT SUM(score) FROM t_exam_score WHERE exam_id=:examId AND student_id =:studentId)" +
			"",nativeQuery = true)
	int getStudentGradeRankByExamIdAndStudentId(@Param("examId") Long examId,@Param("studentId") Long studentId);
	@Query(value = "" +
			"SELECT COUNT(1)+1 AS rank FROM (SELECT student_id,SUM(score) AS t_score FROM t_exam_score t1,t_student t2 WHERE t1.student_id=t2.id AND t1.exam_id=:examId and t2.clazz_id=:clazzId GROUP BY student_id) u\n" +
			" WHERE u.t_score > (SELECT SUM(score) FROM t_exam_score WHERE exam_id=:examId AND student_id =:studentId)" +
			"",nativeQuery = true)
	int getStudentClazzRankByExamIdAndClazzIdAndStudentId(@Param("examId") Long examId,@Param("clazzId")Long clazzId,@Param("studentId") Long studentId);

	@Query(value = "SELECT student_id as studentId FROM t_exam_score WHERE exam_id = :examId GROUP BY student_id ORDER BY SUM(score) DESC  LIMIT :page, :pageSize",nativeQuery = true)
	List<Long> getExamStudent(@Param("examId") Long examId,@Param("page") int page,@Param("pageSize") int pageSize);
	@Query(value = "SELECT COUNT(1) FROM (SELECT student_id FROM t_exam_score WHERE exam_id = :examId GROUP BY student_id) u",nativeQuery = true)
	int getExamStudentCount(@Param("examId") Long examId);

}
