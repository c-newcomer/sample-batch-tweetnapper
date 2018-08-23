package application.batcher;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import application.batcher.TweetObject;

@Dependent
@Named("TweetnapperWriter")
public class TweetnapperWriter implements javax.batch.api.chunk.ItemWriter {

    private final static Logger logger = Logger.getLogger("sample");

    @PersistenceContext(unitName = "tweet-persister")
    EntityManager entityManager;

    @Inject
    private JobContext jobCtx;

    @Override
    public void open(Serializable ckpt) throws Exception {
        // logger.info("Hello from the TestWriter");
    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        try {

            logger.info("Writing to db...");
            for (int i = 0; i < items.size(); i++) {
                TweetObject tw = (TweetObject) items.get(i);
                persistTweet(tw);
            }
        } catch (Exception e) {
            logger.info("Something went wrong : " + e);
            e.printStackTrace();
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return new TweetnapperCheckpoint();
    }

    @Override
    public void close() throws Exception {
        // logger.info("Goodbye from the TweetnapperWriter");
    }

    public void persistTweet(TweetObject newTweet) {
        try {
            boolean alreadyExists = entityManager
                    .createQuery("SELECT f.statusId FROM TweetObject f WHERE f.statusId = " + newTweet.getStatusId())
                    .setMaxResults(1).getResultList().isEmpty() ? false : true;

            // if tweet already captured, update popularity in DB
            if (alreadyExists) {

                TweetObject loadedTweet = (TweetObject) entityManager
                        .createQuery("SELECT t FROM TweetObject t WHERE t.statusId = " + newTweet.getStatusId())
                        .getSingleResult();
                Long oldFavs = loadedTweet.getFavoriteCount();
                Long newFavs = newTweet.getFavoriteCount();
                Long oldRetweets = loadedTweet.getRetweetCount();
                Long newRetweets = newTweet.getRetweetCount();
                loadedTweet.setFavoriteCount(newFavs > oldFavs ? newFavs : oldFavs);
                loadedTweet.setFavoriteCount(newRetweets > oldRetweets ? newRetweets : oldRetweets);
                entityManager.merge(loadedTweet);
            } else { // else create a new tweet and add it to the db
                entityManager.persist(newTweet);
            }
            // logger.info("Tweet persisted!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Something went wrong persisting the tweets. Caught exception " + e);
        }
    }
}