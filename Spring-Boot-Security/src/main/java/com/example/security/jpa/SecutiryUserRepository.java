package com.example.security.jpa;


import com.example.security.data.securityUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecutiryUserRepository extends JpaRepository<securityUserEntity,Integer> {
    securityUserEntity findByUsername(String name);
}
