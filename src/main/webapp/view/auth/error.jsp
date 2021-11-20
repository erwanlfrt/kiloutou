<%@ page language="java" pageEncoding="UTF-8"%>
<%
  String message = (String) request.getAttribute("message");
%>
<html>
  <head>
    <title>Error</title>
  </head>
  <body>
      <h1>Error</h1>
      <h3><%= message != null ? message : ""%></h3>
  </body>
</html>