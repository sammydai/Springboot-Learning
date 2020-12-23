package com.learning.zkconsumer.ribbon;

import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * @Package: com.learning.zkconsumer.ribbon
 * @Description: MyRibbonClients
 * @Author: Sammy
 * @Date: 2020/12/21 14:17
 */
@RibbonClients(defaultConfiguration = MyDefaultRibbonConfig.class)
public class MyRibbonClients {
}
