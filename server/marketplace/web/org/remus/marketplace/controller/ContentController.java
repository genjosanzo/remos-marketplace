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
package org.remus.marketplace.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.remus.marketplace.dao.gen.ClickthroughDao;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.entities.Clickthrough;
import org.remus.marketplace.entities.Node;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class ContentController implements Controller {

	private NodeDao nodeDao;

	private String serverPrefix;

	private ClickthroughDao clickthroughDao;

	public void setNodeDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
	}

	public void setServerPrefix(String serverPrefix) {
		this.serverPrefix = serverPrefix;
	}

	public void setClickthroughDao(ClickthroughDao clickthroughDao) {
		this.clickthroughDao = clickthroughDao;
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
		Node findById = null;
		try {
			int contentId = Integer.parseInt(arg0.getParameter("nodeId"));
			findById = nodeDao.findById(contentId);
			if (findById != null) {
				Clickthrough click = new Clickthrough();
				click.setNode(findById);
				click.setTime(new Date());
				clickthroughDao.create(click);
			}
		} catch (Exception e) {
			// do nothing
		}
		return new ModelAndView("content", "node", findById);

	}

}
