package application.batcher;

import java.io.Serializable;

public class SentimentObject implements Serializable {

    String sentimentLabel;
    Double sentimentScore;
    String sentimentEmoji;

    public SentimentObject() {
    }

    public SentimentObject(String label, Double score) {
        this.sentimentLabel = label;
        this.sentimentScore = score;
        setSentimentEmoji();
    }

    public void setSentimentEmoji() {
        
        String neutralEmoji = "😶";

        String slightlyHappyEmoji = "🙂";
        String veryHappyEmoji = "😃";
        String veryExtremelyHappyEmoji = "😁";

        String slightlyUnhappyEmoji = "🙁";
        String veryUnhappyEmoji = "😠"; 
        String veryExtremelyUnhappyEmoji = "😡";

        // COME BACK AND FIX
        Double ss = this.sentimentScore;
        if (ss > .9) { // greater than .9
            this.sentimentEmoji = veryExtremelyHappyEmoji;
        } else if (ss > .6 && ss <= .9) { // between .9 and .6
            this.sentimentEmoji = veryHappyEmoji;
        } else if (ss > .3 && ss <= .6) { // between .6 and .3
            this.sentimentEmoji = slightlyHappyEmoji;
        } else if (ss < -.3 && ss > -.6) { // between -.3 and -.6
            this.sentimentEmoji = slightlyUnhappyEmoji;
        } else if (ss < -.6 && ss > -.9) { // between -.6 and -.9
            this.sentimentEmoji = veryUnhappyEmoji;
        } else if (ss < -.9) { // less than -.9
            this.sentimentEmoji = veryExtremelyUnhappyEmoji;
        } else { // between .3 and -.3
            this.sentimentEmoji = neutralEmoji;
        }
    }

    public String getSentimentEmoji() {
        return this.sentimentEmoji;
    }

    public void setSentimentLabel(String label) {
        this.sentimentLabel = label;
    }

    public String getSentimentLabel() {
        return this.sentimentLabel;
    }

    public Double getSentimentScore() {
        return this.sentimentScore;
    }

    public void setSentimentScore(Double score) {
        this.sentimentScore = score;
        setSentimentEmoji();
    }

}