package com.vti.academy.companymanagement.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tblrole")
public class Role {
	@Id
	private Integer id;
	@Column(name = "rolename")
	private String roleName;
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	List<UserRole> listUserRole;
}
