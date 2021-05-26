package au.com.xpto.gvendas.gestaovendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    //Swagger documentation: http://localhost:8080/v2/api-docs
    //Swagger UI: http://localhost:8080/swagger-ui.html

    @Bean
    public Docket api(){ //swagger configuration
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.any())
                .build()
                //.pathMapping("/")//pathMapping allows choosing what apis I want to expose.
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        Contact contact = new Contact("Henrique Fernandes", "https://xpto.com.au/about/", "hfernandes@xpto.com.au");

        return new ApiInfoBuilder()
                .title("Sales management")
                .description("Sales management system")
                .version("1.0.0")
                .termsOfServiceUrl("Terms of Service URL: blah")
                .contact(contact)
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .extensions(new ArrayList<>())
                .build();

//        return new ApiInfo(
//                "Sales management",
//                "Sales management system",
//                "1.0.0",
//                "Terms of Service: blah",
//                contact,
//                "Apache License Version 2.0",
//                "https://www.apache.org/licenses/LICENSE-2.0",
//                new ArrayList<>());
    }

}
