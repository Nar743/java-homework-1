import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private String article;
    private String name;
    private double price;
    private int quantity;

    public Product(String article, String name, double price, int quantity) {
        this.article = article;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getArticle() {
        return article;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Артикул: " + article + ", Название: " + name + ", Цена: " + price + ", Количество: " + quantity;
    }
}


public class Main {
    private static ArrayList<Product> products = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nМеню:");
            System.out.println("1. Создать новый товар (create article name price quantity)");
            System.out.println("2. Вывести список всех товаров (read)");
            System.out.println("3. Обновить информацию о товаре (update article name price quantity)");
            System.out.println("4. Удалить товар (delete article)");
            System.out.println("5. Выйти из программы (exit)");
            System.out.print("Введите номер команды: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Считываем лишний перевод строки после ввода числа

            switch (choice) {
                case 1:
                    System.out.println("Пример заполнения: create A001 \"Ноутбук\" 999.99 10");
                    createProduct();
                    break;
                case 2:
                    readProducts();
                    break;
                case 3:
                    System.out.println("Пример заполнения: update A001 \"Ноутбук\" 899.99 5");
                    updateProduct();
                    break;
                case 4:
                    System.out.println("Пример заполнения: delete A001");
                    deleteProduct();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный номер команды.");
            }
        }
    }

    private static void createProduct() {
        System.out.print("Введите артикул, название, цену и количество товара: ");
        String input = scanner.nextLine();
        String[] tokens = input.split(" ");
        if (tokens.length != 4) {
            System.out.println("Некорректное количество аргументов.");
            return;
        }
        createProduct(tokens[0], tokens[1], Double.parseDouble(tokens[2]), Integer.parseInt(tokens[3]));
    }

    private static void createProduct(String article, String name, double price, int quantity) {
        for (Product product : products) {
            if (product.getArticle().equals(article)) {
                System.out.println("Товар с таким артикулом уже существует.");
                return;
            }
        }
        products.add(new Product(article, name, price, quantity));
        System.out.println("Товар успешно добавлен.");
    }

    private static void readProducts() {
        if (products.isEmpty()) {
            System.out.println("Список товаров пуст.");
            return;
        }
        System.out.println("Список товаров:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void updateProduct() {
        System.out.print("Введите артикул, новое название, новую цену и новое количество товара: ");
        String input = scanner.nextLine();
        String[] tokens = input.split(" ");
        if (tokens.length != 4) {
            System.out.println("Некорректное количество аргументов.");
            return;
        }
        updateProduct(tokens[0], tokens[1], Double.parseDouble(tokens[2]), Integer.parseInt(tokens[3]));
    }

    private static void updateProduct(String article, String name, double price, int quantity) {
        for (Product product : products) {
            if (product.getArticle().equals(article)) {
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                System.out.println("Информация о товаре успешно обновлена.");
                return;
            }
        }
        System.out.println("Товар с указанным артикулом не найден.");
    }

    private static void deleteProduct() {
        System.out.print("Введите артикул товара для удаления: ");
        String article = scanner.nextLine();
        deleteProduct(article);
    }

    private static void deleteProduct(String article) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getArticle().equals(article)) {
                products.remove(i);
                System.out.println("Товар успешно удален.");
                return;
            }
        }
        System.out.println("Товар с указанным артикулом не найден.");
    }
}

