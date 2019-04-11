import java.util.Arrays;
import java.util.stream.Collectors;

public class Person {

    private String name;

    public Person(){

    }

    public Person(String name){
        this.name = name;
    }

    public Person(Person p){
        this.name = p.name;
    }

    //Varargs constructor:- a constructor that takes a variable argument list of String
    public Person(String... names){
        this.name = Arrays.stream(names).collect(Collectors.joining(" "));
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
