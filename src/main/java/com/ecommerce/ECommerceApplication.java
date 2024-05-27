package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@OpenAPIDefinition(
//		info = @Info(
//				title = "E-commerce Website API Information Services ",
//				description = "E-commerce REST API Documentation",
//				version = "v1",
//				contact = @Contact(
//						name = "Abhishek Kumar",
//						email = "abhithakur291998@gmail.com",
//						url = "http://www.abhitechdecrypt.com"
//				),
//				license = @License(
//						name = "Apache 2.0",
//						url = "http://www.abhitechdecrypt.com"
//				)
//		),
//		externalDocs = @ExternalDocumentation(
//				description =  "E-commerce Website API REST API Documentation",
//				url = "http://www.abhitechdecrypt.com/swagger-ui.html"
//		)
//)
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
