package com.agencia.vousuave.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("API Agência VouSuave").version("v1")
				.description("API Agência VouSuave").contact(contact())
				.termsOfService("https://github.com/git/git-scm.com/blob/main/MIT-LICENSE.txt")
				.license(new License().name("MIT").url("https://choosealicense.com/licenses/mit/")));
	}

	private Contact contact() {
		Contact contact = new Contact();
		contact.setEmail("guilhermao127@gmail.com");
		contact.setName("Guilherme Américo");
		contact.setUrl("https://github.com/GuiAmerico");
		return contact;
	}

}
