package aui.lab.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Comparator;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = "category")
@EqualsAndHashCode(of = "id")
@Entity
@Table(name="items")
public class Item implements Comparable<Item>, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Column(name = "item_name", nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @Override
    public int compareTo(Item other) {
        return Comparator.comparing(Item::getName)
                .thenComparing(Item::getPrice)
                .thenComparing(Item::getId)
                .compare(this, other);
    }
}
