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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.xml.serialize.XMLSerializer;
import org.hibernate.criterion.Restrictions;
import org.remus.marketplace.dao.AdvancedCriteria;
import org.remus.marketplace.dao.gen.CategoryDao;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.entities.Category;
import org.remus.marketplace.entities.Node;
import org.remus.marketplace.xml.Featured;
import org.remus.marketplace.xml.Marketplace;
import org.remus.marketplace.xml.XMLBuilder;
import org.springframework.web.HttpRequestHandler;

/**
 * Servlet implementation class CategoryListing
 */

public class FeaturedListing implements HttpRequestHandler {
	private static final long serialVersionUID = 1L;

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	private NodeDao nodeDao;

	public void setNodeDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
	}

	private String serverPrefix;

	public void setServerPrefix(String serverPrefix) {
		this.serverPrefix = serverPrefix;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FeaturedListing() {
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
			Category category2search = categoryDao.findById(categoryId);
			if (category2search != null) {
				try {
					JAXBContext newInstance = JAXBContext.newInstance(
							Marketplace.class,
							org.remus.marketplace.xml.Market.class);

					Marketplace marketplace = new Marketplace();

					List<Node> findByCategoriesId = nodeDao
							.find(new AdvancedCriteria()
									.setMaxResults(10)
									.addRestriction(
											Restrictions.eq(
													Node.FOUNDATIONMEMBER, 1))
									.addSubCriteria(
											new AdvancedCriteria()
													.setAssosication(
															Node.CATEGORIES)
													.addRestriction(
															Restrictions
																	.eq(Category.ID,
																			category2search
																					.getId()))));
					Featured featured = new Featured();
					featured.setCount(findByCategoriesId.size());
					for (Node findById : findByCategoriesId) {
						org.remus.marketplace.xml.Node node = XMLBuilder
								.buildNode(serverPrefix, findById);
						featured.getNode().add(node);
					}
					marketplace.setFeatured(featured);
					Marshaller createMarshaller = newInstance
							.createMarshaller();
					XMLSerializer xmlSerializer = XMLBuilder
							.getXMLSerializer(arg1.getOutputStream());
					createMarshaller.marshal(marketplace,
							xmlSerializer.asContentHandler());
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

				List<Category> findByMarketId = categoryDao.findByMarketId(
						marketId, null);
				if (findByMarketId.size() == 0) {
					throw new ServletException("market id not matching");
				}
				List<Integer> categoryIds = new ArrayList<Integer>();
				for (Category category : findByMarketId) {
					categoryIds.add(category.getId());
				}
				Marketplace marketplace = new Marketplace();

				List<Node> findByCategoriesId = nodeDao
						.find(new AdvancedCriteria()
								.setMaxResults(10)
								.addRestriction(

								Restrictions.eq(Node.FOUNDATIONMEMBER, 1))
								.addSubCriteria(
										new AdvancedCriteria().setAssosication(
												Node.CATEGORIES)
												.addRestriction(
														Restrictions.in(
																Category.ID,
																categoryIds))));

				Featured featured = new Featured();
				featured.setCount(findByCategoriesId.size());
				for (Node findById : findByCategoriesId) {
					org.remus.marketplace.xml.Node node = XMLBuilder.buildNode(
							serverPrefix, findById);
					featured.getNode().add(node);
				}
				marketplace.setFeatured(featured);
				Marshaller createMarshaller = newInstance.createMarshaller();
				XMLSerializer xmlSerializer = XMLBuilder.getXMLSerializer(arg1
						.getOutputStream());
				createMarshaller.marshal(marketplace,
						xmlSerializer.asContentHandler());
			} catch (JAXBException e) {
				throw new ServletException(e);
			}
		} else {
			try {
				JAXBContext newInstance = JAXBContext.newInstance(
						Marketplace.class,
						org.remus.marketplace.xml.Market.class);

				Marketplace marketplace = new Marketplace();

				List<Node> findByCategoriesId = nodeDao
						.find(new AdvancedCriteria().addRestriction(
								Restrictions.eq(Node.FOUNDATIONMEMBER, 1))
								.setMaxResults(10));

				Featured featured = new Featured();
				featured.setCount(findByCategoriesId.size());
				for (Node findById : findByCategoriesId) {
					org.remus.marketplace.xml.Node node = XMLBuilder.buildNode(
							serverPrefix, findById);
					featured.getNode().add(node);
				}
				marketplace.setFeatured(featured);
				Marshaller createMarshaller = newInstance.createMarshaller();
				XMLSerializer xmlSerializer = XMLBuilder.getXMLSerializer(arg1
						.getOutputStream());
				createMarshaller.marshal(marketplace,
						xmlSerializer.asContentHandler());
			} catch (JAXBException e) {
				throw new ServletException(e);
			}
		}
	}
}
