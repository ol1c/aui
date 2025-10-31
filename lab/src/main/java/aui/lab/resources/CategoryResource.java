package aui.lab.resources;

import aui.lab.entities.Category;
import aui.lab.entities.CategoryDTO;
import aui.lab.entities.CategoryUpdateDTO;
import aui.lab.services.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.findAll().stream().map(CategoryDTO::from).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable UUID id) {
        Optional<Category> category = categoryService.findById(id);
        return new ResponseEntity<>(category.map(CategoryDTO::from).orElse(null), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryUpdateDTO categoryUpdateDTO) {
        String name = categoryUpdateDTO.getName();
        Category category = categoryService.create(name);
        return new ResponseEntity<>(CategoryDTO.from(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory (@PathVariable UUID id, @RequestBody CategoryUpdateDTO categoryUpdateDTO) {
        return new ResponseEntity<>(CategoryDTO.from(categoryService.update(id, categoryUpdateDTO.getName())), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
