package com.thunisoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * spring-boot-seed启动主程序
 * 使用@SpringBootApplication注解最重要的功能有@Configuration、@EnableAutoConfiguration、@ComponentScan三个注解功能
 * 使用@Configuration注解可以将类中的方法作为一个bean注入到Ioc容器中，方法名就是这个bean的name，返回值为这个bean的class，方法需要使用@Bean注解
 * 使用@ComponentScan注解相当于使用<context:component-scan base-package=""/>包扫描，会自动扫描当前类所在的包，因此Application启动类一般放在root package下
 * 使用@EnableAutoConfiguration注解是借助@Import将所有符合自动配置条件bean定义加载到Ioc容器中
 * 使用@MapperScan注解是自动扫描注入mapper接口，如果不使用这个接口，可以在每个Mapper接口上增加@Mapper注解让springboot自动注入接口
 * 使用@EnableCaching注解开启缓存功能
 *
 * @author zhangweijun
 * @date 2018/10/18
 */

@MapperScan(basePackages = "com.thunisoft.dao")
@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class MasterdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterdataApplication.class,args);
    }
}
