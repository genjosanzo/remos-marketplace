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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.xml.serialize.XMLSerializer;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.remus.marketplace.dao.AdvancedCriteria;
import org.remus.marketplace.dao.gen.CategoryDao;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.entities.Node;
import org.remus.marketplace.xml.Favorite;
import org.remus.marketplace.xml.Marketplace;
import org.remus.marketplace.xml.Popular;
import org.remus.marketplace.xml.XMLBuilder;
import org.springframework.web.HttpRequestHandler;

/**
 * Servlet implementation class CategoryListing
 */

public class PopularListing implements HttpRequestHandler {
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
	public PopularListing() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void
			handleRequest(HttpServletRequest arg0, HttpServletResponse arg1)
					throws ServletException, IOException {
		arg1.setContentType("text/xml");
		try {
			JAXBContext newInstance = JAXBContext.newInstance(
					Marketplace.class, org.remus.marketplace.xml.Market.class);

			Marketplace marketplace = new Marketplace();

			List<Object> findByCategoriesId = nodeDao
					.query(new AdvancedCriteria()
							.setMaxResults(10)
							.setProjection(
									Projections
											.projectionList()
											.add(Projections
													.groupProperty(Node.ID),
													"dl_node")
											.add(Projections.alias(
													Projections.rowCount(),
													"downloadCount")))
							.addSubCriteria(
									new AdvancedCriteria().setAssosication(
											Node.DOWNLOADS)

									.addOrder(Order.desc("downloadCount"))));
			boolean favoriteMode = arg0.getParameter("favorites") != null
					&& Boolean.parseBoolean(arg0.getParameter("favorites"));
			Favorite favorite = null;
			Popular popular = null;
			if (favoriteMode) {
				favorite = new Favorite();
				favorite.setCount(findByCategoriesId.size());
			} else {
				popular = new Popular();
				popular.setCount(findByCategoriesId.size());
			}
			for (Object findById : findByCategoriesId) {
				Object[] rs = (Object[]) findById;
				int parseInt = Integer.parseInt(rs[0].toString());
				Node findById2 = nodeDao.findById(parseInt);
				org.remus.marketplace.xml.Node node = XMLBuilder.buildNode(
						serverPrefix, findById2);
				if (favoriteMode) {
					favorite.getNode().add(node);
				} else {
					popular.getNode().add(node);
				}
			}
			if (favoriteMode) {
				marketplace.setFavorites(favorite);
			} else {
				marketplace.setPopular(popular);
			}
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
