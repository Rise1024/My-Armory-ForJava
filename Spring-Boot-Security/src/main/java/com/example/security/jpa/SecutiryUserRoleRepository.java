package com.example.security.jpa;


import com.example.security.data.securityUserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecutiryUserRoleRepository extends JpaRepository<securityUserRoleEntity,Integer> {
       List<securityUserRoleEntity> findByUserId(Integer userId);

       @Query("select s.roleId from securityUserRoleEntity s where s.userId=?1")
       List<Integer> findRoleIdByUserId(Integer userId);

}
