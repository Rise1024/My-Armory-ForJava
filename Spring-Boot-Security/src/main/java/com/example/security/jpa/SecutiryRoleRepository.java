package com.example.security.jpa;


import com.example.security.data.securityRoleEntity;
import com.example.security.data.securityUserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecutiryRoleRepository extends JpaRepository<securityRoleEntity,Integer> {
}
