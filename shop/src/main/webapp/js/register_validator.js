var EMAIL_PATTERN = /^[\w\.\d-_]+@[\w\.\d-_]+\.\w{2,4}$/i;

function resetErrorJSNew(name) {
	errItem = document.getElementById(name + '_err_id');
	errItem.innerHTML = '';
	container = errItem.parentNode;
	container.className = '';
}

function showErrorJSNew(name, errorMessage) {
	errItem = document.getElementById(name + '_err_id');
	errItem.innerHTML = errorMessage;
	container = errItem.parentNode;
	container.className = 'error';
}

function validateJS(form) {
	var elems = form.elements;
	var valid = true;

	resetErrorJSNew(elems.name.name);
	if (!elems.name.value) {
		showErrorJSNew(elems.name.name, document
				.getElementById('res_error_name_empty').value);
		valid = false;
	}

	resetErrorJSNew(elems.lastName.name);
	if (!elems.lastName.value) {
		showErrorJSNew(elems.lastName.name, document
				.getElementById('res_error_lastName_empty').value);
		valid = false;
	}

	resetErrorJSNew(elems.login.name);
	if (!elems.login.value) {
		showErrorJSNew(elems.login.name, document
				.getElementById('res_error_email_empty').value);
		valid = false;
	} else if (!EMAIL_PATTERN.test(elems.login.value)) {
		showErrorJSNew(elems.login.name, document
				.getElementById('res_error_email_incorrect').value);
		valid = false;
	}

	resetErrorJSNew(elems.password.name);
	if (!elems.password.value) {
		showErrorJSNew(elems.password.name, document
				.getElementById('res_error_pwd_empty').value);
		valid = false;
	} else if (elems.password.value != elems.rePassword.value) {
		showErrorJSNew(elems.password.name, document
				.getElementById('res_error_pwd_mismatch').value);
		valid = false;
	}

	resetErrorJSNew(elems.capcha.name);
	if (!elems.capcha.value) {
		showErrorJSNew(elems.capcha.name, document
				.getElementById('res_error_capcha_empty').value);
		valid = false;
	}

	resetErrorJSNew(elems.avatar.name);
	if (elems.avatar.value.length > 0) {
		var ext = elems.avatar.value;
		ext = ext.substring(ext.length - 3, ext.length);
		ext = ext.toLowerCase();
		if (ext != 'jpg') {
			showErrorJSNew(elems.avatar.name, document
					.getElementById('res_error_avatar_bad_format').value);
			valid = false;
		}
	}

	return valid;
}

function checkLogin(loginElement) {
	if (!loginElement.value) {
		return;
	}
	resetErrorJSNew(loginElement.name);
	$.ajax({
		url : document.getElementById('login_check_url').value,
		data : {'login' : loginElement.value}
	}).done(function(resp) {
		if (resp.empty) {
			showErrorJSNew(loginElement.name, document.getElementById('res_error_email_empty').value);
		} else if (resp.exist) {
			showErrorJSNew(loginElement.name, document.getElementById('res_error_email_already_in_use').value);
		}
	}).fail(function () {
		showErrorJSNew(loginElement.name, document.getElementById('res_error_email_check_fail').value);
	});
	
}