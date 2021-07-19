package com.venkat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class StudentcrudoperationApplication{

	public static void main(String[] args) {
		SpringApplication.run(StudentcrudoperationApplication.class, args);
	}


}
