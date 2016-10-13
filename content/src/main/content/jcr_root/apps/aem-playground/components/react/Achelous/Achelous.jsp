<%@include file="/libs/foundation/global.jsp" %>
<%@ page import="com.lazurey.www.samples.foundation.Achelous;" %>
<%= new Achelous((Node)((Resource)component.adaptTo(Resource.class)).adaptTo(Node.class), currentNode).render() %>

<h3>Achelous Summary</h3>
<p>This component is an example of using React server render.</p>
