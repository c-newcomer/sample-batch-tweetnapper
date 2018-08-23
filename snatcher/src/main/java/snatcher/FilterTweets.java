
package snatcher;

import twitter4j.*;

public class FilterTweets {

    // limit feed to tweets from NYC, in the english language, and containing "MTA"
    public static TwitterStream from(StatusListener listener) {

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(listener);
        FilterQuery tweetFilterQuery = new FilterQuery();
        tweetFilterQuery.track(new String[] { "MTA", "#MTA", "@MTA" })
                .locations(new double[][] { new double[] { 39.3682, -75.9374 }, new double[] { 42.0329, -71.7187 } })
                .language(new String[] { "en" });
        twitterStream.filter(tweetFilterQuery);
        return twitterStream;

    }
}