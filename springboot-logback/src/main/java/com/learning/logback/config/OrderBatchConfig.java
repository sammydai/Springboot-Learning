package com.learning.logback.config;

import com.learning.logback.entity.Order;
import com.learning.logback.entity.OrderHistory;
import com.learning.logback.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringBatch核心配置：
 * 1. Reader：批量读取待处理订单（Chunk模式，每次读100条）
 * 2. Processor：处理订单业务逻辑（计算实际金额、校验库存）
 * 3. Writer：批量更新订单状态 + 同步到历史表
 * 4. Step/Job：极简串联，无复杂Flow
 */
@Configuration
@Slf4j
public class OrderBatchConfig {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory; // 新增：注入SqlSessionFactory


    // ====================== 1. 读取待处理订单（Reader） ======================
    @Bean
    public ItemReader<Order> orderReader() {
        log.info("读取待处理订单（Reader)");
        MyBatisPagingItemReader<Order> reader = new MyBatisPagingItemReader<>();
        reader.setSqlSessionFactory(sqlSessionFactory);
        reader.setQueryId("com.learning.logback.mapper.OrderMapper.selectPendingOrders");
        reader.setPageSize(100);
        return reader;
    }

    // ====================== 2. 处理订单业务逻辑（Processor） ======================
    @Bean
    public ItemProcessor<Order, Order> orderProcessor() {
        log.info("2. 处理订单业务逻辑（Processor）");
        return order -> {
            try {
                // 1. 标记订单为处理中
                order.setStatus("PROCESSING");
                // 2. 模拟业务逻辑：校验库存（实际场景可调用库存服务）
                boolean stockValid = checkStock(order.getOrderNo());
                if (!stockValid) {
                    order.setStatus("FAILED");
                    return order;
                }
                // 3. 计算实际支付金额（比如95折优惠）
                BigDecimal actualAmount = order.getAmount().multiply(new BigDecimal("0.95"));
                order.setActualAmount(actualAmount);
                // 4. 标记订单为成功
                order.setStatus("SUCCESS");
                return order;
            } catch (Exception e) {
                // 异常时标记为失败
                order.setStatus("FAILED");
                return order;
            }
        };
    }

    // 模拟库存校验
    private boolean checkStock(String orderNo) {
        // 实际场景：调用库存服务，根据订单号查库存
        return true; // 简化：默认库存充足
    }

    // ====================== 3. 批量写入（更新订单+同步历史表） ======================

    @Bean
    public ItemWriter<Order> orderWriter() {
        log.info(" 3. 批量写入（更新订单+同步历史表）");
        return orders -> {
            // 1. 批量更新订单状态（核心批处理，效率高）
            orderMapper.batchUpdateOrderStatus((List<Order>) orders);
            // 2. 同步到订单历史表（数据归档）
            List<OrderHistory> histories = orders.stream().map(order -> {
                OrderHistory history = new OrderHistory();
                history.setOrderId(order.getId());
                history.setOrderNo(order.getOrderNo());
                history.setUserId(order.getUserId());
                history.setAmount(order.getAmount());
                history.setActualAmount(order.getActualAmount());
                history.setStatus(order.getStatus());
                return history;
            }).collect(Collectors.toList()); // 替换toList()为这个
            orderMapper.batchInsertOrderHistory(histories);
        };
    }

    // ====================== 4. 兜底清理Step（无论成败都执行） ======================
    @Bean
    public Step cleanStep() {
        return stepBuilderFactory.get("cleanStep")
                .tasklet((contribution, chunkContext) -> {
                    // 模拟清理逻辑：清理库存锁定缓存、临时文件等
                    log.info("【兜底清理】清理订单处理临时缓存");
                    return org.springframework.batch.repeat.RepeatStatus.FINISHED;
                })
                .build();
    }

    // ====================== 5. 核心Step（读取→处理→写入） ======================
    @Bean
    public Step processOrderStep() {
        return stepBuilderFactory.get("processOrderStep")
                .<Order, Order>chunk(100) // Chunk大小：每100条提交一次事务
                .reader(orderReader())
                .processor(orderProcessor())
                .writer(orderWriter())
                .faultTolerant() // 开启容错（Chunk模式支持）
                .skip(Exception.class) // 跳过单个订单的异常，不影响整体批处理
                .skipLimit(10) // 最多跳过10条异常订单
                .build();
    }

    // ====================== 6. 核心Job（极简串联：处理Step → 清理Step） ======================
    @Bean
    public Job processOrderJob() {
        return jobBuilderFactory.get("processOrderJob")
                .incrementer(new RunIdIncrementer()) // 每次执行生成唯一ID
                .start(processOrderStep()) // 第一步：处理订单
                .next(cleanStep()) // 第二步：兜底清理（无论Step1成败都执行）
                .build();
    }
}