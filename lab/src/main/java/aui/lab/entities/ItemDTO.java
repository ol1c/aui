package aui.lab.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private String id;
    private String name;
    private String price;
    private String amount;
    private String categoryName;

    public static ItemDTO from(Item item) {
        return ItemDTO.builder()
                .id(item.getId().toString())
                .name(item.getName())
                .price(item.getPrice().toString())
                .categoryName(
                        item.getCategory() != null ? item.getCategory().getName() : null)
                .build();
    }
}
