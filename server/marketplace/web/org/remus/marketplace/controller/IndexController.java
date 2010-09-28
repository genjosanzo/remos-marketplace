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

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.remus.marketplace.dao.AdvancedCriteria;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.entities.Node;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class IndexController implements Controller {

	private NodeDao nodeDao;

	public void setNodeDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
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

		List<Node> featured = nodeDao.find(new AdvancedCriteria()
				.addRestriction(Restrictions.eq(Node.FOUNDATIONMEMBER, 1))
				.setMaxResults(10));

		List<Object> popular = nodeDao.query(new AdvancedCriteria()
				.setMaxResults(10)
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.groupProperty(Node.ID),
										"dl_node")
								.add(Projections.alias(Projections.rowCount(),
										"downloadCount")))
				.addSubCriteria(
						new AdvancedCriteria().setAssosication(Node.DOWNLOADS)

						.addOrder(Order.desc("downloadCount"))));
		List<Popular> popularResponse = new ArrayList<IndexController.Popular>();
		for (Object object : popular) {
			Popular popular2 = new Popular();
			popular2.setCount((Integer) ((Object[]) object)[1]);
			popular2.setId((Integer) ((Object[]) object)[0]);
			popular2.setName(nodeDao.findById(popular2.getId()).getName());
			popularResponse.add(popular2);
		}

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("featured", featured);
		response.put("popular", popularResponse);
		return new ModelAndView("index", response);

	}

	public static class Popular {
		private String name;

		private int count;

		private int id;

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the count
		 */
		public int getCount() {
			return count;
		}

		/**
		 * @param count
		 *            the count to set
		 */
		public void setCount(int count) {
			this.count = count;
		}

		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}
	}

}
