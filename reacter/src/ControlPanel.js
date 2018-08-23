import React, { Component } from 'react';

class ControlPanel extends Component {

    render() {
        return (
            <section className="container control-panel">
                <button id="button-top-tweets" onClick={this.props.handleClick} value="popularity" ><i className="fas fa-fire"> popular</i></button>
                <button id="button-recent-tweets" onClick={this.props.handleClick} value="recent" ><i className="fas fa-clock"> recent</i></button>
            </section>)
    }
}

export default ControlPanel; 