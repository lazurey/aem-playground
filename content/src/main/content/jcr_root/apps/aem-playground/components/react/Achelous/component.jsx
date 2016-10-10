var Achelous = React.createClass({
  propTypes: {
    message: React.PropTypes.string
  },
  render: function() {
    return (
      React.createElement("h2", null, "Hello you have a message: " + this.props.message)
    )
    // return (
      // <div>
        // <h2>Message box:<h2>
        // <p>this.props.message</p>
      // </div>
    // )
  }
});

var renderServer = function(data) {
  return ReactDOMServer.renderToString(React.createElement(Achelous, JSON.parse(data)));
};
