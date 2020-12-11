package com.learning.helloworld.nullvalue;

import lombok.Data;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @Package: com.learning.helloworld.nullvalue
 * @Description: User Entity
 * @Author: Sammy
 * @Date: 2020/12/9 07:53
 */
@Entity
@Data
@DynamicUpdate
public class UserEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String nickname;

	@Column(nullable = false)
	private Integer age;

	@Column(nullable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createDate;
}
