package com.learning.security.storepassword;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Package: com.learning.security.storepassword
 * @Description: User Data
 * @Author: Sammy
 * @Date: 2021/1/18 10:46
 */
@Data
@Entity
public class UserData {
	@Id
    private Long id;
    private String name;
    private String salt;
    private String password;
}
