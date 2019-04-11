import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

public class RunnableDemo {


    public static void main (String[] args){

        new Thread(() ->
                System.out.println("inside runnable using an anonymous inner class")
        ).start();

        Runnable r = () -> System.out.println("Running");

        new Thread(r).start();


        File directory = new File("/Users/takintolu/Documents");

        String[] names = directory.list((dir, name) -> false);
        System.out.println(Arrays.asList(names));

        Stream.of(3, 1, 4, 1, 5 ,9).forEach(System.out::println);

        Stream.generate(Math::random).limit(10)
                .forEach(System.out::println);

        System.out.println(Math.round(0.56684));
        String MyName = "Akintolu Mabolaje";

    }
}
