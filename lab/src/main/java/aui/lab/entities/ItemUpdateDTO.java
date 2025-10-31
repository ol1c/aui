package aui.lab.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Comparator;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemUpdateDTO implements Comparable<ItemUpdateDTO> {
    @NotBlank
    private String name;
    @NotNull
    @Positive
    private Double price;

    @Override
    public int compareTo(ItemUpdateDTO other) {
        return Comparator.comparing(ItemUpdateDTO::getName)
                .thenComparing(ItemUpdateDTO::getPrice)
                .compare(this, other);
    }
}
