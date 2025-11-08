package aui.microservices.item_service.item.event;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SimplifiedCategory {
    @Id
    private UUID id;
    private String name;
}
