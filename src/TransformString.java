import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import junit.*;

public class TransformString {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    public static void main(String[] args){
        List<String> names = Arrays.asList("Grace Hopper", "Barbara Liskov", "Ada Lovelace", "Karen Spärck Jones", "Akin Timi");

        List<Person> personList = names.stream().map(name -> new Person(name)).collect(Collectors.toList()); // using a Lambda expression

        List<Person> personList1 = names.stream().map(Person::new).collect(Collectors.toList()); //using a constructor reference reference

        for (int x = 0 ; x< personList.size(); x++){
            System.out.println(personList.get(x).getName());
        }
        System.out.println(personList1.get(2).getName());

        //Converting a List to a Stream and back
        Person before = new Person("Pearson Black");
        List<Person> people = Stream.of(before).collect(Collectors.toList());
        Person after = people.get(0);

        assert(before == after);

        before.setName("Castle Black");
//        assert("Castle Black", after.getName());

        //Copy Constructor
        people = Stream.of(before).map(Person::new).collect(Collectors.toList());
        after = people.get(0);
        assert(before == after);

        before.setName("Jon Snow");
        assert(after.equals(before));

        //Using the varargs constructor
        names.stream().map(x -> x.split(" ")).map(Person::new).collect(Collectors.toList());
//        System.out.println("Varargs ctor, names=" + Arrays.toList(names));

        //Arrays- creating an array of person reference
        Person[] person1 = names.stream().map(Person::new).toArray(Person[]::new);
        TransformString ts = new TransformString();
        ts.SortingStrings();
        ts.summaryStatistics();



    }

    public void UsingDefaultMethods(){
        List<Integer> nums = Arrays.asList(3, 5, 9, 8, 7, 1, 9, 2, 4);
        boolean remove = nums.removeIf(n -> n <= 0);
        System.out.println("Elements where " + (remove ? "" : "NOT ") + "removed");
        nums.forEach(System.out::println);
    }

    public void SortingStrings(){
        //Static Methods in Interfaces
        List<String> bonds = Arrays.asList("Connery", "Lazenby", "Moore", "Dalton", "Brosman", "Craig");

        System.out.println("Printing in Natural order...");
        List<String> sorted = bonds.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        sorted.forEach(System.out::println);

        System.out.println();
        System.out.println("Printing in Reversed order...");
        sorted = bonds.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        sorted.forEach(System.out::println);

        System.out.println();
        System.out.println("Printing in toLowerCase order...");
        sorted = bonds.stream().sorted(Comparator.comparing(String::toLowerCase)).collect(Collectors.toList());
        sorted.forEach(System.out::println);

        System.out.println();
        System.out.println("Printing in comparing Int length order...");
        sorted = bonds.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        sorted.forEach(System.out::println);

        System.out.println();
        System.out.println("Printing in comparing Int length & natural order...");
        sorted = bonds.stream().sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder())).collect(Collectors.toList());
        sorted.forEach(System.out::println);

        //Stream.Iterate
        List<BigDecimal> nums = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE)).limit(20).collect(Collectors.toList());
        System.out.println(nums);

        Stream.iterate(LocalDate.now(), ld -> ld.plusDays(1L)).limit(10).forEach(System.out::println);

        Stream.generate(Math::random).limit(10).forEach(System.out::println);

        String[] strings = "This is an array of strings".split(" ");
        long count = Arrays.stream(strings).map(String::length).count();
        System.out.println("There are " + count + " strings");

        int totalLength = Arrays.stream(strings).mapToInt(String::length).sum();
        System.out.println("the total length is " + totalLength);

        OptionalDouble ave = Arrays.stream(strings).mapToInt(String::length).average();
        System.out.println("The average length is "+ ave);

        OptionalInt max = Arrays.stream(strings).mapToInt(String::length).max();
        System.out.println("The max value is " + max);

        OptionalInt min = Arrays.stream(strings).mapToInt(String::length).min();
        System.out.println("the min value is " + min);

        int sum = IntStream.rangeClosed(1, 10).reduce((x, y) -> {
            System.out.printf("x=%d, y=%d%n", x, y);
            return x + y;
        }).orElse(0);
        System.out.println("Sum=" + sum);
    }

    //working with Supplier
    public void OptionalExamp(){
        List<String> names = Arrays.asList("Mac","Wash","Kaylee","Inara","Zoe","Jayne","Simon","Zara");

        Optional firstElement = names.stream().filter(name -> name.startsWith("Z")).findFirst();
        //using peek to see individual elements in a stream
        Optional firstElement1 = names.stream().peek(n -> System.out.println(n)).filter(name -> name.startsWith("Z")).findFirst();

        System.out.println(firstElement);
        System.out.println(firstElement.orElse("None"));

        System.out.println(firstElement.orElse(String.format("no result found in %s", names.stream().collect(Collectors.joining(",")))));

        System.out.println(firstElement.orElseGet(() -> String.format("no result found in %s", names.stream().collect(Collectors.joining(",")))));
    }

    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        String forward = sb.toString().toLowerCase();
        String backward = sb.reverse().toString().toLowerCase();
        return forward.equals(backward);
    }

    public void summaryStatistics(){
        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(1_000_000).summaryStatistics();
        System.out.println(stats);
        System.out.println(stats.getMax());
        System.out.println(stats.getMin());
        System.out.println(stats.getAverage());
        System.out.println(stats.getCount());
        System.out.println(stats.getSum());
        logger.info("This is an info message");
        logger.severe("This is a severe message");
    }

}
