package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.constant.UserType;
import com.wangjj.scoreinquirywxback.dao.RoleRepository;
import com.wangjj.scoreinquirywxback.dao.UserRepository;
import com.wangjj.scoreinquirywxback.pojo.dto.ParentDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.UserDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.request.WXLoginParameter;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.pojo.entity.Parent;
import com.wangjj.scoreinquirywxback.pojo.entity.Teacher;
import com.wangjj.scoreinquirywxback.pojo.entity.User;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.pojo.dto.request.LoginParameter;
import com.wangjj.scoreinquirywxback.util.IdGenerator;
import com.wangjj.scoreinquirywxback.util.ParameterUtils;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName : UserServiceImpl
 * @Author : wangJJ
 * @Date : 2020/1/2 16:59
 * @Description : 用户业务处理
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ParentService parentService;


	public UserDTO findByLoginParameter(LoginParameter loginParameter) {

		User user = userRepository.findByAccountAndPassword(loginParameter.getAccount(), loginParameter.getPassword());
		if(Objects.isNull(user)) {
			throw new GlobalException("用户名或密码错误！");
		}
		return getUserDTO(user);
	}

	public UserDTO findByWxLoginParameter(WXLoginParameter wxLoginParameter) {
		User user = userRepository.findByWxOpenId(wxLoginParameter.getWxOpenId());
		if(Objects.isNull(user)) {
			throw new GlobalException("不存在该微信用户");
		}
		return getUserDTO(user);
	}

	private UserDTO getUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		PropertyUtils.copyNoNullProperties(user,userDTO);
		userDTO.setRoleId(user.getRole().getId());
		return userDTO;
	}

	@Transactional
	public void addTeacherUser(Teacher teacher) {
		User user = new User();
		user.setId(teacher.getId());
		user.setAccount(teacher.getPhone()+"");
		user.setPassword("123456");
//		user.setInfoId(teacher.getId());
		user.setRole(teacher.getRole());
		user.setName(teacher.getTeacherName());
		user.setPhone(teacher.getPhone());
		userRepository.save(user);
	}

	@Transactional
	public ParentDTO addParentUser(ParentDTO parentDTO) {
		Long id = IdGenerator.generateId();
		parentDTO.setId(id);
		Parent parent = parentService.saveParent(parentDTO);
		User user = new User();
		user.setId(id);
//		user.setInfoId(id);
		user.setRole(parent.getRole());
		user.setName(parent.getName());
		user.setPhone(parent.getPhone());
		user.setWxOpenId(parentDTO.getWxOpenId());
		user.setAvatar(parentDTO.getAvatar());
		userRepository.save(user);
		return parentService.getParentDTO(parent);
	}


	/**
	 *获得用户列表
	 * @param userDTO 用户查询参数
	 * @return 用户列表
	 */
	public List<UserDTO> getUserList(UserDTO userDTO) {
		List<User> users = userRepository.findAll(getUserSpecification(userDTO));
		return PropertyUtils.convert(users, this::getUserDTO);
	}

	/**
	 * 分页获取用户列表
	 * @param userDTO
	 * @return
	 */
	public PageResult<UserDTO> getUserPage(UserDTO userDTO, Pageable pageable) {
		Page<User> users = userRepository.findAll(getUserSpecification(userDTO), pageable);
		return PropertyUtils.convert(users,this::getUserDTO);
	}

	private Specification<User> getUserSpecification(UserDTO userDTO) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if(Objects.nonNull(userDTO.getId())) {
				predicates.add(criteriaBuilder.equal(root.get("id"),userDTO.getId()));
			}

			if(Objects.nonNull(userDTO.getRoleId())) {
				predicates.add(criteriaBuilder.equal(root.get("role").get("id"),userDTO.getRoleId()));
			}
			query.where(predicates.toArray(new Predicate[predicates.size()]));
			return null;
		};
	}


	@Transactional
	public void addParentUser(User user,Parent parent) {
		user.setRole(parent.getRole());
	}

	public User findById(Long id) {
		return userRepository.getOne(id);
	}
}
