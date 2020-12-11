package com.learning.helloworld.nullvalue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.learning.helloworld.nullvalue
 * @Description: User Repository
 * @Author: Sammy
 * @Date: 2020/12/8 16:38
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
