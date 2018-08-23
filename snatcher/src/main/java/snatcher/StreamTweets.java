
package snatcher;

import snatcher.FilterTweets;
import twitter4j.*;

public class StreamTweets {
    static boolean showIncoming = true;
    public static int counter = 0;

    public static void startStream() {
        FilterTweets.from(streamFeed());
    }

    public static Twitter getTwitterinstance() {
        Twitter twitter = TwitterFactory.getSingleton();
        return twitter;
    }

    public static StatusListener streamFeed() {

        return new StatusListener() {

            @Override
            public void onException(Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg) {
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onStatus(Status status) {
                // System.out.println("\nincoming tweet...!");
                if (showIncoming) {
                    try {
                        String text = status.getText();
                        User user = status.getUser();
                        String location = user.getLocation() != null ? " in " + user.getLocation() : "";
                        // if (status.getQuotedStatusId() != -1 ) {
                        // text = status.getQuotedStatus().getText();
                        // }
                        // else
                        if (status.getRetweetedStatus() != null) {
                            text = "RT " + status.getRetweetedStatus().getText();
                        }
                        System.out.println("\n----\n" + user.getName() + " (@" + user.getScreenName() + ")" + location
                                + ": " + text + "\n" + "----");
                    } catch (Exception e) {
                        System.out.println("\nPreview function needs a little more work...");
                        e.printStackTrace();
                    }
                }

                try {
                    TweetSaver tp = new TweetSaver();
                    tp.persist(status);
                } catch (Exception e) {
                    System.out.println("exceptional!");
                }
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                // System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }
        };
    }
}