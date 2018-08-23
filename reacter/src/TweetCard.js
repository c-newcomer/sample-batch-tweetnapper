import React, { Component } from 'react';
import keygen from 'uuid/v4';

class TweetCard extends Component {

    render() { 
        var tweet= this.props.tweet;
        return ( <div style={this.props.style} key={tweet.key} className='tweet-box'>
                        <div className='tweet-box-content'>
                            <div className='tweet-popularity'>Popularity: {tweet.popularity}</div>
                            <div className='tweet-creation-date'><i className='fab fa-twitter-square'></i> {tweet.creationDate} in {tweet.accountLocation}</div>
                            <div className='tweet-status'>
                                <div className='tweet-username'><i className='fas fa-user-circle'> {tweet.realName}  (@{tweet.screenName})</i>:</div>
                                <div className='tweet-text-content'>{tweet.textContent} </div>
                                {tweet.imageUrl != null ?
                                    <div className='tweet-image' ><img alt="" src={tweet.imageUrl} /></div> : ""}
                            </div>
                            <div className='tweet-metadata'>
                                <div className='tweet-metadata-buttons'>
                                    <div className='tweet-metadata-favorites'><i className='fas fa-star'></i> {tweet.favoriteCount}</div>
                                    <div className='tweet-metadata-retweets'><i className='fas fa-retweet'></i> {tweet.retweetCount}</div>
                                </div>
                                <div className='tweet-metadata-emoji'>Sentiment: {tweet.sentiment.sentimentEmoji} ({tweet.sentiment.sentimentScore})</div> 
                            </div>
                        </div>
                    </div>
 
        )
    }
}
export default TweetCard; 