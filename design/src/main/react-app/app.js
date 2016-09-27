import React, { Component } from 'react'
import ReactDOM from 'react-dom'
import { Router, Route, browserHistory } from 'react-router'

import Container from './app/pages/Container'
import Home from './app/pages/Home'
import Users from './app/pages/Users'
import User from './app/pages/User'
import NoMatch from './app/pages/NoMatch'

class App extends Component {
  render() {
    return (
      <Router history={browserHistory}>
        <Route path="/" component={Container}>
          <Route path="home" component={Home}/>
          <Route path="users" component={Users}>
            <Route path="/user/:userId" component={User}/>
          </Route>
          <Route path="*" component={NoMatch}/>
        </Route>
      </Router>
    )
  }
}

ReactDOM.render(<App />, document.getElementById('app'));
