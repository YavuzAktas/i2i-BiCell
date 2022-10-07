package org.bicell.rest.api.swagger;

import org.springframework.context.annotation.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.*;
import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@Profile({"!prod"})
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.bicell.rest.api"))
                .paths(regex("/.*"))
                .build().apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo(){
        return new ApiInfoBuilder().title("i2i Rest Api Swagger")
                .description("Rest Api Documentation")
                .contact(new Contact("Anıl Can Özgök", "","anil.can.ozgok@ext.i2i-systems.com"))
                .license("Apache 2.0")
                .build();
    }

}
