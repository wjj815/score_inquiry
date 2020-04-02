package com.wangjj.scoreinquirywxback.pojo.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @ClassName : Manager
 * @Author : wangJJ
 * @Date : 2019/12/25 16:11
 * @Description :  管理员
 */
@ToString
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_manager")
@org.hibernate.annotations.Table(appliesTo = "t_manager",comment = "管理员信息表")
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/** 管理员姓名 */
	private String managerName ;
	/** 管理员电话 */
	private String mobilePhone ;
	/**角色*/
	@ManyToOne(fetch = FetchType.LAZY)
	private Role role;

}
