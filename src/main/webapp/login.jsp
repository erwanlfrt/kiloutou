<%@ page language="java"%>

<html>
<head>
<form action="welcome" method="post">
  <div class="container">
    <label for="mailAddress"><b>Mail address</b></label>
    <input type="text" placeholder="Enter Username" name="mailAddress" required>

    <label for="pwd"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="pwd" required>

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


</html>