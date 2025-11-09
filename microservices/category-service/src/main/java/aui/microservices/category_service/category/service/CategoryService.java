package aui.microservices.category_service.category.service;

import aui.microservices.category_service.category.domain.Category;
import aui.microservices.category_service.category.event.CategoryEventRepository;
import aui.microservices.category_service.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryEventRepository categoryEventRepository;

    public List<Category> findAll() { return categoryRepository.findAll(); }
    public Optional<Category> findById(UUID id) { return categoryRepository.findById(id); }

    @Transactional
    public Category create(String name) {
        Category category = Category.builder()
                .id(UUID.randomUUID())
                .name(name)
                .build();
        Category saved = categoryRepository.save(category);
        categoryEventRepository.upsert(saved);
        return saved;
    }

    @Transactional
    public Category create(UUID id, String name) {
        Category category = Category.builder()
                .id(id)
                .name(name)
                .build();
        Category saved = categoryRepository.save(category);
        categoryEventRepository.upsert(saved);
        return saved;
    }

    @Transactional
    public Category update(UUID id, String name) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Category not found: " + id)
        );
        category.setName(name);
        Category updated = categoryRepository.save(category);
        categoryEventRepository.upsert(updated);
        return updated;
    }

    @Transactional
    public void delete(UUID id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            categoryEventRepository.delete(id);
        }
    }
}
