package aui.lab.entities;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import java.util.Comparator;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryUpdateDTO implements Comparable<CategoryUpdateDTO> {
    @NotBlank private String name;

    @Override
    public int compareTo(CategoryUpdateDTO other) {
        return Comparator.comparing(CategoryUpdateDTO::getName)
                .compare(this, other);
    }
}
