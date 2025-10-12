package aui.lab.runner;

import aui.lab.entities.*;
import ch.qos.logback.core.net.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApplicationCommandLineRunner implements CommandLineRunner {

//    private final ObjectWriter writer;

    @Override
    public void run(String... args) throws Exception {
        Category drinks = createNewCategory("Drinks");
        Category snacks = createNewCategory("Snacks");

        Item water = createNewItem("Water", 0.99, drinks);
        Item juice = createNewItem("Juice", 1.99, drinks);
        Item crisps = createNewItem("Crisps", 4.49, snacks);
        Item candybar = createNewItem("Candy bar", 2.49, snacks);

        List<Category> categories = List.of(drinks, snacks);
        categories.forEach(category -> {
            System.out.println(category);
            category.getItems().stream()
                    .map(ItemDTO::from)
                    .forEach((item -> System.out.println("   " + item)));
        });
        System.out.println();

        Set<Item> allItems = categories.stream()
                .flatMap(category -> category.getItems().stream())
                .collect(Collectors.toSet());
        allItems.stream().map(ItemDTO::from).forEach(System.out::println);
        System.out.println();

        allItems.stream()
                .filter(item -> item.getName().startsWith("C"))
                .sorted(Comparator.comparing(Item::getPrice))
                .map(ItemDTO::from)
                .forEach(System.out::println);
        System.out.println();

        List<ItemDTO> allItemsDTO = allItems.stream()
                .map(ItemDTO::from)
                .sorted()
                .toList();
        allItemsDTO.forEach(System.out::println);
        System.out.println();

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("categories.bin"))) {
            output.writeObject(categories);
            System.out.println("Saved to file");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Saving error");
        }

        List<Category> categoriesFromFile = null;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("categories.bin"))) {
            categoriesFromFile = (List<Category>) input.readObject();
            System.out.println("Loaded from file");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Loading error");
        }

        if (categoriesFromFile != null) {
            categoriesFromFile.forEach(category -> {
                System.out.println(category);
                category.getItems().stream()
                        .map(ItemDTO::from)
                        .forEach(item -> System.out.println("  " + item));
            });
        }

        ForkJoinPool customPool = new ForkJoinPool(2);

        Runnable task = () -> categories.parallelStream().forEach(category -> {
            System.out.println("[" + Thread.currentThread().getName() + "] Category: " + category.getName());
            category.getItems().stream()
                    .map(ItemDTO::from)
                    .forEach(item -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("   [" + Thread.currentThread().getName() + "] Item: " + item);
            });
        });

        try {
            customPool.submit(task).get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            customPool.shutdown();
            customPool.awaitTermination(1, TimeUnit.MINUTES);
        }

//        Scanner scanner = new Scanner(System.in);
//        String command;
//        main_loop:
//        while (true) {
//            command = scanner.next();
//            switch (command) {
//
//            }
//        }
    }

    private static Category createNewCategory(String name) {
        return Category.builder()
                .id(UUID.randomUUID())
                .name(name)
                .items(new ArrayList<>())
                .build();
    }

    private static Item createNewItem(String name, Double price, Category category) {
        Item newItem = Item.builder()
                .id(UUID.randomUUID())
                .name(name)
                .price(price)
                .category(category)
                .build();
        category.getItems().add(newItem);
        return newItem;
    }
}
