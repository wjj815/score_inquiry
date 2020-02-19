package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.dao.ExamRepository;
import com.wangjj.scoreinquirywxback.dao.GradeRepository;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.pojo.dto.ExamDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.pojo.entity.CourseScore;
import com.wangjj.scoreinquirywxback.pojo.entity.Exam;
import com.wangjj.scoreinquirywxback.pojo.entity.Grade;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName : ExamServiceImpl
 * @Author : wangJJ
 * @Date : 2020/2/14 18:16
 * @Description : 考试业务的实现类
 */
@Slf4j
@Service
public class ExamService {

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private GradeRepository gradeRepository;




	public Page<Exam> getExamPage(Exam exam, Pageable pageable) {

		return null;
	}


	public List<ExamDTO> getExamList(ExamDTO examDTO) {
		List<Exam> exams = examRepository.findAll(getExamSpecification(examDTO));

		return PropertyUtils.convert(exams, this::getExamDTO);
	}

	public void saveExam(ExamDTO examDTO) {
		/*如果id不为空，则修改考试信息*/
		Exam origin;
		/*如果已经存在考试则得到考试信息*/
		if(Objects.nonNull(examDTO.getId())&& examRepository.existsById(examDTO.getId())) {
			origin = examRepository.getOne(examDTO.getId());
		} else {
			origin = new Exam();
		}
		/*修改考试信息*/
		PropertyUtils.copyNoNullProperties(examDTO,origin);
		/*查询年级信息是否存在*/
		if(!gradeRepository.existsById(examDTO.getGradeId())) {
			throw new GlobalException("年纪不存在");
		}
		Grade grade = gradeRepository.getOne(examDTO.getGradeId());
		origin.setGrade(grade);
		examRepository.save(origin);
	}



	public void deleteExam(Long examId) {
		examRepository.deleteById(examId);
	}


	public PageResult<ExamDTO> getExamScorePage(ExamDTO examDTO, Pageable pageable) {

		Page<Exam> exams = examRepository.findAll(getExamSpecification(examDTO), pageable);

		return PropertyUtils.convert(exams, this::getExamDTO);
	}

	private ExamDTO getExamDTO(Exam u) {
		ExamDTO dto = new ExamDTO();
		PropertyUtils.copyNoNullProperties(u, dto);
		dto.setGradeId(u.getGrade().getId());
		return dto;
	}

	private Specification<Exam> getExamSpecification(ExamDTO examDTO) {
		return (root, query, criteriaBuilder) ->{
			List<Predicate> predicates = new ArrayList<>();

			if(Objects.nonNull(examDTO.getId())) {
				predicates.add(criteriaBuilder.equal(root.get("id"),examDTO.getId()));
			}

			if(Objects.nonNull(examDTO.getGradeId())) {
				predicates.add(criteriaBuilder.equal(root.get("grade").get("id"),examDTO.getGradeId()));
			}

			if(Objects.nonNull(examDTO.getExamTime())) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("examTime"),examDTO.getExamTime()));
			}

			if(Objects.nonNull(examDTO.getTeacherId())) {
				predicates.add(criteriaBuilder.equal(
						root.join("grade")
								.joinSet("courses")
								.joinSet("teachers").get("id")
						,examDTO.getTeacherId()));
			}

			if(Objects.nonNull(examDTO.getStudentId())) {
				predicates.add(criteriaBuilder.equal(
						root.join("grade")
								.joinSet("students")
								.get("id")
						,examDTO.getStudentId()
				));
			}

			query.where(predicates.toArray(new Predicate[predicates.size()]));

			query.orderBy(criteriaBuilder.desc(root.get("examTime")));
			return null;
		};
	}


	public List<CourseScore> getExamScoreList(CourseScore courseScore) {
		return null;
	}


	public void saveExamScore(CourseScore courseScore) {

	}


	public void deleteExamScore(Long id) {

	}
}
