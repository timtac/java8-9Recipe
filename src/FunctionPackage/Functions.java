package FunctionPackage;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Functions {
    Function<Integer, Integer> add2 = x -> x + 2;
    Function<Integer, Integer> multi3 = x -> x * 3;



    public static void main(String[] args) {
        Functions fun = new Functions();
        //System.out.println(isPerfect(81));
        //System.out.println(isTriangular(10));
        List<Integer> values = Arrays.asList(30, 20, 10, 15);
        List<Integer> results = fun.div(values, 0);
        System.out.println(results);
        //CompositionDemo();
    }

    public void mergeFunction(){

        Function<Integer, Integer> mult3add2 = add2.compose(multi3);
        Function<Integer, Integer> add2multi3 = add2.andThen(multi3);

        System.out.println(mult3add2.apply(1));
        System.out.println(add2multi3.apply(2));
    }

    public static boolean isPerfect(int x){
        //System.out.println(Math.sqrt(x));
        return Math.sqrt(x) % 1 == 0;
     }

    //n=(sqrt(8x + 1) -1)/ 2
    public static boolean isTriangular(int x){
        double val = (Math.sqrt(8 * x + 1) - 1) / 2;
        return val % 1 == 0;
    }

    public static void CompositionDemo() {
        IntPredicate triangular = Functions::isTriangular;
        IntPredicate perfect = Functions::isPerfect;
        IntPredicate both = triangular.and(perfect);

        IntStream.rangeClosed(1, 10_000).filter(both).forEach(System.out::println);
    }

    //divide a collection by a constant value
    public List<Integer> div(List<Integer> values, Integer factor) {
        return values.stream().map(n -> n / factor).collect(Collectors.toList());
    }

    //Implementing exception wrapper class
    private static <T, R, E extends Exception> Function<T, R> wrapper(FunctionWithInterfaceException<T, R, E> fe) {
        return arg -> {
            try {
              return fe.apply(arg);
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        };
    }
}
