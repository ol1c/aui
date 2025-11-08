package aui.microservices.item_service.item.event;

import aui.microservices.item_service.item.domain.SimplifiedCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryEventRepository extends JpaRepository<SimplifiedCategory, UUID> {
}
