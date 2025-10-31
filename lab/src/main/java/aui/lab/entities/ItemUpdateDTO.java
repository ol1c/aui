package aui.lab.entities;

import lombok.*;

import java.util.Comparator;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemUpdateDTO implements Comparable<ItemUpdateDTO> {
    private String name;
    private Double price;

    @Override
    public int compareTo(ItemUpdateDTO other) {
        return Comparator.comparing(ItemUpdateDTO::getName)
                .thenComparing(ItemUpdateDTO::getPrice)
                .compare(this, other);
    }
}
