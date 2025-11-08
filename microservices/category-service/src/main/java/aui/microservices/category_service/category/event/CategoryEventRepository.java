package aui.microservices.category_service.category.event;

import aui.microservices.category_service.category.domain.Category;
import aui.microservices.category_service.category.domain.CategoryResponse;
import aui.microservices.category_service.category.function.CategoryToResponseFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class CategoryEventRepository {
    private final RestTemplate restTemplate;
    private final CategoryToResponseFunction categoryToResponseFunction;

    public void upsert(Category category) {
        CategoryResponse payload = categoryToResponseFunction.apply(category);
        restTemplate.postForEntity("/api/categories", payload, Void.class);
    }

    public void delete(UUID id) {
        restTemplate.delete("/api/categories/{id}", id);
    }
}
