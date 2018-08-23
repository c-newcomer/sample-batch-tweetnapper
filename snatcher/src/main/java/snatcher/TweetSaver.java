package snatcher;

import java.text.SimpleDateFormat;

import com.google.gson.Gson;

import twitter4j.*;

public class TweetSaver {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd__HH-mm");
    public static String fe = ".txt";

    public void persist(Status status) throws Exception {
 
        try {
            Gson gson = new Gson();
            String preview = gson.toJson(status);
            JsonWriter.write(preview, sdf, fe);
            FileScooter fs = new FileScooter();
            fs.copyFiles("temp/", "batch-source/", sdf, fe);
        } catch (Exception e) {
            System.out.println("couldn't save the tweet :(");
            e.printStackTrace();
        }
    }
}
