package aui.microservices.item_service.item.domain;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemResponse {
    private UUID id;
    private String name;
    private Double price;
    private UUID categoryId;
    private String categoryName;
}
