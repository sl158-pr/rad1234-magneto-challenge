<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Magneto Customer Registration Web Service</title>
<meta name="description" content="Default Description" />
<link href="css/styles.css" rel="stylesheet" type="text/css"></link>

</head>
<body>
<div class="pagebody">
		<div class="page-title">
			<h1>Create an Account</h1>
</div>
<c:if test="${!empty messages}">
<ul><c:out value="${messages}" escapeXml="false" /></ul>
</c:if>
<c:if test="${!empty success && success}"><h1>User Successfully Created</h1></c:if>
<c:if test="${empty success || !success}">
	
		<form action="${pageContext.request.contextPath}/submit" method="post">
			<div style="width:90%">
				<fieldset>
					<legend>Personal Information</legend>
					<div style="width:100%">
						<div style="width:49%;float:left;">
							<div style="width:45%; "> <label> First Name <em>*</em> </label></div>
							<div style="width:50%"><input type="text" name="firstName" id="firstName" value="${param.firstName}" /></div>
						</div>
						<div style="width:49%; float:left;">
							<div style="width:45%; "> <label> Last Name <em>*</em> </label></div>
							<div style="width:50%"><input type="text" name="lastName" id="lastName" value="${param.lastName}" /></div>
						</div>
					</div>
					<div style="width:100%">
						<div style="width:100%;float:left;">
							<div style="width:45%; "> <label> Email Address <em>*</em> </label></div>
							<div style="width:50%"><input type="email" name="email" id="email" value="${param.email}" /></div>
						</div>
					</div>
					<div style="width:100% float:left;">
						<input type="checkbox" name="newsletter" id="newsletter"></input>
						<label style="width: 100px">Sign up for newsletter</label><br />
					</div>			
				</fieldset>
			</div>
			<div style="width: 90%">
				<fieldset>
					<legend>Login Information</legend>
					<div style="width:100%">
						<div style="width:49%;float:left;">
							<div style="width:45%; "> <label> Password <em>*</em> </label></div>
							<div style="width:50%"><input type="password" name="password" id="password" /></div>
						</div>
						<div style="width:49%; float:left;">
							<div style="width:45%; "> <label> Confirm Password <em>*</em> </label></div>
							<div style="width:50%"><input type="password" name="password2" id="password2" /></div>
						</div>
					</div>
				</fieldset>
			</div>
			<div>&nbsp;</div>
			<div id="errMessage" style="color:red;"></div>
			<div id="successMessage" ></div>
			
			
			<div style="width:100%;text-align:center;">
			<input type="submit" value="Submit"></input>
			</div>
			<div>&nbsp;</div>
		</form>
		
	</div>
	</c:if>
</body>
</html>