import React, { Component } from 'react';
import { Helmet } from "react-helmet";
class AppHelmet extends Component {

    render() {
        return (
            <div>
                <Helmet defer={false}>
                    <link href="https://fonts.googleapis.com/css?family=Crimson+Text:400,400i,600,600i,700,700i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet" />
                    <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous" />
                </Helmet>
                <Helmet>                
                    <meta charset="utf-8" />
                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                    <title>IBM - Tweetnapper</title></Helmet>
            </div>)
    }
}
export default AppHelmet; 