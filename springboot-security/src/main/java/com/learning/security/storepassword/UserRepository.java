package com.learning.security.storepassword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.learning.security.storepassword
 * @Description: User Repository
 * @Author: Sammy
 * @Date: 2021/1/18 10:49
 */
@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {

}
