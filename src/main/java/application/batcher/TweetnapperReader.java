package application.batcher;

import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.Serializable;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@Dependent
@Named("TweetnapperReader")
public class TweetnapperReader implements javax.batch.api.chunk.ItemReader {
    private BufferedReader breader;
    private final static Logger logger = Logger.getLogger("sample");
    @Inject
    JobContext jobCtx;

    @Override
    public void open(Serializable ckpt) throws Exception {

        // logger.info("Hello from the TestReader");

        String file = TweetnapperReader.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()
                .toString() + "batch-sources/" + "tweetArchive-collection.txt";
        logger.info("File to be read is: " + file);
        breader = new BufferedReader(new FileReader(file));
    }

    @Override
    public void close() throws Exception {
        // logger.info("Goodbye from the TestReader");
        breader.close();
    }

    @Override
    public Object readItem() throws Exception {
        String line = breader.readLine();
        return (line != null) ? line.trim() : null;
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}