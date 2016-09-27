import React, { Component } from 'react'
import { Link } from 'react-router'

import style from './style.css'

class Header extends Component {
  render() {
    return (
      <nav className={style.nav}>
        <ul className={style.list}>
          <li><Link to="home">Home</Link></li>
          <li><Link to="users">Users</Link></li>
        </ul>
      </nav>
    )
  }
}

export default Header
