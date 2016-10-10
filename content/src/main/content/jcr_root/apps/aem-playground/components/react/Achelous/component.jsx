var Achelous = React.createClass({
  propTypes: {
    message: React.PropTypes.string,
    dataUri: React.PropTypes.string
  },
  getInitialState: function() {
    return {
      message: 'no client side message'
    }
  },
  componentDidMount: function() {
    if (!!this.props.dataUri) {
      $.get(this.props.dataUri, function(result) {
        this.setState({
          message: result.message
        })
      }.bind(this))
    }
  },
  serverRender: function() {
    return React.createElement("h2", null, "Hello you have a message: " + this.props.message);
  },
  clientRender: function() {
    return React.createElement("h2", null, "Hello you have a message: " + this.state.message)
  },
  render: function() {
    return (!!this.props.dataUri) ? this.clientRender() : this.serverRender();
  }
});

var renderServer = function(data) {
  return ReactDOMServer.renderToString(React.createElement(Achelous, JSON.parse(data)));
};

var renderClient = function(dataUri) {
  return React.render(React.createElement(Achelous, {dataUri: dataUri}), document.getElementbyId('comp-placeholder'));
};
