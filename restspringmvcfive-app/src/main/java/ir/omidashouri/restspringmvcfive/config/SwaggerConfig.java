package ir.omidashouri.restspringmvcfive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig { //extends WebMvcConfigurationSupport {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .tags(new Tag("Customer-Controller", "Api annotation: This is Customer Controller"))
                .apiInfo(metaData());
    }

    private ApiInfo metaData(){

        Contact contact = new Contact("Omid Ashouri","http://omidashouri.ir","omidashouri@gmail.com");

        return new ApiInfo(
                "Spring MVC Restful Web Service",
                "Spring Frame work Multi module",
                "1.0",
                "Terms of Service: blah",
                contact,
                "Apache  License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }

//    if http://localhost:8080/swagger-ui.html is not showing use the
//    following commands with extends WebMvcConfigurationSupport for class

/*    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }*/
}
