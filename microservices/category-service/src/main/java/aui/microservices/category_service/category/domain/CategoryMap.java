package aui.microservices.category_service.category.domain;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryMap {
    @NotBlank
    private String name;
}
