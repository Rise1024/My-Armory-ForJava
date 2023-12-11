package com.example.security.jpa;


import com.example.security.SpringBootBaseTest;
import com.example.security.data.*;
import com.example.security.service.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DataInitTest extends SpringBootBaseTest {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RoleResourceService roleResourceService;

    @Test
    public void initTest() {
        //创建角色
        securityRoleEntity role = createRole(true);
        securityRoleEntity role1 = createRole(false);

        //创建资源
        securityResourceEntity guser = createResource("GET:/v1/user", "获取user", "1");
        securityResourceEntity uuser = createResource("PUT:/v1/user", "修改user", "1");
        securityResourceEntity duser = createResource("DELETE:/v1/user", "删除user", "1");

        //创建角色资源关联
        createRoleResourc(role.getRoleId(),guser.getResourceId());
        createRoleResourc(role.getRoleId(),uuser.getResourceId());
        createRoleResourc(role.getRoleId(),duser.getResourceId());
        createRoleResourc(role1.getRoleId(),guser.getResourceId());

        //创建用户
        createUser(role.getRoleId(),"ds","ds");
        createUser(role.getRoleId(),"zhangsan","zhangsan");
        createUser(role1.getRoleId(),"lisi","lisi");



    }


    securityRoleEntity createRole(boolean isadmin) {
        securityRoleEntity securityRoleEntity = new securityRoleEntity();
        securityRoleEntity.setRoleName(isadmin ? "admin" : "agent");
        securityRoleEntity.setDescription(isadmin ? "超级管理员" : "普通用户");
        return roleService.save(securityRoleEntity);
    }

    void createUser(Integer roleId,String setUsername,String setPassword) {
        securityUserEntity securityUserEntity = new securityUserEntity();
        securityUserEntity.setUsername(setUsername);
        securityUserEntity.setPassword(bCryptPasswordEncoder.encode(setPassword));
        com.example.security.data.securityUserEntity save = userService.save(securityUserEntity);
        securityUserRoleEntity securityUserRoleEntity = new securityUserRoleEntity();
        securityUserRoleEntity.setRoleId(roleId);
        securityUserRoleEntity.setUserId(save.getUserId());
        userRoleService.save(securityUserRoleEntity);
    }

    securityResourceEntity createResource(String setResourcePath, String setResourceName, String setResourceType) {
        securityResourceEntity securityResourceEntity = new securityResourceEntity();
        securityResourceEntity.setResourcePath(setResourcePath);
        securityResourceEntity.setResourceName(setResourceName);
        securityResourceEntity.setResourceType(setResourceType);
        return resourceService.save(securityResourceEntity);
    }


    void createRoleResourc(int roleId, int resourceId) {
        securityRoleResourceEntity securityRoleResourceEntity = new securityRoleResourceEntity();
        securityRoleResourceEntity.setResourceId(resourceId);
        securityRoleResourceEntity.setRoleId(roleId);
        roleResourceService.save(securityRoleResourceEntity);
    }


}
