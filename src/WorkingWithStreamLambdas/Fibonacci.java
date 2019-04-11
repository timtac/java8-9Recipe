package WorkingWithStreamLambdas;

import java.math.BigInteger;
import java.util.*;
import java.util.logging.Logger;

//Fibonacci calculation with cache
public class Fibonacci {

    private Logger logger =Logger.getLogger(getClass().getName());
    private List<String> data = new ArrayList<>();


    public static void main(String[] args){
        String passage = "NSA agent walks into a bar. Bartender says, " +
        "'Hey, I have a new joke for you.' agent says, 'heard it'.";
        Fibonacci f = new Fibonacci();
        BigInteger outcome = f.fib(5);

        Map<String, Integer> count = f.findWordCount(passage, "NSA","agent","joke");
        count.forEach((key,value) -> System.out.println(key + " = " + value));

        Map<String, Integer> count2 = f.countWords(passage);
        count2.forEach((key,value) -> System.out.println(key + " = " + value));


    }

    private Map<Long, BigInteger> cache = new HashMap<>();

    public BigInteger fib(long i){
        if (i == 0) return BigInteger.ZERO;
        if (i == 1) return BigInteger.ONE;
        return cache.computeIfAbsent(i, n -> fib(n - 2).add(fib(n - 1)));
    }
    /*
    The computeIfAbsent method looks in the cache for a given number. If it exists, it returns the value.
    Otherwise it uses the supplied Function to compute the new value, store it in the cache, and return it.
    That’s quite an improvement for a single method.
     */

    //compute if present

    private Map<String, Integer> findWordCount(String passage, String... strings){

        Map<String, Integer> wordCount = new HashMap<>();
        Arrays.stream(strings).forEach(s -> wordCount.put(s, 0));

        Arrays.stream(passage.split(" ")).forEach(s -> wordCount.computeIfPresent(s, (key, value) -> value + 1));

        return wordCount;
    }

    //using map.merge() method to count number of all words in a text
    private Map<String, Integer> countWords (String passage) {
        Map<String, Integer> wordCount = new HashMap<>();
        String testString = passage.toLowerCase().replaceAll("\\W"," ");
        Arrays.stream(testString.split(" ")).forEach(s -> wordCount.merge(s, 1, Integer::sum));

        return wordCount;
    }

    //using logger

    public void loggerExamp() {
        data.add("");
        data.add("");
        data.add("");
        data.add("");

        logger.info("the value of data is " + data.toString());
        logger.info(() -> "the value of data is " + data.toString());
    }
}
