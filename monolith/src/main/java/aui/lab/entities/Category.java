package aui.lab.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = "items")
@EqualsAndHashCode(of = "id")
@Entity
@Table(name="categories")
public class Category implements Comparable<Category>, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Column(name = "category_name", nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<Item> items = new ArrayList<>();

    @Override
    public int compareTo(Category other) {
        return Comparator.comparing(Category::getName)
                .thenComparing(Category::getId)
                .compare(this, other);
    }

    public void addItem(Item item) {
        if (item != null) {
            item.setCategory(this);
            this.items.add(item);
        }
    }

    public void removeItem(Item item) {
        if (item != null) {
            item.setCategory(null);
            this.items.remove(item);
        }
    }
}
