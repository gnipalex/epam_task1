var EMAIL_PATTERN = /^[\w\.\d-_]+@[\w\.\d-_]+\.\w{2,4}$/i;

function resetErrorJS(container) {
	container.className = '';
	if (container.lastChild.className == 'error_message') {
		container.removeChild(container.lastChild);
	}
}

function showErrorJS(container, errorMessage) {
	container.className = 'error';
	var msgElem = document.createElement('div');
	msgElem.className = "error_message";
	msgElem.innerHTML = errorMessage;
	container.appendChild(msgElem);
}

function validateJS(form) {
	var elems = form.elements;
	var valid = true;

	resetErrorJS(elems.name.parentNode);
	if (!elems.name.value) {
		showErrorJS(elems.name.parentNode, ' specify name');
		valid = false;
	}
	
	resetErrorJS(elems.lastName.parentNode);
	if (!elems.lastName.value) {
		showErrorJS(elems.lastName.parentNode, ' specify last name');
		valid = false;
	}

	resetErrorJS(elems.email.parentNode);
	if (!elems.email.value) {
		showErrorJS(elems.email.parentNode, ' specify email');
		valid = false;
	} else if (!EMAIL_PATTERN.test(elems.email.value)) {
		showErrorJS(elems.email.parentNode, ' email is incorrect');
		valid = false;
	}
	
	resetErrorJS(elems.password.parentNode);
	if (!elems.password.value) {
		showErrorJS(elems.password.parentNode, ' specify password');
		valid = false;
	} else if (elems.password.value != elems.rePassword.value) {
		showErrorJS(elems.password.parentNode, ' passwords does not match');
		valid = false;
	}
	
	return valid;
}

function validateJQ() {
	var valid = true;
	resetAllErrorsJQ();
	$('#register_form_id input').each(function(){
		switch(this.name) {
		case 'name':
			if (!this.value) {
				showErrorJS(this.parentNode, ' specify name');
				valid = false;
			}
			break;
		case 'lastName':
			if (!this.value) {
				showErrorJS(this.parentNode, ' specify last name');
				valid = false;
			}
			break;
		case 'email':
			if (!this.value) {
				showErrorJS(this.parentNode, ' specify email');
				valid = false;
			} else if (!EMAIL_PATTERN.test(this.value)) {
				showErrorJS(this.parentNode, ' email is incorrect');
				valid = false;
			}
			break;
		case 'password':
			if (!this.value) {
				showErrorJS(this.parentNode, ' specify password');
				valid = false;
			} else if (this.value != $("#register_form_id input[name='rePassword']").val() ) {
				showErrorJS(this.parentNode, ' passwords does not match');
				valid = false;
			}
			break;
			default:
		}
	});
	return valid;
}

function resetAllErrorsJQ() {
	$("#register_form_id .error_message").parent().removeClass();
	$("#register_form_id .error_message").remove();
}