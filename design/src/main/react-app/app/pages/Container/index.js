import React, { Component } from 'react'

import Header from '../../components/Header'
import Footer from '../../components/Footer'

import style from './style.css'

class Container extends Component {
  render() {
    return (
      <div className={style.container}>
        <Header/>
        {this.props.children}
        <Footer/>
      </div>
    )
  }
}

export default Container
