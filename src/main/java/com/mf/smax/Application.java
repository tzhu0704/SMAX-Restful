package com.mf.smax;


import com.mf.integration.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;



@EnableScheduling
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class Application  {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
		logger.info("SMAX集成服务项目启动");
		Constants.Initialize();

	}
}