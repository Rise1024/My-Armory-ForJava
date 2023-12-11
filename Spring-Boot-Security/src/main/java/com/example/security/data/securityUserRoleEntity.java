package com.example.security.data;

import javax.persistence.*;

/**
 * @Author: DS
 * @Date: 2023/11/20 14:40
 * @Description:
 **/

@Entity
@Table(name = "User_Role", schema = "security-test", catalog = "")
public class securityUserRoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_role_id", nullable = false)
    private int userRoleId;
    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "role_id", nullable = false)
    private int roleId;

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        securityUserRoleEntity that = (securityUserRoleEntity) o;

        if (userRoleId != that.userRoleId) return false;
        if (userId != that.userId) return false;
        if (roleId != that.roleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userRoleId;
        result = 31 * result + userId;
        result = 31 * result + roleId;
        return result;
    }
}
