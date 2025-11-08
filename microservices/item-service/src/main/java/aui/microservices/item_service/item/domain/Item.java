package aui.microservices.item_service.item.domain;

import aui.microservices.item_service.item.event.SimplifiedCategory;
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
@Table(name = "items")
public class Item implements Comparable<Item>, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private SimplifiedCategory category;

    @Override
    public int compareTo(Item other) {
        return Comparator.comparing(Item::getName)
                .thenComparing(Item::getPrice)
                .thenComparing(Item::getId)
                .compare(this, other);
    }
}
