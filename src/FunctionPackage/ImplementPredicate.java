package FunctionPackage;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ImplementPredicate {
    public static final Predicate<String> LENGTH_FIVE = s -> s.length() == 5;
    public static final Predicate<String> STARTS_WITH_S = s -> s.startsWith("S");

    public String getNameOfLength(int length, String ... names){
        return Arrays.stream(names).filter(s -> s.length() == length)
                .collect(Collectors.joining(","));
    }

    public String getNamesSatisfyingCondition(String s, String... names){
        return Arrays.stream(names).filter(x -> x.startsWith(s))
                .collect(Collectors.joining(","));
    }
}
