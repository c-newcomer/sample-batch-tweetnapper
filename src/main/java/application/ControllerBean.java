package application;

import javax.annotation.PostConstruct;
import javax.annotation.security.RunAs;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;
import application.batcher.TweetnapperJobStarter;

@Singleton
@Startup
@RunAs("JOBSTARTER")
public class ControllerBean {
    @PersistenceContext(unitName = "tweet-persister")
    EntityManager entityManager;
    private final static Logger logger = Logger.getLogger("sample");

    @PostConstruct
    public void initialize() {
        
        logger.info("\n\n\n\n\nhello from the startup ejb\n\n\n\n\n");

        TweetnapperJobStarter t = new TweetnapperJobStarter();
        t.beginJob();

    }
}
