package ParallelVsSequential;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class AddingIntegers {

    @Test
    public static int doubleIt(int n){
        try{
            Thread.sleep(100);
        }
        catch (InterruptedException ig){

        }
        return n * 2;
    }

    public static void main(String[] args){

        Instant before = Instant.now();
        int total = IntStream.of(3, 2, 1, 4, 5, 1, 9)
                .parallel()
                .map(AddingIntegers::doubleIt)
                .sum();

        Instant after = Instant.now();
        Duration duration = Duration.between(before, after);
        System.out.println("Total of doubles = " + total);
        System.out.println("time = " + duration.toMillis() + "ms");

    }

    //unoptimised
    public long sequentialStreamSum() {
        return Stream.iterate(1L, i -> i + 1)
            .limit(10)//N
            .reduce(0L, Long::sum);
    }
    public long parallelStreamSum() {
        return Stream.iterate(1L, i -> i + 1)
                .limit(10)//N
                .parallel()
                .reduce(0L, Long::sum);
    }

    //optimized
    public long sequentialLongStreamSum() {
        return LongStream.rangeClosed(1, 10)//N
            .sum();
    }
    public long parallelLongStreamSum() {
        return LongStream.rangeClosed(1, 10)//N
            .parallel()
            .sum();
    }
}
