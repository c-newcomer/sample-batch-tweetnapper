package application.batcher;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TWEETS", schema = "TWITTERCOLLECTION")
public class TweetObject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DBID")
    private long dbId;

    @Column(name = "STATUS_ID")
    private long statusId;

    @Column(name = "ACCOUNT_ID")
    private long accountId;

    @Column(name = "SCREEN_NAME")
    String screenName;

    @Column(name = "REAL_NAME")
    String realName;

    @Column(name = "NUMBER_OF_FOLLOWERS")
    long numberOfFollowers;

    @Column(name = "ACCOUNT_DESCRIPTION")
    String accountDescription;

    @Lob
    @Column(name = "TEXTCONTENT")
    private String textContent;

    @Column(name = "CREATION_DATE")
    Date creationDate;

    @Column(name = "FAVORITE_COUNT")
    long favoriteCount;

    @Column(name = "RETWEET_COUNT")
    long retweetCount;

    @Column(name = "POPULARITY")
    long popularity;

    @Column(name = "HASHTAGS")
    List<String> hashtags;

    @Column(name = "IS_RETWEET")
    boolean isRetweet;

    @Column(name = "SENTIMENT")
    SentimentObject sentiment;

    @Column(name = "PLACE")
    String place;

    @Column(name = "ACCOUNT_LOCATION")
    String accountLocation;

    @Column(name = "LINKS")
    String urls;

    @Lob
    @Column(name = "IMAGE")
    byte[] image;

    @Lob
    @Column(name = "IMAGE_URL")
    String imageUrl;

    public TweetObject() {
        this.screenName = "";
        this.realName = "";
        this.isRetweet = false;
        this.favoriteCount = 0;
        this.retweetCount = 0;
        this.popularity = 0;
        this.textContent = "";
        this.sentiment = new SentimentObject("",0.0);
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String url) {
        this.imageUrl = url;
    }

    public void setAccountLocation(String location) {
        this.accountLocation = location;
    }

    public String getAccountLocation() {
        return this.accountLocation;
    }

    public void setSentimentObject(SentimentObject sentiment) {
        this.sentiment = sentiment;
    }

    public SentimentObject getSentiment() {
        return this.sentiment;
    }

    public long getStatusId() {
        return this.statusId;
    }

    public void setStatusId(long id) {
        this.statusId = id;
    }

    public long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(long id) {
        this.accountId = id;
    }


    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public void setDate(Date date) {
        this.creationDate = date;
    }

    public Date getDate() {
        return this.creationDate;
    }

    public String getAccountDescription() {
        return this.getAccountDescription();
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public void setNumberOfFollowers(long numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
    }

    public long getNumberOfFollowers() {
        return this.numberOfFollowers;
    }

    public boolean checkIfRetweet() {
        return this.isRetweet;
    }

    public void setRetweetStatus(boolean retweetStatus) {
        this.isRetweet = retweetStatus;

    }

    public long getPopularity() {
        return this.favoriteCount + this.retweetCount;
    }

    public void setPopularity() {
        this.popularity = this.favoriteCount + this.retweetCount;
    }

    public void setFavoriteCount(long favoriteCount) {
        this.favoriteCount = favoriteCount;
        setPopularity();
    }

    public long getFavoriteCount() {
        return this.favoriteCount;
    }

    public void setRetweetCount(long retweetCount) {
        this.retweetCount = retweetCount;
        setPopularity();
    }

    public long getRetweetCount() {
        return this.retweetCount;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenName() {
        return this.screenName;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public List<String> getHashtags() {
        return this.hashtags;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return this.realName;
    }

    @Override
    public String toString() {
        return "";
    }
}
