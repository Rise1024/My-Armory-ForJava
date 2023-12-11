package com.example.security.jpa;


import com.example.security.data.securityRoleEntity;
import com.example.security.data.securityRoleResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecutiryRoleResourceRepository extends JpaRepository<securityRoleResourceEntity,Integer> {
    @Query("select s.resourceId from securityRoleResourceEntity s where s.roleId in (?1) ")
    List<Integer> findResourceIdByRoleId(List<Integer> roleId);

}
