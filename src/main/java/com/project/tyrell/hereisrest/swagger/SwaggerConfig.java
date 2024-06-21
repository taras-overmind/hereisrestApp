package com.project.tyrell.hereisrest.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@Configuration
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(
        title = "єВідпочинок API"))
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi customOpenAPI() {
        return GroupedOpenApi.builder()
                .group("tyrell")
                .addOpenApiCustomizer(sortEndpointsByUrl())
                .build();
    }

    public OpenApiCustomizer sortEndpointsByUrl() {
        return openApi -> {
            Paths paths = openApi.getPaths().entrySet()
                    .stream()
                    .sorted(Comparator.comparing(
                                    (Map.Entry<String, PathItem> entry) -> getOperationTag(entry.getValue()))
                            .thenComparing(Map.Entry::getKey))
                    .collect(Paths::new, (map, item) -> map.addPathItem(item.getKey(), item.getValue()), Paths::putAll);

            openApi.setPaths(paths);
        };
    }

    private String getOperationTag(final PathItem pathItem) {
        return Stream.of(pathItem.getGet(), pathItem.getPost(), pathItem.getDelete(),
                        pathItem.getPut(), pathItem.getPatch())
                .filter(Objects::nonNull)
                .map(Operation::getTags)
                .flatMap(java.util.Collection::stream)
                .findFirst()
                .orElse("");
    }

}
