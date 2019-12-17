package com.koby5i.wh.domain;


import com.koby5i.wh.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@Configuration
// from swagger
@EnableSwagger2
// from lombok
@RequiredArgsConstructor

public class ItemConfig {

    private static final String BASE_PACKAGE = "com.koby5i.wh.controller";

    private final ItemRepository itemRepository;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .build();
    }

    @PostConstruct
    public void init() {
        Item item = Item.builder()
                .name("car")
                .description("saab 95")
                .qty(20)
                .price(20000)
                .build();
        itemRepository.save(item);
    }

}

