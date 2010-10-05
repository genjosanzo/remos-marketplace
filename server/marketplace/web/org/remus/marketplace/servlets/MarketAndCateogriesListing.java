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

import org.hibernate.criterion.Restrictions;
import org.remus.marketplace.dao.AdvancedCriteria;
import org.remus.marketplace.dao.gen.MarketDao;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.entities.Category;
import org.remus.marketplace.entities.Market;
import org.remus.marketplace.entities.Node;
import org.remus.marketplace.xml.Marketplace;
import org.remus.marketplace.xml.MarketplaceCategory;
import org.springframework.web.HttpRequestHandler;

/**
 * Servlet implementation class MarketAndCateogriesListing
 */
public class MarketAndCateogriesListing implements HttpRequestHandler {
	private static final long serialVersionUID = 1L;

	private MarketDao marketDao;

	private NodeDao nodeDao;

	public void setNodeDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
	}

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
	public MarketAndCateogriesListing() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		try {
			JAXBContext newInstance = JAXBContext.newInstance(
					Marketplace.class, org.remus.marketplace.xml.Market.class,
					MarketplaceCategory.class);

			Marketplace marketplace = new Marketplace();

			List<Market> find = marketDao
					.find(AdvancedCriteria.EMPTY_READONLY_INSTANCE);
			for (Market market : find) {
				org.remus.marketplace.xml.Market xmlMarket = new org.remus.marketplace.xml.Market();
				xmlMarket.setId(market.getId());
				xmlMarket.setName(market.getName());
				xmlMarket.setUrl(serverPrefix + "category/markets/"
						+ market.getId());
				Set<Category> categories = market.getCategories();
				for (Category category : categories) {
					MarketplaceCategory marketplaceCategory = new MarketplaceCategory();
					marketplaceCategory.setName(category.getName());
					marketplaceCategory.setId(category.getId());
					marketplaceCategory.setUrl(serverPrefix + "taxonomy/term/"
							+ market.getId() + "," + category.getId());
					marketplaceCategory
							.setCount(nodeDao.count(new AdvancedCriteria()
									.addSubCriteria(new AdvancedCriteria()
											.setAssosication(Node.CATEGORIES)
											.addRestriction(
													Restrictions.eq(
															Category.ID,
															category.getId())))));
					xmlMarket.getCategories().add(marketplaceCategory);
				}
				marketplace.getMarket().add(xmlMarket);
			}

			newInstance.createMarshaller().marshal(marketplace,
					response.getOutputStream());
		} catch (JAXBException e) {
			throw new ServletException(e);
		}

	}

	@Override
	public void
			handleRequest(HttpServletRequest arg0, HttpServletResponse arg1)
					throws ServletException, IOException {
		doPost(arg0, arg1);

	}

}
