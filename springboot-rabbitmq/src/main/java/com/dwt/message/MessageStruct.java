package com.dwt.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import java.io.Serializable;

/**
 * @Package: com.dwt.message
 * @Description:
 * @Author: Sammy
 * @Date: 2020/5/17 00:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageStruct implements Serializable{

	private static final long serialVersionUID = 9187763923976788544L;

	private String message;
}
