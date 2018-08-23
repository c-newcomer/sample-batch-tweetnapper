import React, { Component } from 'react';
import keygen from 'uuid/v4';

class LoadCard extends Component {

    render() {

        return (<div key={keygen()} className='tweet-box' onClick={this.props.handleClick} value="load-more" >
            <div className='tweet-box-content'>
                {/* <div className='load-tweets'>Load More Tweets</div> */}
            </div>
        </div>
        )
    }
}
export default LoadCard; 