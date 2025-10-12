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

    @Column(name="category_name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Item> items;

    @Override
    public int compareTo(Category other) {
        return Comparator.comparing(Category::getName)
                .thenComparing(Category::getId)
                .compare(this, other);
    }

    public void addItem(Item item) {

    }
}
