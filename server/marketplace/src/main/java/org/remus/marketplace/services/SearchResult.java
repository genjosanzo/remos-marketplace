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
package org.remus.marketplace.services;

import java.util.List;

/**
 * This object is a wrapper for executing search queries with integrated
 * pagination. It gives you the possibility to access the records of the current
 * page and provides you also the absolute number of records
 * 
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class SearchResult<T> {

	public SearchResult(int absoluteResults, int currentPage, int max,
			List<T> currentResultList) {
		super();
		this.absoluteResults = absoluteResults;
		this.currentPage = currentPage;
		this.currentResultList = currentResultList;
		this.max = max;
	}

	private final int absoluteResults;

	private final int currentPage;

	private final List<T> currentResultList;

	private final int max;

	/**
	 * @return the absoluteResults
	 */
	public int getAbsoluteResults() {
		return absoluteResults;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return the currentResultList
	 */
	public List<T> getCurrentResultList() {
		return currentResultList;
	}

	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	public int getNumberOfPages() {
		if (max == 0 || absoluteResults == 0) {
			return 0;
		}
		return absoluteResults / max + 1;
	}

}
