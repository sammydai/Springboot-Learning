package com.dwt.springbootanotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @Package: com.dwt.springbootanotation.bean
 * @Description:
 * @Author: Sammy
 * @Date: 2020/3/22 22:02
 */

@Configuration
public class ApplicationContextTestResourceNameType {


    @Bean(name="defaultFile")
    public File defaultFile() {
        File namedFile = new File("defaultFile.txt");
        return namedFile;
    }
    @Bean(name="namedFile")
    public File namedFile() {
        File namedFile = new File("namedFile.txt");
        return namedFile;
    }
}
