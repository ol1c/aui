package aui.microservices.item_service.item.initialize;

import aui.microservices.item_service.item.domain.Item;
import aui.microservices.item_service.item.event.CategoryEventService;
import aui.microservices.item_service.item.event.SimplifiedCategory;
import aui.microservices.item_service.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitializeData implements InitializingBean {
    private final ItemService itemService;
    private final CategoryEventService categoryService;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (itemService.findAll().isEmpty()) {
            categoryService.save(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"), "Drinks");
            categoryService.save(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197"), "Snacks");

            itemService.create("Water", 0.99, UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"));
            itemService.create("Juice", 1.99, UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"));
            itemService.create("Crisps", 4.49, UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197"));
            itemService.create("Candy bar", 2.49, UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197"));
        }
    }
}
