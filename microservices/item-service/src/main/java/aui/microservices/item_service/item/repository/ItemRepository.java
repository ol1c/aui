package aui.microservices.item_service.item.repository;

import aui.microservices.item_service.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findAllByCategoryId(UUID categoryId);
    void deleteByCategoryId(UUID categoryId);
}
