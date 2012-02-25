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
package org.remus.marketplace.dao.impl.gen;

import java.io.Serializable;
import org.remus.marketplace.dao.gen.MarketDao;
import org.remus.marketplace.dao.AdvancedCriteria;
import org.remus.marketplace.entities.Market;
import org.remus.marketplace.dao.AbstractCrudDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * This is a generated class. DO NOT MODIFY THE CONTENTS!!
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class MarketDaoImpl extends AbstractCrudDao<Market> implements MarketDao {

	public Market findById(java.lang.String id) {
		return (Market) getHibernateTemplate().get(Market.class, id);

	}

	public Market findByCategoriesId(int id, AdvancedCriteria adv) {
		Criteria createCriteria = getCurrentSession()
				.createCriteria(Market.class).createCriteria("categories")
				.add(Restrictions.eq("id", id));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}

		java.util.List result = createCriteria.list();
		if (result.size() == 1) {
			return (Market) result.get(0);
		} else if (result.size() > 1) {
			throw new IllegalArgumentException(
					"Find returned more than one Result...");
		} else {
			return null;
		}

	}

	public java.util.List<org.remus.marketplace.entities.Category> getChildrenOfCategories(
			Market base, AdvancedCriteria adv) {

		Criteria createCriteria = getCurrentSession()
				.createCriteria(org.remus.marketplace.entities.Category.class)
				.createCriteria("market")
				.add(Restrictions.eq("id", base.getId()));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}
		java.util.List result = createCriteria.list();

		return result;

	}

	public org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Category> searchChildrenOfCategories(
			Market base, AdvancedCriteria adv, int page, int entitiesPerPage) {
		assert page >= 0;
		assert entitiesPerPage > 0;
		Criteria criteria = getCurrentSession()
				.createCriteria(org.remus.marketplace.entities.Category.class)
				.createCriteria("market")
				.add(Restrictions.eq("id", base.getId()));

		if (adv != null) {
			adv.add2Criterion(criteria);
		}
		criteria.setFirstResult(page);
		criteria.setMaxResults(entitiesPerPage);
		final int count = count(criteria);
		if (adv.getFirstResult() >= 0
				&& count < adv.getFirstResult() * adv.getMaxResults()) {
			throw new IllegalArgumentException(
					"Paging index higher than available records");
		}
		return new org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Category>(
				count, adv.getFirstResult(), adv.getMaxResults(),
				criteria.list());
	}

	public void lazyAttachCategories(Market base) {

		java.util.Collection<org.remus.marketplace.entities.Category> result = getChildrenOfCategories(
				base, null);

		base.getCategories().clear();
		for (org.remus.marketplace.entities.Category obj : result) {
			base.getCategories().add(obj);
		}

	}

}
