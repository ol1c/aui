package aui.microservices.item_service.item.resource;

import aui.microservices.item_service.item.domain.ItemMap;
import aui.microservices.item_service.item.domain.ItemResponse;
import aui.microservices.item_service.item.function.ItemToResponseFunction;
import aui.microservices.item_service.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemResource {
    private final ItemService itemService;
    private final ItemToResponseFunction itemToResponse;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<ItemResponse> getAll(@RequestParam(value = "categoryId", required = false) UUID categoryId) {
        if (categoryId != null) {
            return itemService.findAllByCategoryId(categoryId).stream().map(itemToResponse).collect(Collectors.toList());
        }
        return itemService.findAll().stream().map(itemToResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ItemResponse getById(@PathVariable("id") UUID id) {
        return itemService.findById(id)
                .map(itemToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ItemResponse create(@RequestBody ItemMap map) {
        return itemToResponse.apply(itemService.create(map.getName(), map.getPrice(), UUID.fromString(map.getCategoryId())));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ItemResponse update(@PathVariable UUID id, @RequestBody ItemMap map) {
        if (map.getCategoryId() != null) {
            return itemToResponse.apply(itemService.update(id, map.getName(), map.getPrice(), UUID.fromString(map.getCategoryId())));
        }
        return itemToResponse.apply(itemService.update(id, map.getName(), map.getPrice(), null));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        itemService.findById(id)
                .ifPresentOrElse(
                        item -> itemService.delete(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }
}
