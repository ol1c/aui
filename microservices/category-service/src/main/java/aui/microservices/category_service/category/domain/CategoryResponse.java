package aui.microservices.category_service.category.domain;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryResponse {
    private UUID id;
    private String name;
}
