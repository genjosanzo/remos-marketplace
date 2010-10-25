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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.xml.serialize.XMLSerializer;
import org.remus.marketplace.dao.gen.CategoryDao;
import org.remus.marketplace.dao.gen.DownloadDao;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.entities.Node;
import org.remus.marketplace.xml.IU;
import org.remus.marketplace.xml.IUs;
import org.remus.marketplace.xml.Marketplace;
import org.remus.marketplace.xml.Platform;
import org.remus.marketplace.xml.Platforms;
import org.remus.marketplace.xml.XMLBuilder;
import org.springframework.web.HttpRequestHandler;

/**
 * Servlet implementation class MarketAndCateogriesListing
 */
public class ContentListing implements HttpRequestHandler {
	private static final long serialVersionUID = 1L;

	private NodeDao nodeDao;

	private CategoryDao categoryDao;

	private DownloadDao downloadDao;

	public void setNodeDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setDownloadDao(DownloadDao downloadDao) {
		this.downloadDao = downloadDao;
	}

	private String serverPrefix;

	public void setServerPrefix(String serverPrefix) {
		this.serverPrefix = serverPrefix;
	}

	@Override
	public void handleRequest(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		int contentId = Integer.parseInt(arg0.getParameter("nodeId"));
		arg1.setContentType("text/xml");
		try {
			JAXBContext newInstance = JAXBContext.newInstance(
					Marketplace.class, org.remus.marketplace.xml.Node.class,
					Platforms.class, Platform.class, IUs.class, IU.class);

			Marketplace marketplace = new Marketplace();
			Node findById = nodeDao.findById(contentId);
			if (findById != null) {
				org.remus.marketplace.xml.Node node = XMLBuilder.buildNode(
						serverPrefix, findById);
				marketplace.setNode(node);
				Marshaller createMarshaller = newInstance.createMarshaller();
				XMLSerializer xmlSerializer = XMLBuilder.getXMLSerializer(arg1
						.getOutputStream());
				createMarshaller.marshal(marketplace,
						xmlSerializer.asContentHandler());
			} else {
				throw new ServletException("Node not found");
			}
		} catch (JAXBException e) {
			throw new ServletException(e);
		}

	}

}
