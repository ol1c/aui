package aui.lab.services;

import aui.lab.entities.Category;
import aui.lab.entities.Item;
import aui.lab.repositories.ItemRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicMarkableReference;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> findById(UUID id) {
        return itemRepository.findById(id);
    }

    public boolean existsById(UUID id) {
        return itemRepository.existsById(id);
    }

    public long count() {
        return itemRepository.count();
    }

    public List<Item> findAllByCategory(Category category) {
        return itemRepository.findAllByCategory(category);
    }

    public List<Item> findAllByCategoryId(UUID categoryId) {
        return itemRepository.findAllByCategoryId(categoryId);
    }

    @Transactional
    public Item create(UUID categoryId, String name, Double price) {
        Category categoryRef = entityManager.getReference(Category.class, categoryId);
        Item item = Item.builder()
                .id(UUID.randomUUID())
                .name(name)
                .price(price)
                .category(categoryRef)
                .build();
        return itemRepository.save(item);
    }

    @Transactional
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Transactional
    public Item save(Item item, UUID newCategoryId) {
        Category oldCategory = itemRepository.findById(item.getId()).get().getCategory();
        oldCategory.removeItem(item);
        Category newCategoryRef = entityManager.getReference(Category.class, newCategoryId);
        if (newCategoryRef == null) {
            throw new NoSuchElementException();
        }
        newCategoryRef.addItem(item);
        return itemRepository.save(item);
    }

    @Transactional
    public List<Item> saveAll(Iterable<Item> items) {
        return itemRepository.saveAll(items);
    }

    @Transactional
    public void deleteById(UUID id) {
        itemRepository.deleteById(id);
    }

    @Transactional
    public void delete(Item item) {
        itemRepository.delete(item);
    }
}
