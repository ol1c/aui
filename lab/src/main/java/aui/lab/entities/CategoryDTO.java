package aui.lab.entities;

import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDTO implements Comparable<CategoryDTO> {
    private String id;
    private String name;
    private List<String> items;

    public static CategoryDTO from(Category category) {
        return CategoryDTO.builder()
                .id(category.getId().toString())
                .name(category.getName())
                .items(Optional.ofNullable(category.getItems())
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .map(Item::getName)
                        .toList())
                .build();
    }

    @Override
    public int compareTo(CategoryDTO other) {
        return Comparator.comparing(CategoryDTO::getName)
                .thenComparing(CategoryDTO::getId)
                .compare(this, other);
    }
}
