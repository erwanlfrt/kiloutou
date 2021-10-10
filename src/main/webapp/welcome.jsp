<%@ page language="java"%>
<%@page import="java.util.List,model.object.user.User"%>
<%
List<User> users = (List<User>)request.getAttribute("users");
%>
<html>
<head>
<form action="" method="post">
  <div class="container">
    <label for="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="uname" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required>

    <button type="submit">Login</button>
    <label>
      <input type="checkbox" checked="checked" name="remember"> Remember me
    </label>
  </div>

  <div class="container" style="background-color:#f1f1f1">
    <button type="button" class="cancelbtn">Cancel</button>
    <span class="psw">Forgot <a href="#">password?</a></span>
  </div>
</form>

<table border="1">
<tr>
<th>Login</th>
<th>Password</th>
</tr>
<%
for (User user : users) {
String login = user.getLogin();
String password = user.getPassword();
%>
<tr>
<td><%=login %></td>
<td><%=password %></td>
</tr>
<%
}
%>
</table>
</html>