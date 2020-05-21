package com.vsthk.api.app;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by kenlent on 2020/1/7.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class},scanBasePackages = {"com.vsthk.api"} )
@MapperScan({"com.vsthk.api.persistence.mapper"})

@ServletComponentScan
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
//		outputInfo(applicationContext);

        System.out.println("API 服务启动完成...");
    }
}
