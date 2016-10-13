(function($) {
  $(document).ready(function() {
    renderClient();
  });
})(jQuery);

function renderClient() {
  $('.component-placeholder').each(function() {
    var name = $(this).data('comp-name'),
        uri = $(this).data('uri');
    var container = $(this).get(0);
    $.get(uri, function(data) {
      ReactDOM.render(React.createElement(ReactMDL[name], null, data.message), container);
    });
  });
}
