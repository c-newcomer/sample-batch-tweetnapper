package application.fetcher;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
import com.google.gson.Gson;
import application.batcher.TweetObject;

@Dependent
@Path("tweets")
public class TweetService {
    private final static Logger logger = Logger.getLogger("sample");

    @PersistenceContext(unitName = "tweet-persister")
    EntityManager entityManager;

    List<TweetObject> tweets = new ArrayList<TweetObject>();

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON })
    public String getTestTweets(@QueryParam("from") Integer startingPoint, @QueryParam("to") Integer endingPoint,
            @QueryParam("orderBy") String category) {

        // hardcoded for now...pagination via front end later
        startingPoint = 0;
        endingPoint = 100;
        
        // set query to either popularity or creation date
        String jql = category.equals("popularity") ? "SELECT t FROM TweetObject as t ORDER BY t.popularity DESC"
                : "SELECT t FROM TweetObject as t ORDER BY t.creationDate DESC";

        try {
            Query query = entityManager.createQuery(jql);
            query.setFirstResult(startingPoint).setMaxResults(endingPoint);
            tweets = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Something went wrong: " + e);
        }

        String json = new Gson().toJson(tweets);
        return json;

    }
}
