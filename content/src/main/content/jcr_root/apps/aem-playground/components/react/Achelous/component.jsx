var Achelous = React.createClass({
  render: function() {
    return (
      React.createElement("h2", null, "Hello from jsx")
    )
  }
});

var renderServer = function(message) {
  return ReactDOMServer.renderToString(React.createElement(Achelous));
};
