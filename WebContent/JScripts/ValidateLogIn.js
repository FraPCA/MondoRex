function CheckRegularExpression(inputtxt, regxp)
{
	if(inputtxt.match(regxp)){
		return true;
	}
	else{
		return false;
	}
}

function CheckTheEmail(){
	var email_expr = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,7})+$/;
	let username = document.forms["form"]["username"].value;
	console.log(username);
	if (username == null || CheckRegularExpression(username, email_expr) == false){
		console.log(CheckRegularExpression(username, email_expr));
		document.forms["form"]["username"].focus();
		 document.getElementById("QuestaNonEunaEmail").innerHTML = "<i> ! Prego, inserisca una email valida</i>";
	 	return false;
	}
	
	 else {
		 document.getElementById("form").submit();
		 return true;
	 }
}
