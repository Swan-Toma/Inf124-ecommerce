<%@ page language="java" %>

<%@ page import="java.util.ArrayList, javax.servlet.http.HttpSession" %>
<%
HttpSession s = request.getSession(true);
if (s.getAttribute("cart") == null) {
    s.setAttribute("cart", new ArrayList<String>());
}
ArrayList<String> pids = (ArrayList<String>) s.getAttribute("cart");
int cartSize = pids.size();


%>

<div class="navbar">
	<div class="logo">
		<img src="imgs/ant_logo.png" alt="Ants R Us Logo"></div>
	<div class="pages">
		<ul class="navigation">
			<li><a href="./index.jsp">Home</a></li>
			<li><a href="./pages/about.html">About</a></li>
			<li><a href="./checkout.jsp">Cart (<%= cartSize %>)</a></li>
		</ul>
	</div>
</div>