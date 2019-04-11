package ParallelVsSequential;

import javax.xml.transform.Result;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

public class BoxscoreRetriever implements Function<List<String>, List<Result>> {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public List<Result> apply(List<String> strings) {
        return null;
    }
}
