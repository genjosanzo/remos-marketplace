
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
import org.remus.marketplace.entities.Market;

/**
 * This is a generated class. DO NOT MODIFY THE CONTENTS!!
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public interface MarketDao extends CrudDao<Market> {

	public Market

	findById(

	java.lang.String

	id);

	Market

	findByCategoriesId(int id, AdvancedCriteria adv);

	org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Category> searchChildrenOfCategories(
			Market base, AdvancedCriteria adv, int page, int entitiesPerPage);

	java.util.List<org.remus.marketplace.entities.Category>

	getChildrenOfCategories(Market base

	, AdvancedCriteria adv

	);

	void lazyAttachCategories(Market base);

}
