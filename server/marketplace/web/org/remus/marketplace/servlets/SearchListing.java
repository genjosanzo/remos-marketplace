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
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.xml.serialize.XMLSerializer;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.entities.Node;
import org.remus.marketplace.indexing.IndexService;
import org.remus.marketplace.xml.Marketplace;
import org.remus.marketplace.xml.Search;
import org.remus.marketplace.xml.XMLBuilder;
import org.springframework.web.HttpRequestHandler;

/**
 * Servlet implementation class CategoryListing
 */

public class SearchListing implements HttpRequestHandler {
	private static final long serialVersionUID = 1L;

	private NodeDao nodeDao;

	public void setNodeDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
	}

	private IndexService indexService;

	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}

	private String serverPrefix;

	public void setServerPrefix(String serverPrefix) {
		this.serverPrefix = serverPrefix;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchListing() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void
			handleRequest(HttpServletRequest arg0, HttpServletResponse arg1)
					throws ServletException, IOException {
		String query = arg0.getParameter("query");
		String parameter = arg0.getParameter("filters");
		int category = 0;
		String marketId = null;
		if (parameter != null) {
			String[] split = parameter.split("\\s");
			for (String string : split) {
				String replace = string.replace("tid:", "");
				if (replace != null && replace.trim().length() > 0) {
					try {
						category = Integer.parseInt(replace);

					} catch (NumberFormatException e1) {
						marketId = replace;
					}
				}
			}
		}

		try {
			category = Integer.parseInt(arg0.getParameter("categoryId"));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			JAXBContext newInstance = JAXBContext.newInstance(
					Marketplace.class, org.remus.marketplace.xml.Market.class);

			Marketplace marketplace = new Marketplace();

			List<Integer> searchNodes = indexService.searchNodes(query,
					category, marketId);
			Search search = new Search();
			search.setTerm(query);
			search.setCount(searchNodes.size());
			search.setUrl(serverPrefix + "search/apachesolr/"
					+ URLEncoder.encode(query, "UTF-8")
					+ (category != 0 && marketId != null ? "?filters=" : "")
					+ (category != 0 ? "tid:" + category : "")
					+ (marketId != null ? " tid:" + marketId : ""));

			for (int i = 0, n = Math.min(searchNodes.size(), 10); i < n; i++) {
				Node findById = nodeDao.findById(searchNodes.get(i));
				org.remus.marketplace.xml.Node node = XMLBuilder.buildNode(
						serverPrefix, findById);
				search.getNode().add(node);
			}
			marketplace.setSearch(search);
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
