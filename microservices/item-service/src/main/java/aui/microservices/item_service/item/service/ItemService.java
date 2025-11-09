package aui.microservices.item_service.item.service;

import aui.microservices.item_service.item.domain.Item;
import aui.microservices.item_service.item.event.SimplifiedCategory;
import aui.microservices.item_service.item.repository.ItemRepository;
import aui.microservices.item_service.item.event.CategoryEventRepository;
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
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryEventRepository categoryRepository;

    public List<Item> findAll() { return itemRepository.findAll(); }
    public Optional<Item> findById(UUID id) { return itemRepository.findById(id); }
    public List<Item> findAllByCategoryId(UUID categoryId) { return itemRepository.findAllByCategoryId(categoryId); }

    @Transactional
    public Item create(String name, Double price, UUID categoryId) {
        SimplifiedCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException("Category not found: " + categoryId));
        Item item = Item.builder()
                .id(UUID.randomUUID())
                .name(name)
                .price(price)
                .category(category)
                .build();
        return itemRepository.save(item);
    }

    @Transactional
    public Item update(UUID id, String name, Double price, UUID newCategoryId) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Item not found: " + id));
        item.setName(name);
        item.setPrice(price);
        if (newCategoryId != null && !newCategoryId.equals(item.getCategory().getId())) {
            SimplifiedCategory newCategory = categoryRepository.findById(newCategoryId)
                    .orElseThrow(() -> new NoSuchElementException("Category not found: " + newCategoryId));
            item.setCategory(newCategory);
        }
        return itemRepository.save(item);
    }

    @Transactional
    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }

    @Transactional
    public void deleteByCategoryId(UUID categoryId) {
        itemRepository.deleteByCategoryId(categoryId);
    }
}
