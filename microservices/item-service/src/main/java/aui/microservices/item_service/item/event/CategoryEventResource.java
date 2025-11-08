package aui.microservices.item_service.item.event;

import aui.microservices.item_service.item.service.ItemService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryEventResource {
    private final CategoryEventService categoryService;
    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void upsert(@RequestBody SimplifiedCategoryEvent event) {
        categoryService.save(event.getId(), event.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        itemService.deleteByCategoryId(id);
        categoryService.delete(id);
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    private static class SimplifiedCategoryEvent {
        private UUID id;
        private String name;
    }
}
