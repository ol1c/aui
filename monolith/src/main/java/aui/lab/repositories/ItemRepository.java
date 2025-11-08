package aui.lab.repositories;

import aui.lab.entities.Category;
import aui.lab.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findAllByCategory(Category category);
    List<Item> findAllByCategoryId(UUID categoryId);
}
