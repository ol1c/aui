package aui.lab.resources;

import aui.lab.entities.CategoryDTO;
import aui.lab.entities.Item;
import aui.lab.entities.ItemDTO;
import aui.lab.entities.ItemUpdateDTO;
import aui.lab.services.CategoryService;
import aui.lab.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ItemResource {
    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public List<ItemDTO> getAllItems() {
        return itemService.findAll().stream().map(ItemDTO::from).toList();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable UUID id) {
        return itemService.findById(id).map(value -> ResponseEntity.ok(ItemDTO.from(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/categories/{categoryId}/items")
    public ResponseEntity<List<ItemDTO>> getItemsByCategoryId(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(itemService.findAllByCategoryId(categoryId).stream().map(ItemDTO::from).toList());
    }

    @PostMapping("/categories/{categoryId}/items")
    public ResponseEntity<ItemDTO> addItem(@PathVariable UUID categoryId, @RequestBody ItemUpdateDTO itemUpdateDTO) {
        String name = itemUpdateDTO.getName();
        Double price = Double.parseDouble(itemUpdateDTO.getPrice());
        Item item = itemService.create(categoryId, name, price);
        return new ResponseEntity<>(ItemDTO.from(item), HttpStatus.CREATED);
    }
}