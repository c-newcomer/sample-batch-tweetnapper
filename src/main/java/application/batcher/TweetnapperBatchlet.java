package application.batcher;

import java.util.logging.Logger;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Dependent
@Named("TweetnapperBatchlet")
public class TweetnapperBatchlet implements javax.batch.api.Batchlet {
    private final static Logger logger = Logger.getLogger("sample");
    @Inject
    private JobContext jobCtx;

    @Override
    public String process() throws Exception {
        logger.info("Hello and goodbye from the TestBatchlet"); 
        return "COMPLETED";
    }

	@Override
	public void stop() throws Exception {
		
	}
}