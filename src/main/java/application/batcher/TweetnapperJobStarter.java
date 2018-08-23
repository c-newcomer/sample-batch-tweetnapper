package application.batcher;
 
import java.util.logging.Logger;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;

public class TweetnapperJobStarter {
    private final static Logger logger = Logger.getLogger("sample");
    public void beginJob() {
        try {
            JobOperator jobOperator = BatchRuntime.getJobOperator();
            jobOperator.start("TweetnapperJob", null);
        } catch (Exception e) {
            logger.info("Something's wrong with TweetnapperJobStarter!");
            e.printStackTrace();
        }
    }
}