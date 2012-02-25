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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.remus.marketplace.dao.AdvancedCriteria;
import org.remus.marketplace.dao.gen.CategoryDao;
import org.remus.marketplace.dao.gen.MarketDao;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.dao.gen.PlatformDao;
import org.remus.marketplace.entities.Category;
import org.remus.marketplace.entities.Market;
import org.remus.marketplace.entities.Node;
import org.remus.marketplace.entities.Platform;
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

	private NodeDao nodeDao;

	public void setNodeDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
	}

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	private PlatformDao platformDao;

	public void setPlatformDao(PlatformDao platformDao) {
		this.platformDao = platformDao;
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

		List<Object> query = nodeDao
				.query(new AdvancedCriteria().setProjection(
						Projections
								.projectionList()
								.add(Projections.groupProperty(Node.ID),
										"cat_node")
								.add(Projections.alias(Projections.rowCount(),
										"catCount")))
						.addSubCriteria(
								new AdvancedCriteria()
										.setAssosication(Node.CATEGORIES)));

		List<Node> orphans = new ArrayList<Node>();
		List<Integer> nonOrphan = new ArrayList<Integer>();
		for (Object object : query) {
			nonOrphan.add((Integer) ((Object[]) object)[0]);
		}
		if (nonOrphan.size() == 0) {
			orphans = nodeDao.find(AdvancedCriteria.EMPTY_READONLY_INSTANCE);
		} else {
			orphans = nodeDao.find(new AdvancedCriteria()
					.addRestriction(Restrictions.not(Restrictions.in(Node.ID,
							nonOrphan))));
		}
		List<Category> categories = categoryDao
				.find(AdvancedCriteria.EMPTY_READONLY_INSTANCE);

		List<Platform> platforms = platformDao
				.find(AdvancedCriteria.EMPTY_READONLY_INSTANCE);

		List<Node> nodes = nodeDao
				.find(AdvancedCriteria.EMPTY_READONLY_INSTANCE);

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("markets", find);
		response.put("orphans", orphans);
		response.put("platforms", platforms);
		response.put("categories", categories);
		response.put("solutions", nodes);

		return new ModelAndView("admin/markets", response);

	}

}
