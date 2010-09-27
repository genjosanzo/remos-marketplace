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
package org.remus.marketplace.dao;

import java.util.List;

import org.remus.marketplace.services.SearchResult;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public interface CrudDao<T> {

	int count(AdvancedCriteria criteria);

	Object create(T obj);

	void delete(T obj);

	List<T> find(AdvancedCriteria criteria);

	List<Object> query(AdvancedCriteria criteria);

	SearchResult<T> search(AdvancedCriteria criteria);

	void update(T obj);

	void refresh(T obj);

	void refreshAndUpdate(T obj);

	// SearchResult<T> search(String[] fields, String searchquery, int page,
	// int resultsPerPage);

}
