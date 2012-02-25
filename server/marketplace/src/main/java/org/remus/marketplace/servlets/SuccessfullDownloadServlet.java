/*******************************************************************************
 * Copyright (c) 2010 Tom Seidel, Remus Software
 * All rights reserved.
 *
 *
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/

package org.remus.marketplace.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.remus.marketplace.dao.gen.DownloadDao;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.entities.Download;
import org.remus.marketplace.entities.Node;
import org.springframework.web.HttpRequestHandler;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class SuccessfullDownloadServlet implements HttpRequestHandler {

	private DownloadDao downloadDao;

	public void setDownloadDao(DownloadDao downloadDao) {
		this.downloadDao = downloadDao;
	}

	private NodeDao nodeDao;

	public void setNodeDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.HttpRequestHandler#handleRequest(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void handleRequest(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		String parameter = arg0.getParameter("nodeId");
		if (parameter != null && Pattern.matches("^\\d*$", parameter)) {
			Node findById = nodeDao.findById(Integer.parseInt(parameter));
			if (findById != null) {
				Download download = new Download();
				download.setNode(findById);
				download.setTime(new Date());
				downloadDao.create(download);
			} else {
				throw new ServletException("Node not found");
			}
		}

	}

}
