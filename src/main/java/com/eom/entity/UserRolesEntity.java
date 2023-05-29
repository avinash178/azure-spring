package com.eom.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRolesEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long roleId;

	private String roleName;


	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "UserRolesEntity [roleId=" + roleId + ", roleName=" + roleName + "]";
	}

	
	
}
