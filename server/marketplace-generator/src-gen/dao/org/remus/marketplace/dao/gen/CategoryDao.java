
/*******************************************************************************
 * Copyright (c) 2010 Tom Seidel, Remus Software
 * All rights reserved.
 *
 *
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
package org.remus.marketplace.dao.gen;

import org.remus.marketplace.dao.CrudDao;
import org.remus.marketplace.dao.AdvancedCriteria;
import org.remus.marketplace.entities.Category;

/**
 * This is a generated class. DO NOT MODIFY THE CONTENTS!!
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public interface CategoryDao extends CrudDao<Category> {

	public Category

	findById(

	int

	id);

	java.util.List<Category>

	findByNodesId(int id, AdvancedCriteria adv);

	org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Node> searchChildrenOfNodes(
			Category base, AdvancedCriteria adv, int page, int entitiesPerPage);

	java.util.List<org.remus.marketplace.entities.Node>

	getChildrenOfNodes(Category base

	, AdvancedCriteria adv

	);

	void lazyAttachNodes(Category base);

	java.util.List<Category>

	findByMarketId(java.lang.String id, AdvancedCriteria adv);

	org.remus.marketplace.entities.Market

	getChildrenOfMarket(Category base

	, AdvancedCriteria adv

	);

	void lazyAttachMarket(Category base);

}
