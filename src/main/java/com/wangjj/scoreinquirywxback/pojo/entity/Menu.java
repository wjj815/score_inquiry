package com.wangjj.scoreinquirywxback.pojo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName : Menu
 * @Author : wangJJ
 * @Date : 2020/1/7 13:48
 * @Description : 菜单实体类对象
 */
@ToString(exclude = {"roleList","parentMenu"})
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_menu",indexes = {@Index(
		unique = true,name = "menu_code",columnList = "menuCode"
)})
public class Menu {

	/*菜单id*/
	@Id
	/*@GeneratedValue(strategy = GenerationType.TABLE)*/
	private Long id;
	/*菜单名字*/
	private String menuName;
	/*菜单编码*/
	private String menuCode;
	/*菜单url*/
	private String menuUrl;
	/*菜单图标*/
	private String menuIcon;
	/*菜单顺序*/
	private Integer menuOrder;
	/*一个子菜单只能对应一个父菜单*/
	@OneToMany(mappedBy = "parentMenu",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private final Set<Menu> childMenu = new HashSet<>(0);
	/*父菜单*/
	@ManyToOne(fetch = FetchType.LAZY)
	private Menu parentMenu;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_role_menu",
			joinColumns = @JoinColumn(name = "menu_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
	private final Set<Role> roleList = new HashSet<>(0);
}
