package aui.microservices.item_service.item.function;

import aui.microservices.item_service.item.domain.Item;
import aui.microservices.item_service.item.domain.ItemResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ItemToResponseFunction implements Function<Item, ItemResponse> {

    @Override
    public ItemResponse apply(Item entity) {
        return ItemResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .categoryId(entity.getCategory().getId())
                .categoryName(entity.getCategory().getName())
                .build();
    }
}
