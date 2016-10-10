var Achelous = React.createClass({
  propTypes: {
    message: React.PropTypes.string
  },
  render: function() {
    return (
      React.createElement("h2", null, "Hello you have a message: " + this.props.message)
    )
  }
});

var renderServer = function(data) {
  return ReactDOMServer.renderToString(React.createElement(Achelous, JSON.parse(data)));
};
