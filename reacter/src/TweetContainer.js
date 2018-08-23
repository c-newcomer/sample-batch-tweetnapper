import React, { Component } from 'react';
import keygen from 'uuid/v4';
import { Spring } from 'react-spring';
import { Transition } from 'react-spring';
import { Trail } from 'react-spring';
import TweetCard from './TweetCard';
import LoadCard from './LoadCard';

class TweetContainer extends Component {

    render() {
        var tweets = this.props.tweets;
        return (
            <section id="tweet-container" className="container tweet-container" >
                {tweets.map(tweet => <TweetCard key ={tweet.key}tweet={tweet} />)}
                <LoadCard handleClick={this.props.handleClick} />
            </section>)
    }
}
export default TweetContainer;

{/* <Trail from={{ opacity: 0 }} to={{ opacity: 1 }} keys={items.map(item => item.key)}>
   {items.map(item => styles => <TweetCard style={styles}  tweet={item}/>)}
     </Trail> */}