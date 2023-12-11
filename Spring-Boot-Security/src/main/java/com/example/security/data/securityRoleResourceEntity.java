package com.example.security.data;

import javax.persistence.*;

/**
 * @Author: DS
 * @Date: 2023/11/20 14:40
 * @Description:
 **/

@Entity
@Table(name = "Role_Resource", schema = "security-test", catalog = "")
public class securityRoleResourceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_resource_id", nullable = false)
    private int roleResourceId;
    @Basic
    @Column(name = "role_id", nullable = false)
    private int roleId;
    @Basic
    @Column(name = "resource_id", nullable = false)
    private int resourceId;

    public int getRoleResourceId() {
        return roleResourceId;
    }

    public void setRoleResourceId(int roleResourceId) {
        this.roleResourceId = roleResourceId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        securityRoleResourceEntity that = (securityRoleResourceEntity) o;

        if (roleResourceId != that.roleResourceId) return false;
        if (roleId != that.roleId) return false;
        if (resourceId != that.resourceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleResourceId;
        result = 31 * result + roleId;
        result = 31 * result + resourceId;
        return result;
    }
}
