package snatcher;

import java.text.SimpleDateFormat;

import snatcher.StreamTweets;

public class TweetSnatcher {
    private static int counter = 0;
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd__HH-mm");
    public static void start() {
        FileScooter fs = new FileScooter();
        fs.concatFiles(); 
        System.out.println("Starting snatcher...");
        StreamTweets.startStream();
    }
}