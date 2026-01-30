
package com.learning.logback.mapper;


import com.learning.logback.entity.Order;
import com.learning.logback.entity.OrderHistory;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface OrderMapper {
    // 读取待处理订单（Chunk模式，批量读取）
    List<Order> selectPendingOrders();
    // 批量更新订单状态
    int batchUpdateOrderStatus(List<Order> orders);
    // 批量插入订单历史（同步归档）
    int batchInsertOrderHistory(List<OrderHistory> orderHistories);
}