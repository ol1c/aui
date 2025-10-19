package aui.lab.runner;

import aui.lab.entities.*;
import aui.lab.services.CategoryService;
import aui.lab.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Order(1)
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoryService categoryService;
    private final ItemService itemService;

    @Override
    public void run(String... args) {
        Category drinks = Category.builder()
                .id(UUID.randomUUID())
                .name("Drinks")
                .build();

        Category snacks = Category.builder()
                .id(UUID.randomUUID())
                .name("Snacks")
                .build();

        Item water = Item.builder()
                .id(UUID.randomUUID())
                .name("Water")
                .price(0.99)
                .build();
        drinks.addItem(water);

        Item juice = Item.builder()
                .id(UUID.randomUUID())
                .name("Juice")
                .price(1.99)
                .build();
        drinks.addItem(juice);

        Item crisps = Item.builder()
                .id(UUID.randomUUID())
                .name("Crisps")
                .price(4.49)
                .build();
        snacks.addItem(crisps);

        Item candybar = Item.builder()
                .id(UUID.randomUUID())
                .name("Candy bar")
                .price(2.49)
                .build();
        snacks.addItem(candybar);

        categoryService.saveAll(List.of(drinks, snacks));

//        System.out.println("Categories in Spring-DB: " + categoryService.count());
//        System.out.println("Items in Spring-DB: " + itemService.count());
//
//        itemService.findAllByCategoryId(snacks.getId())
//                .forEach(i -> System.out.println("[Snacks] " + i.getName() + " - " + i.getPrice()));
    }
}
