package FileIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DicStream {

    public static void main(String[] args) {
        try(Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/web2"))){
            lines.filter(s -> s.length() > 20)
                    .sorted(Comparator.comparingInt(String::length).reversed())
                    .limit(10)
                    .forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    //NUmber of words of each length, in descending order
    public void NoOfWords(){
        try(Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/web2"))){
            Map<Integer, Long> map = lines.filter(s -> s.length() > 20)
                    .collect(Collectors.groupingBy(String::length, Collectors.counting()));

            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("%d : %d words %n", e.getKey(), e.getValue()));
        } catch (IOException ex){

        }
    }

    //processing all the files in a directory with Files.list method
    public void processFiles(String path){
        try(Stream<Path> list = Files.list(Paths.get(path))) {
            list.forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //using Files.walk
    public void walkFilesDirectory(){
        try(Stream<Path> walk = Files.walk(Paths.get("src/main/java"))){
            walk.forEach(System.out::println);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
