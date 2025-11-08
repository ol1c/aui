package aui.microservices.category_service.category.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Comparator;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "categories")
public class Category implements Comparable<Category>, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private UUID id;
    @Column(name = "category_name", nullable = false)
    private String name;

    @Override
    public int compareTo(Category other) {
        return Comparator.comparing(Category::getName)
                .thenComparing(Category::getId)
                .compare(this, other);
    }
}
