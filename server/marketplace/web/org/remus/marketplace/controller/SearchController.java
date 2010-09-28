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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.entities.Node;
import org.remus.marketplace.indexing.IndexService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class SearchController implements Controller {

	private NodeDao nodeDao;

	public void setNodeDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
	}

	private String serverPrefix;

	public void setServerPrefix(String serverPrefix) {
		this.serverPrefix = serverPrefix;
	}

	private IndexService indexService;

	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
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
		String parameter = arg0.getParameter("query");
		List<Integer> searchNodes = indexService
				.searchNodes(parameter, 0, null);
		List<Node> returnNodes = new ArrayList<Node>();
		for (Integer integer : searchNodes) {
			Node findById = nodeDao.findById(integer);
			findById.setUrl(serverPrefix + "content/" + findById.getId());
			returnNodes.add(findById);
		}
		String query = StringEscapeUtils.escapeHtml(parameter);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("query", query);
		response.put("node", returnNodes);
		return new ModelAndView("searchResult", response);

	}

}
