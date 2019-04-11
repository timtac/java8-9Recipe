package CollectionComparators;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Book {
    private int Id;
    private String name;
    private double price;

    public Book(int id, String name, double price) {
        Id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void sampleMethod(){
        List<Book> books = Arrays.asList(
                new Book(1,"Binta",20.50),
                new Book(2,"Ojo Farmer",30.20)
        );

        //getting books are a Map
        Map<Integer, Book> bookMap = books.stream()
                .collect(Collectors.toMap(Book::getId, b -> b));

        //another way
        bookMap = books.stream().collect(Collectors.toMap(Book::getId, Function.identity()));

    }

    public void oddStringSet(String... strings){

    }
}
