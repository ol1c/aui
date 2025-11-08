package aui.lab.services;

import aui.lab.entities.Category;
import aui.lab.entities.Item;
import aui.lab.repositories.CategoryRepository;
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

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
    }

    public boolean existsById(UUID id) {
        return categoryRepository.existsById(id);
    }

    public long count() {
        return categoryRepository.count();
    }

    @Transactional
    public Category create(String name) {
        Category category = Category.builder()
                .id(UUID.randomUUID())
                .name(name)
                .build();
        return categoryRepository.save(category);
    }

    @Transactional
    public Item createItem(UUID categoryId, String name, Double price) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category not found: " + categoryId));
        Item item = Item.builder()
                .id(UUID.randomUUID())
                .name(name)
                .price(price)
                .build();
        category.addItem(item);
        categoryRepository.save(category);
        return item;
    }

    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public Category update(UUID id, String name) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Category not found: " + id));
        category.setName(name);
        return categoryRepository.save(category);
    }

    @Transactional
    public List<Category> saveAll(Iterable<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    @Transactional
    public void deleteById(UUID id) {
        categoryRepository.deleteById(id);
    }

    @Transactional
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
