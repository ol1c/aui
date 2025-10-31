package aui.lab.resources;

import aui.lab.entities.*;
import aui.lab.services.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/categories")
public class CategoryResource {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.findAll().stream().map(CategoryDTO::from).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable UUID id) {
        return categoryService.findById(id).map(value -> ResponseEntity.ok(CategoryDTO.from(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryUpdateDTO categoryUpdateDTO) {
        String name = categoryUpdateDTO.getName();
        Category category = categoryService.create(name);
        return new ResponseEntity<>(CategoryDTO.from(category), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<ItemDTO> addItemToCategory(@PathVariable UUID categoryId, @RequestBody ItemUpdateDTO itemUpdateDTO) {
        String name = itemUpdateDTO.getName();
        Double price = Double.parseDouble(itemUpdateDTO.getPrice());
        Item item = categoryService.createItem(categoryId, name, price);
        return new ResponseEntity<>(ItemDTO.from(item), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory (@PathVariable UUID id, @RequestBody CategoryUpdateDTO categoryUpdateDTO) {
        return ResponseEntity.ok(CategoryDTO.from(categoryService.update(id, categoryUpdateDTO.getName())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
