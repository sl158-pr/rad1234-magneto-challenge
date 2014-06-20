<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Registration</title>
<style>
/* Basics */
html,body {
	width: 100%;
	height: 100%;
	font-family: "Helvetica Neue", Helvetica, sans-serif;
	color: #444;
	-webkit-font-smoothing: antialiased;
	background: #f0f0f0;
}

#container {
	position: fixed;
	width: 470px;
	height: 580px;
	top: 50%;
	left: 50%;
	margin-top: -140px;
	margin-left: -170px;
	background: #fff;
	border-radius: 3px;
	border: 1px solid #ccc;
	box-shadow: 0 1px 2px rgba(0, 0, 0, .1);
}

form {
	margin: 0 auto;
	margin-top: 20px;
}

label {
	color: #555;
	display: inline-block;
	margin-left: 18px;
	padding-top: 10px;
	font-size: 14px;
}

p a {
	font-size: 11px;
	color: #aaa;
	float: right;
	margin-top: -13px;
	margin-right: 20px;
	-webkit-transition: all .4s ease;
	-moz-transition: all .4s ease;
	transition: all .4s ease;
}

p a:hover {
	color: #555;
}

input {
	font-family: "Helvetica Neue", Helvetica, sans-serif;
	font-size: 12px;
	outline: none;
}

input[type=text],input[type=password],input[type=time] {
	color: #777;
	padding-left: 10px;
	margin: 10px;
	margin-top: 12px;
	margin-left: 18px;
	width: 290px;
	height: 35px;
	border: 1px solid #c7d0d2;
	border-radius: 2px;
	box-shadow: inset 0 1.5px 3px rgba(190, 190, 190, .4), 0 0 0 5px #f5f7f8;
	-webkit-transition: all .4s ease;
	-moz-transition: all .4s ease;
	transition: all .4s ease;
}

input[type=text]:hover,input[type=password]:hover,input[type=time]:hover
	{
	border: 1px solid #b6bfc0;
	box-shadow: inset 0 1.5px 3px rgba(190, 190, 190, .7), 0 0 0 5px #f5f7f8;
}

input[type=text]:focus,input[type=password]:focus,input[type=time]:focus
	{
	border: 1px solid #a8c9e4;
	box-shadow: inset 0 1.5px 3px rgba(190, 190, 190, .4), 0 0 0 5px #e6f2f9;
}

#lower {
	background: #ecf2f5;
	width: 100%;
	height: 69px;
	margin-top: 20px;
	box-shadow: inset 0 1px 1px #fff;
	border-top: 1px solid #ccc;
	border-bottom-right-radius: 3px;
	border-bottom-left-radius: 3px;
}

input[type=checkbox] {
	margin-left: 20px;
	margin-top: 30px;
}

.check {
	margin-left: 3px;
	font-size: 11px;
	color: #444;
	text-shadow: 0 1px 0 #fff;
}

input[type=submit] {
	float: right;
	margin-right: 20px;
	margin-top: 20px;
	width: 80px;
	height: 30px;
	font-size: 14px;
	font-weight: bold;
	color: #fff;
	background-color: #acd6ef; /*IE fallback*/
	background-image: -webkit-gradient(linear, left top, left bottom, from(#acd6ef),
		to(#6ec2e8));
	background-image: -moz-linear-gradient(top left 90deg, #acd6ef 0%, #6ec2e8 100%);
	background-image: linear-gradient(top left 90deg, #acd6ef 0%, #6ec2e8 100%);
	border-radius: 30px;
	border: 1px solid #66add6;
	box-shadow: 0 1px 2px rgba(0, 0, 0, .3), inset 0 1px 0
		rgba(255, 255, 255, .5);
	cursor: pointer;
}

input[type=submit]:hover {
	background-image: -webkit-gradient(linear, left top, left bottom, from(#b6e2ff),
		to(#6ec2e8));
	background-image: -moz-linear-gradient(top left 90deg, #b6e2ff 0%, #6ec2e8 100%);
	background-image: linear-gradient(top left 90deg, #b6e2ff 0%, #6ec2e8 100%);
}

input[type=submit]:active {
	background-image: -webkit-gradient(linear, left top, left bottom, from(#6ec2e8),
		to(#b6e2ff));
	background-image: -moz-linear-gradient(top left 90deg, #6ec2e8 0%, #b6e2ff 100%);
	background-image: linear-gradient(top left 90deg, #6ec2e8 0%, #b6e2ff 100%);
}

.reset {
	float: right;
	margin-right: 20px;
	margin-top: 20px;
	width: 80px;
	height: 30px;
	font-size: 14px;
	font-weight: bold;
	color: #fff;
	background-color: #acd6ef; /*IE fallback*/
	background-image: -webkit-gradient(linear, left top, left bottom, from(#acd6ef),
		to(#6ec2e8));
	background-image: -moz-linear-gradient(top left 90deg, #acd6ef 0%, #6ec2e8 100%);
	background-image: linear-gradient(top left 90deg, #acd6ef 0%, #6ec2e8 100%);
	border-radius: 30px;
	border: 1px solid #66add6;
	box-shadow: 0 1px 2px rgba(0, 0, 0, .3), inset 0 1px 0
		rgba(255, 255, 255, .5);
	cursor: pointer;
}
</style>

</head>

<body>

	<!-- Begin Page Content -->
	<div id="container">
	<h2> Magneto Clone SignUp</h2>
	
		 <form method="post" action="SignUp">
         <input type="hidden" name="flag" value="1" />
		<label for="username">First Name:</label>
			<input type="text" name="fname" value="" /> <br>
			<label for="password">Last Name:</label>
			<input type="text" name="lname" value="" /><br>
			<label for="username">Email</label>
			<input type="text" name="email" value="" /><br>
			<label for="password">Password</label>
			<input type="password" name="pass" value="" /><br>
			<label for="password">Confirm Password</label>
			<input type="password" name="conpass" value="" /><br>
			
			<div id="lower">
				<input type="submit" value="Submit" /><input type="reset"
					value="Reset" class="reset" /> <a href="index.jsp">Member Login</a>
			</div>
		</form>
	</div>
	<!--/ container-->
	<!-- End Page Content -->
</body>
</html>