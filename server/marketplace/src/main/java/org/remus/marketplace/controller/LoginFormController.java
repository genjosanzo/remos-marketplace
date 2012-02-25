package org.remus.marketplace.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.remus.marketplace.form.LoginFormData;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class LoginFormController extends SimpleFormController {

	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		LoginFormData formData = (LoginFormData) command;
		// request.getSession().setAttribute("FORM_DATA", formData);
		request.getSession().setAttribute("inAdminMode", Boolean.TRUE);
		ModelAndView modelAndView = new ModelAndView(new RedirectView(
				getSuccessView(), true));
		return modelAndView;
	}

}
