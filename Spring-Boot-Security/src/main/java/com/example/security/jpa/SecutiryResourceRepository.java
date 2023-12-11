package com.example.security.jpa;


import com.example.security.data.securityResourceEntity;
import com.example.security.data.securityRoleResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecutiryResourceRepository extends JpaRepository<securityResourceEntity,Integer> {

}
