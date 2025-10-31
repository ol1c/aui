package aui.lab.runner;

import aui.lab.entities.*;
import aui.lab.services.CategoryService;
import aui.lab.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@Order(2)
@RequiredArgsConstructor
public class ApplicationCommandLineRunner implements CommandLineRunner {

    private final CategoryService categoryService;
    private final ItemService itemService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;

        main_loop:
        while (true) {
            System.out.print("> ");
            command = scanner.next().toLowerCase();
            switch (command) {
                case "quit" -> {
                    break main_loop;
                }
                case "help" -> {
                    showAvailableCommands();
                }
                case "get_categories" -> {
                    showAllCategories();
                }
                case "get_items" -> {
                    showAllItems();
                }
                case "add_item" -> {
                    addItem(scanner);
                }
                case "delete_item" -> {
                    deleteItem(scanner);
                }
                default -> {
                    System.out.println("Unknown command");
                }
            }
        }
    }

    private void showAvailableCommands() {
        System.out.println("Available commands:");
        System.out.println("    help                - show this help");
        System.out.println("    get_categories      - list all categories");
        System.out.println("    get_items           - list all items");
        System.out.println("    add_item            - add new item (with category selection)");
        System.out.println("    delete_item         - delete existing item (by UUID)");
        System.out.println("    quit                - stop the application");
    }

    private void showAllCategories() {
        List<Category> categories = categoryService.findAll();
        if (categories == null) {
            System.out.println("(no categories)");
        } else {
            categories.forEach(System.out::println);
        }
    }

    private void showAllItems() {
        List<Item> items = itemService.findAll();
        if (items == null) {
            System.out.println("(no items)");
        } else {
            items.stream().map(ItemDTO::from).forEach(System.out::println);
        }
    }

    private void addItem(Scanner scanner) {
        System.out.print("Item name: ");
        String name = scanner.next();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Item price (use dot as decimal separator): ");
        String priceStr = scanner.next();
        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price.");
            return;
        }

        List<Category> categories = categoryService.findAll();
        System.out.println("Select category:");
        for (int i = 1; i <= categories.size(); i++) {
            System.out.println(i + ") " + categories.get(i-1).getName());
        }
        System.out.print("Enter number: ");
        String idxStr = scanner.next();
        int idx;
        try {
            idx = Integer.parseInt(idxStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return;
        }
        if (idx < 1 || idx > categories.size()) {
            System.out.println("Out of range.");
            return;
        }
        Category category = categories.get(idx - 1);

        Item newItem = Item.builder().
                id(UUID.randomUUID()).
                name(name).
                price(price).
                category(category).
                build();
        itemService.save(newItem);
        System.out.println("Added item: " + ItemDTO.from(newItem));
    }

    private void deleteItem(Scanner scanner) {
        List<Item> items = itemService.findAll();
        if (items.isEmpty()) {
            System.out.println("No items to delete.");
            return;
        }
        items.stream().map(ItemDTO::from).forEach(System.out::println);
        System.out.print("Enter item UUID or number: ");
        String idString = scanner.next();

        try {
            UUID id = UUID.fromString(idString);
            if (itemService.existsById(id)) {
                itemService.deleteById(id);
                System.out.println("Deleted item with id " + id);
            } else {
                System.out.println("Item not found: " + id);
            }
            return;
        } catch (IllegalArgumentException ignored) { }
    }

//    LAB 1
//    private void lab1(Category drinks, Category snacks) {
//        List<Category> categories = List.of(drinks, snacks);
//        categories.forEach(category -> {
//            System.out.println(category);
//            category.getItems().stream()
//                    .map(ItemDTO::from)
//                    .forEach((item -> System.out.println("   " + item)));
//        });
//        System.out.println();
//
//        Set<Item> allItems = categories.stream()
//                .flatMap(category -> category.getItems().stream())
//                .collect(Collectors.toSet());
//        allItems.stream().map(ItemDTO::from).forEach(System.out::println);
//        System.out.println();
//
//        allItems.stream()
//                .filter(item -> item.getName().startsWith("C"))
//                .sorted(Comparator.comparing(Item::getPrice))
//                .map(ItemDTO::from)
//                .forEach(System.out::println);
//        System.out.println();
//
//        List<ItemDTO> allItemsDTO = allItems.stream()
//                .map(ItemDTO::from)
//                .sorted()
//                .toList();
//        allItemsDTO.forEach(System.out::println);
//        System.out.println();
//
//        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("categories.bin"))) {
//            output.writeObject(categories);
//            System.out.println("Saved to file");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Saving error");
//        }
//
//        List<Category> categoriesFromFile = null;
//        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("categories.bin"))) {
//            categoriesFromFile = (List<Category>) input.readObject();
//            System.out.println("Loaded from file");
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("Loading error");
//        }
//
//        if (categoriesFromFile != null) {
//            categoriesFromFile.forEach(category -> {
//                System.out.println(category);
//                category.getItems().stream()
//                        .map(ItemDTO::from)
//                        .forEach(item -> System.out.println("  " + item));
//            });
//        }
//
//        ForkJoinPool customPool = new ForkJoinPool(2);
//
//        Runnable task = () -> categories.parallelStream().forEach(category -> {
//            System.out.println("[" + Thread.currentThread().getName() + "] Category: " + category.getName());
//            category.getItems().stream()
//                    .map(ItemDTO::from)
//                    .forEach(item -> {
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            Thread.currentThread().interrupt();
//                        }
//                        System.out.println("   [" + Thread.currentThread().getName() + "] Item: " + item);
//                    });
//        });
//
//        try {
//            customPool.submit(task).get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            customPool.shutdown();
//            customPool.awaitTermination(1, TimeUnit.MINUTES);
//        }
//    }
}
