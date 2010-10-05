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
package org.remus.marketplace.xml;

import java.io.Serializable;

/**
 * This is a generated class. DO NOT MODIFY THE CONTENT!!
 * 
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
@javax.xml.bind.annotation.XmlRootElement
public class Marketplace implements Serializable {

	public static final String MARKET = "market";

	private java.util.Set<Market> market = new java.util.HashSet<Market>();

	private NodeCategory category;

	private Node node;

	private Featured featured;

	private Recent recent;

	private Popular popular;

	private Favorite favorites;

	private Search search;

	public java.util.Set<Market> getMarket() {
		return market;
	}

	public void setMarket(java.util.Set<Market> market) {
		this.market = market;
	}

	/**
	 * @return the category
	 */
	public NodeCategory getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(NodeCategory category) {
		this.category = category;
	}

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
	 * @return the featured
	 */
	public Featured getFeatured() {
		return featured;
	}

	/**
	 * @param featured
	 *            the featured to set
	 */
	public void setFeatured(Featured featured) {
		this.featured = featured;
	}

	/**
	 * @return the recent
	 */
	public Recent getRecent() {
		return recent;
	}

	/**
	 * @param recent
	 *            the recent to set
	 */
	public void setRecent(Recent recent) {
		this.recent = recent;
	}

	/**
	 * @return the popular
	 */
	public Popular getPopular() {
		return popular;
	}

	/**
	 * @param popular
	 *            the popular to set
	 */
	public void setPopular(Popular popular) {
		this.popular = popular;
	}

	/**
	 * @return the favorite
	 */
	public Favorite getFavorites() {
		return favorites;
	}

	/**
	 * @param favorite
	 *            the favorite to set
	 */
	public void setFavorites(Favorite favorite) {
		this.favorites = favorite;
	}

	/**
	 * @return the search
	 */
	public Search getSearch() {
		return search;
	}

	/**
	 * @param search
	 *            the search to set
	 */
	public void setSearch(Search search) {
		this.search = search;
	}

}
