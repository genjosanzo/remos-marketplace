/*******************************************************************************
 * Copyright (c) 2010 Tom Seidel, Remus Software
 * All rights reserved.
 *
 *
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/

package org.remus.marketplace.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.HttpRequestHandler;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class ImageStreamer implements HttpRequestHandler {

	private String imagePath;

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.HttpRequestHandler#handleRequest(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("nodeId");
		String type = request.getParameter("type");
		if (parameter != null && Pattern.matches("^\\d*$", parameter)
				&& type != null
				&& ("download".equals(type) || "clicks".equals(type))) {
			File file = new File(imagePath + type + "_" + parameter + ".png");
			if (file.exists()) {
				response.setContentType("image/png");
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
					byte[] byteArray = IOUtils.toByteArray(fis);
					response.setContentLength(byteArray.length);
					response.getOutputStream().write(byteArray);
					response.getOutputStream().flush();
				} catch (Exception e) {
					// exception
				} finally {
					if (fis != null) {
						try {
							fis.close();
						} catch (Exception e) {
							// do nothing. we've done our best.
						}
						fis = null;
					}
				}

			}
		}

	}

}
