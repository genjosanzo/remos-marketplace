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
package org.remus.marketplace.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.remus.marketplace.dao.gen.CategoryDao;
import org.remus.marketplace.dao.gen.MarketDao;
import org.remus.marketplace.entities.Category;
import org.remus.marketplace.entities.Market;
import org.remus.marketplace.entities.Node;
import org.remus.marketplace.xml.CategoryNode;
import org.remus.marketplace.xml.Marketplace;
import org.remus.marketplace.xml.NodeCategory;
import org.springframework.web.HttpRequestHandler;

/**
 * Servlet implementation class CategoryListing
 */

public class CategoryListing implements HttpRequestHandler {
	private static final long serialVersionUID = 1L;

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	private MarketDao marketDao;

	public void setMarketDao(MarketDao marketDao) {
		this.marketDao = marketDao;
	}

	private String serverPrefix;

	public void setServerPrefix(String serverPrefix) {
		this.serverPrefix = serverPrefix;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryListing() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void
			handleRequest(HttpServletRequest arg0, HttpServletResponse arg1)
					throws ServletException, IOException {
		String marketId = arg0.getParameter("marketId");
		arg1.setContentType("text/xml");
		int categoryId = 0;
		try {
			categoryId = Integer.parseInt(arg0.getParameter("categoryId"));
		} catch (Exception e1) {
			// do nothing
		}
		if (categoryId != 0) {
			Category findById = categoryDao.findById(categoryId);
			if (findById != null) {
				try {
					JAXBContext newInstance = JAXBContext.newInstance(
							Marketplace.class,
							org.remus.marketplace.xml.Market.class);

					Marketplace marketplace = new Marketplace();
					NodeCategory cat = new NodeCategory();

					cat.setId(String.valueOf(findById.getId()));
					cat.setName(findById.getName());
					cat.setUrl(serverPrefix + "taxonomy/term/" + marketId + ","
							+ categoryId);
					Set<Node> nodes = findById.getNodes();
					for (Node node : nodes) {
						CategoryNode node2 = new CategoryNode();
						node2.setFavorited(0);
						node2.setId(node.getId());
						node2.setName(node.getName());
						node2.setUrl(serverPrefix + "content/" + node.getId());
						cat.getNode().add(node2);
					}
					marketplace.setCategory(cat);
					newInstance.createMarshaller().marshal(marketplace,
							arg1.getOutputStream());
				} catch (JAXBException e) {
					throw new ServletException(e);
				}
			} else {
				throw new ServletException("Category not found");
			}
		} else if (marketId != null && marketId.trim().length() > 0) {
			try {
				JAXBContext newInstance = JAXBContext.newInstance(
						Marketplace.class,
						org.remus.marketplace.xml.Market.class);
				Market findById = marketDao.findById(marketId);
				if (findById == null) {
					throw new ServletException("market not found");
				}
				List<Category> findByMarketId = categoryDao.findByMarketId(
						marketId, null);
				Marketplace marketplace = new Marketplace();
				NodeCategory cat = new NodeCategory();

				cat.setId(findById.getId());
				cat.setName(findById.getName());
				cat.setUrl(serverPrefix + "category/markets/" + marketId);
				for (Category category : findByMarketId) {
					Set<Node> nodes = category.getNodes();
					for (Node node : nodes) {
						CategoryNode node2 = new CategoryNode();
						node2.setFavorited(0);
						node2.setId(node.getId());
						node2.setName(node.getName());
						node2.setUrl(serverPrefix + "content/" + node.getId());
						cat.getNode().add(node2);
					}
				}
				marketplace.setCategory(cat);
				newInstance.createMarshaller().marshal(marketplace,
						arg1.getOutputStream());
			} catch (JAXBException e) {
				throw new ServletException(e);
			}
		}

	}
}
