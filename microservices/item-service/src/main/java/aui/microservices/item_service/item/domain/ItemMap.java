package aui.microservices.item_service.item.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemMap {
    @NotBlank
    private String name;
    @NotBlank @Positive
    private Double price;
    private String categoryId;
}
