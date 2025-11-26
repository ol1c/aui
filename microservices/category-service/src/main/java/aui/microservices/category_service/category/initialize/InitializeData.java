package aui.microservices.category_service.category.initialize;

import aui.microservices.category_service.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitializeData implements InitializingBean {
    private final CategoryService categoryService;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (categoryService.findAll().isEmpty()) {
            categoryService.create(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"), "Drinks");
            categoryService.create(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197"), "Snacks");
        }
    }
}
