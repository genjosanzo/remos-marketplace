/*******************************************************************************
 * Copyright (c) 2010 Tom Seidel, Remus Software
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
package org.remus.marketplace.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.remus.marketplace.dao.AdvancedCriteria;
import org.remus.marketplace.dao.gen.MarketDao;
import org.remus.marketplace.entities.Market;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class MarketAdminController implements Controller {

	MarketDao marketDao;

	public void setMarketDao(MarketDao marketDao) {
		this.marketDao = marketDao;
	}

	private String serverPrefix;

	public void setServerPrefix(String serverPrefix) {
		this.serverPrefix = serverPrefix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {

		List<Market> find = marketDao
				.find(AdvancedCriteria.EMPTY_READONLY_INSTANCE);

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("markets", find);

		return new ModelAndView("admin/markets", response);

	}

}
