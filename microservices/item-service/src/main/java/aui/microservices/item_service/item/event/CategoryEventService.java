package aui.microservices.item_service.item.event;

import aui.microservices.item_service.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryEventService {
    private final CategoryEventRepository categoryRepository;

    public List<SimplifiedCategory> findAll() { return categoryRepository.findAll(); }

    @Transactional
    public void save(UUID id, String name) {
        SimplifiedCategory category = categoryRepository.findById(id)
                .orElse(
                SimplifiedCategory.builder()
                        .id(id)
                        .name(name)
                        .build()
        );
        category.setName(name);
        SimplifiedCategory updated = categoryRepository.save(category);
    }

    @Transactional
    public void delete(UUID id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }
    }
}
