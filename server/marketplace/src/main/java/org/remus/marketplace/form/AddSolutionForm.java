/*******************************************************************************
 * Copyright (c) 2010 Tom Seidel, Remus Software
 * All rights reserved.
 *
 *
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/

package org.remus.marketplace.form;

import org.remus.marketplace.entities.Node;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class AddSolutionForm {

	private Integer category;

	private Node node;

	/**
	 * @return the node
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * @param node
	 *            the node to set
	 */
	public void setNode(Node node) {
		this.node = node;
	}

	/**
	 * @return the categoryId
	 */
	public Integer getCategory() {
		return category;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategory(Integer categoryId) {
		this.category = categoryId;
	}

}
