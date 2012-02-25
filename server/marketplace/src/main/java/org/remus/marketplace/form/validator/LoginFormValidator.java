package org.remus.marketplace.form.validator;

import org.remus.marketplace.form.LoginFormData;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LoginFormValidator implements Validator {

	private String userName;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String password;

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		LoginFormData loginData = (LoginFormData) obj;

		if (loginData.getUsername() == null
				|| loginData.getUsername().length() == 0) {
			errors.rejectValue("username", "error.empty.field",
					"empty username");
		}
		if (loginData.getPassword() == null
				|| loginData.getPassword().length() == 0) {
			errors.rejectValue("password", "error.empty.field",
					"empty password");
		}

		if (!errors.hasErrors()
				&& (!userName.equals(loginData.getUsername()) || !password
						.equals(loginData.getPassword()))) {
			errors.rejectValue("password", "wrong.password", "wrong password");
		}

	}

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(LoginFormData.class);
	}
}
