package application.batcher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Dependent
@Named("TweetnapperProcessor")
public class TweetnapperProcessor implements javax.batch.api.chunk.ItemProcessor {
    private final static Logger logger = Logger.getLogger("sample");
    @PersistenceContext(unitName = "tweet-persister")
    EntityManager entityManager;
    boolean useWatson = true;

    @Override
    public TweetObject processItem(Object obj) throws Exception {
        String rawJson = (String) obj;
        return parseMetadata(rawJson);
    }

    public TweetObject parseMetadata(String json) {
        TweetObject to = new TweetObject();
        String accountLocation = "";
        JsonParser parser = new JsonParser();
        JsonObject status = parser.parse(json).getAsJsonObject();

        boolean isQuoted = status.get("quotedStatusId").getAsInt() != -1;
        boolean isRetweeted = status.get("retweetedStatus") != null;
        boolean isRetweet = isRetweeted ? true : false;

        // get root status to obtain full text (otherwise truncates too early)
        if (isRetweet) { 
            status = status.get("retweetedStatus").getAsJsonObject();
        }
        if (isQuoted) { 
            status = status.get("quotedStatus").getAsJsonObject();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy hh:mm:ss a");
        String createdAt = status.get("createdAt").getAsString();
        Date date = new Date();
        try {
            date = dateFormat.parse(createdAt);
            to.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObject user = status.get("user").getAsJsonObject();
        Long userId = user.get("id").getAsLong();
        String realName = user.get("name").getAsString();
        String screenName = user.get("screenName").getAsString();
        Long numberOfFollowers = user.get("followersCount").getAsLong();

        if (user.get("location") != null) {
            accountLocation = user.get("location").getAsString();
        }

        Long statusId = status.get("id").getAsLong();
        Long favoriteCount = status.get("favoriteCount").getAsLong();
        Long retweetCount = status.get("retweetCount").getAsLong();
        String textContent = status.get("text").getAsString();

        // from status obj
        to.setRetweetStatus(isRetweet);
        to.setStatusId(statusId);
        to.setFavoriteCount(favoriteCount);
        to.setRetweetCount(retweetCount);
        to.setTextContent(textContent);

        // from user obj
        to.setAccountId(userId);
        to.setRealName(realName);
        to.setScreenName(screenName);
        to.setNumberOfFollowers(numberOfFollowers);

        to.setAccountLocation(accountLocation);
        
        if (useWatson) {
            try {
                LanguageAnalyzer la = new LanguageAnalyzer();
                SentimentObject so = la.analyze(to.getTextContent());
                to.setSentimentObject(so);
            } catch (Exception e) {
                SentimentObject so = new SentimentObject();
                to.setSentimentObject(so);
            }
        } else {
            SentimentObject so = new SentimentObject();
            to.setSentimentObject(so);
        }
        return to;
    }
}

