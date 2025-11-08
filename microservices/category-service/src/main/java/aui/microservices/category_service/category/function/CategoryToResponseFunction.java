package aui.microservices.category_service.category.function;

import aui.microservices.category_service.category.domain.Category;
import aui.microservices.category_service.category.domain.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CategoryToResponseFunction implements Function<Category, CategoryResponse> {

    @Override
    public CategoryResponse apply(Category entity) {
        return CategoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
