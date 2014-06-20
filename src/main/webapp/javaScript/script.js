function verifyAndSubmit() {
	// verify that required fields are entered
	var fname = document.getElementById("fname");
	var lname = document.getElementById("lname");
	var email = document.getElementById("email");
	var newsletter = document.getElementById("newsletter");
	var password = document.getElementById("password");
	var confirmPassword = document.getElementById("confirmPassword");
	
	if((!emptyOrNull(fname)) && (!emptyOrNull(lname) ) && (!emptyOrNull(email)) &&
			(!emptyOrNull(newsletter)) && (!emptyOrNull(password)) && (!emptyOrNull(confirmPassword))){
		if(password.value == confirmPassword.value) {
			return true;
		}else {
			document.getElementById("errMessage").innerHTML="Password values do not match.";			
		}		
	}else {
		document.getElementById("errMessage").innerHTML="Please enter all the required fields.";
	}
		
	return false;
}

function emptyOrNull(element) {
	if(element && element.value !='')
		return false;
	else
		return true;
}