# Tweetnapper sample for batch-1.0 on Liberty

Tweetnapper is a fun sample application demonstrating Liberty's implementation of the Batch Programming Model in Java EE 7, as specified by JSR 352. 

Tweetnapper lets you capture Tweets about a topic and batch process them to analyze their sentiment and popularity. After processing, the results are readily viewable in the browser. The default topic is the New York City MTA, since it is an eternally popular social media topic with a high entertainment factor.

Disclaimer/Warning: Twitter users express a wide spectrum of emotions, often using rather colorful language. Since this application involves ingesting the contents of the internet, the material may not always be PG-rated.

## Setting Up the API Keys

To access the Twitter API, you will need to set up credentials. Place these in the twitter4j.properties file. 

https://developer.twitter.com/en/apply-for-access

To access the Watson Natural Language Understanding API, you will need to set up another set of credentials. Place these in the WatsonKeys.java file. 

https://www.ibm.com/watson/developercloud/natural-language-understanding/api/v1/#authentication

https://console.bluemix.net/docs/services/watson/getting-started-credentials.html#creating-credentials

## Collect the Tweets

Use the included "snatcher" utility by changing to the "snatcher" directory and running: 

```mvn clean install```
 
```mvn:exec java``` 

This will use Twitter4J to start up a stream listening to Twitter for Tweets about the New York City subway system.

The tweet snatcher will save incoming tweets that match your desired criteria to the temp folder in a file containing the date and time. When the time period has elapsed, the old file is moved to the "batch-source" folder. When you're ready to combine all the tweet files into one, simply restart tweet snatcher and the previous output will be concatted into a single tweetArchive-collection.txt in the batch-ready folder at startup. 

Allow the snatcher to run for as long as you please, then begin the batch process/visualization step. 

## Process and Display the Tweets

Copy ```tweetArchive-collection.txt``` from snatcher's batch-ready directory into the app's src/main/resources/batch-sources folder. 

Open the app's root directory and type: 

```mvn clean install``` (if you made any changes to the settings)

Followed by:

```mvn liberty:run-server```

Wait for the batch processing to complete. 

Open your browser to localhost:9080. 

Admire your tweets!

## Modifying the Front End

Go to the reacter folder and ```npm install```. Run ```npm start``` to preview your changes.  The react development server uses port 3000 by default, but will automatically proxy any api calls to Fetcher's REST API on port 9080. When you have made your changes, do a ```npm run build``` to compile. Move the contents of reacter's build folder into the webapp folder, overwriting the older files.  

## Modifying the Number of Tweets Displayed

Set "startingPoint" and "endingPoint" to your desired values in fetcher/TweetService.java.

## Toggle Viewing Tweets as They Come In

Set showContents in snatcher/StreamTweets.java to true/false. 

## Temporarily Disable Sentiment Analysis

Set useWatson to false in the TestProcessor. 