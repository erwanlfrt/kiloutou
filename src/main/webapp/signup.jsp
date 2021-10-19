<%@ page language="java"%>
<%@page import="java.util.Date"%>

<html>
<head>
</head>
<body>
	<form action="register" method="post">
		<div class="container">
		    <label for="mailAddress"><b>Mail address</b></label>
		    <input type="text" placeholder="example@gmail.com" name="mailAddress" required>
		
			<label for="name">Name</label>
			<input type="text" placeholder="your name" name="name" required>
			
			<label for="firstname">Firstame</label>
			<input type="text" placeholder="your firstname" name="firstname" required>
			
			<label for="address">Address</label>
			<input type="text" placeholder="your address" name="address" required>
			
			<label for="phoneNumber">Phone number</label>
			<input type="text" placeholder="your phone number" name="phoneNumber" required>
			
			<label for="login">Login</label>
			<input type="text" placeholder="your login" name="login" required>
			
		    <label for="pwd"><b>Password</b></label>
		    <input type="password" placeholder="" name="pwd" required>
		    
		    <button type="submit">Login</button>
	  </div>

	</form>
</body>
</html>
