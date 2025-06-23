package com.zdata.zdata_assignment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ZData API", version = "v1"))
public class ZdataAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZdataAssignmentApplication.class, args);
	}

}
