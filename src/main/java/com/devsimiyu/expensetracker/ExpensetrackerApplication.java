package com.devsimiyu.expensetracker;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ExpensetrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensetrackerApplication.class, args);
	}

	@Bean
    public Docket swaggerOpenApi() {
        ApiInfo apiInfo = new ApiInfo(
            "Expense Tracker API", 
            "Track expenses application", 
            "1.0.0", 
            "Free to use", 
            new Contact(
                "Rodgers Wanyonyi", 
                "https://www.linkedin.com/in/rodgers-wanyonyi-9510a61a4/", 
                "dev.wrodgerss@gmail.com"
            ),
            "MIT License", 
            "https://github.com/devsimiyu/expense-tracker",
            Collections.emptyList()
        );
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.devsimiyu.expensetracker"))
                .build()
                .apiInfo(apiInfo);

        return docket;
    }
}
