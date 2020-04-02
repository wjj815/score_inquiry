package com.wangjj.scoreinquirywxback.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName : Role
 * @Author : wangJJ
 * @Date : 2019/12/28 20:32
 * @Description : 角色表
 */

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user"})
@Table(name="t_role")
@org.hibernate.annotations.Table(appliesTo = "t_role",comment = "角色表")
@Entity
public class Role {
	/** 角色id */
	@Id
	@GeneratedValue
	private Long id ;
	/** 角色名称 */
	private String roleName ;
	/** 角色描述 */
	private String roleDescription ;
	@JsonIgnore
	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
	private final Set<User> user = new HashSet<>(0);

	@ManyToMany(mappedBy = "roleList",fetch = FetchType.LAZY)
	private final Set<Menu> menuList = new HashSet<>(0);

	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
	private final Set<Teacher> teachers = new HashSet<>(0);

	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
	private final Set<Parent> parents = new HashSet<>(0);

	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
	private final Set<Manager> managers = new HashSet<>(0);

}
