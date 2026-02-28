package com.dwt.redis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConfigDiagnosticTest {

    @Autowired
    private Environment environment;
    
    @Autowired(required = false)
    private DataSource dataSource;
    
    @Test
    void diagnoseConfigurations() throws Exception {
        System.out.println("=== 配置诊断开始 ===");
        
        // 1. 检查激活的Profiles
        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println("激活的Profiles: " + String.join(", ", activeProfiles));
        if (activeProfiles.length == 0) {
            System.out.println("⚠️  没有激活的Profile，使用default配置");
        }
        
        // 2. 检查数据库配置
        String dbUrl = environment.getProperty("spring.datasource.url");
        String dbDriver = environment.getProperty("spring.datasource.driver-class-name");
        System.out.println("数据库URL: " + dbUrl);
        System.out.println("数据库驱动: " + dbDriver);
        
        // 3. 检查配置源
        System.out.println("\n=== 配置源检查 ===");
        System.out.println("测试配置位置: src/test/resources/application.yml");
        System.out.println("主配置位置: src/main/resources/application.yml");
        
        // 4. 检查H2是否在classpath中
        try {
            Class.forName("org.h2.Driver");
            System.out.println("✅ H2驱动在classpath中");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ H2驱动不在classpath中");
        }
        
        // 5. 如果有数据源，检查实际连接
        if (dataSource != null) {
            try (Connection conn = dataSource.getConnection()) {
                DatabaseMetaData metaData = conn.getMetaData();
                System.out.println("\n=== 实际数据源信息 ===");
                System.out.println("数据库产品: " + metaData.getDatabaseProductName());
                System.out.println("数据库版本: " + metaData.getDatabaseProductVersion());
                System.out.println("连接URL: " + metaData.getURL());
                
                // 判断是H2还是MySQL
                String productName = metaData.getDatabaseProductName();
                if ("H2".equalsIgnoreCase(productName)) {
                    System.out.println("⚠️  警告：正在使用H2内存数据库");
                    System.out.println("可能原因：");
                    System.out.println("1. test/resources/application.yml 配置未生效");
                    System.out.println("2. 主配置中有H2配置");
                    System.out.println("3. 依赖冲突");
                } else if (productName.contains("MySQL")) {
                    System.out.println("✅ 正在使用MySQL数据库");
                }
            }
        } else {
            System.out.println("❌ 没有找到数据源Bean");
        }
        
        System.out.println("=== 配置诊断结束 ===");
    }
    
    @Test
    void checkPropertySources() {
        System.out.println("\n=== 属性源顺序 ===");
        // Spring Boot配置加载顺序
        System.out.println("1. 测试目录的 @TestPropertySource 注解");
        System.out.println("2. 测试目录的 application.properties/yml");
        System.out.println("3. 随机端口属性");
        System.out.println("4. 主目录的 application.properties/yml");
        System.out.println("5. 默认配置");
    }
}