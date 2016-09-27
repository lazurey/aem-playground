import React, { Component } from 'react'
import ReactDOM from 'react-dom'

import Home from './app/pages/Home'

class App extends Component {
  render() {
    return (<Home />)
  }
}

ReactDOM.render(<App />, document.getElementById('app'));
