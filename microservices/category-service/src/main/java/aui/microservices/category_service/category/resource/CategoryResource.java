package aui.microservices.category_service.category.resource;

import aui.microservices.category_service.category.domain.Category;
import aui.microservices.category_service.category.domain.CategoryMap;
import aui.microservices.category_service.category.domain.CategoryResponse;
import aui.microservices.category_service.category.function.CategoryToResponseFunction;
import aui.microservices.category_service.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Log
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryResource {
    private final CategoryService categoryService;
    private final CategoryToResponseFunction categoryToResponse;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<CategoryResponse> getAll() {
        return categoryService.findAll().stream().map(categoryToResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CategoryResponse getById(@PathVariable("id") UUID id) {
        return categoryService.findById(id)
                .map(categoryToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CategoryResponse create(@RequestBody CategoryMap map) {
        return categoryToResponse.apply(categoryService.create(map.getName()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CategoryResponse update(@PathVariable UUID id, @RequestBody CategoryMap map) {
        return categoryToResponse.apply(categoryService.update(id, map.getName()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        categoryService.findById(id)
                .ifPresentOrElse(
                        category -> categoryService.delete(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }
}
