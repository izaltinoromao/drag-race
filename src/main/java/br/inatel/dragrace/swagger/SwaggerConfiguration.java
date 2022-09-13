package br.inatel.dragrace.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final String title = "Race Drag";
    private static final String description = "A application intended to register data from a drag race, and after the race" +
            "finishes calculate and define a winner by the less time spend at the track and a winner based on the final speed at the track";
    private static final String version = "1.0";
    private static final String name = "Izaltino Romão Neto";
    private static final String url = "https://github.com/izaltinoromao/drag-race";
    private static final String email = "izaltino@inatel.br";

    @Bean
    public Docket SwaggerConfig() {
        Contact contact = new Contact(name, url, email);
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.inatel.dragrace"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title(title)
                        .description(description)
                        .version(version)
                        .contact(contact)
                        .build());
    }
}
