package aui.lab.entities;

import lombok.*;

import java.util.Comparator;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDTO implements Comparable<ItemDTO> {
    private String id;
    private String name;
    private String price;

    public static ItemDTO from(Item item) {
        return ItemDTO.builder()
                .id(item.getId().toString())
                .name(item.getName())
                .price(item.getPrice().toString())
                .build();
    }

    @Override
    public int compareTo(ItemDTO other) {
        return Comparator.comparing(ItemDTO::getName)
                .thenComparing(ItemDTO::getPrice)
                .thenComparing(ItemDTO::getId)
                .compare(this, other);
    }
}
