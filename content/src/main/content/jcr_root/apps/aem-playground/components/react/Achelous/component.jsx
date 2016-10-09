// import React, { Component } from 'react'

var React = require('react')

var Achelous = React.createClass({
  render: function() {
    return (
      <div>
        <h1>I am Achelous!</h1>
        <p>Here is the message:</p>
      </div>
    )
  }
})

// class Achelous extend Component {
//   render() {
//     const data = this.property.data
//     return (
//       <div>
//         <h1>I am Achelous!</h1>
//         <p>Here is the message:</p>
//         <p>{data}</p>
//       </div>
//     )
//   } 
// }

var renderClient = function(data) {
  return React.render(
    <Achelous message={data} />
  )
}
