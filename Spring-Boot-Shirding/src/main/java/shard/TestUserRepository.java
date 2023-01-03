package com.easemob.weichat.shard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zds
 * @Description
 * @createTime 2022/7/8 14:30
 */
@Repository
public interface TestUserRepository extends JpaRepository<TestUser, Long> {


}
