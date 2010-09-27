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
import org.remus.marketplace.dao.AdvancedCriteria;
import org.remus.marketplace.dao.gen.CategoryDao;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.entities.Node;
import org.remus.marketplace.xml.Marketplace;
import org.remus.marketplace.xml.Recent;
import org.remus.marketplace.xml.XMLBuilder;
import org.springframework.web.HttpRequestHandler;

/**
 * Servlet implementation class CategoryListing
 */

public class RecentListing implements HttpRequestHandler {
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
	public RecentListing() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void
			handleRequest(HttpServletRequest arg0, HttpServletResponse arg1)
					throws ServletException, IOException {

		try {
			JAXBContext newInstance = JAXBContext.newInstance(
					Marketplace.class, org.remus.marketplace.xml.Market.class);

			Marketplace marketplace = new Marketplace();

			List<Node> findByCategoriesId = nodeDao.find(new AdvancedCriteria()
					.setMaxResults(10).addOrder(Order.desc(Node.CHANGED)));

			Recent recent = new Recent();
			recent.setCount(findByCategoriesId.size());
			for (Node findById : findByCategoriesId) {
				org.remus.marketplace.xml.Node node = XMLBuilder.buildNode(
						serverPrefix, findById);
				recent.getNode().add(node);
			}
			marketplace.setRecent(recent);
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
