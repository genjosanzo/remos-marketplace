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
import org.remus.marketplace.dao.gen.PlatformDao;
import org.remus.marketplace.dao.AdvancedCriteria;
import org.remus.marketplace.entities.Platform;
import org.remus.marketplace.dao.AbstractCrudDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * This is a generated class. DO NOT MODIFY THE CONTENTS!!
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class PlatformDaoImpl extends AbstractCrudDao<Platform>
		implements
			PlatformDao {

	public Platform findById(java.lang.String id) {
		return (Platform) getHibernateTemplate().get(Platform.class, id);

	}

	public Platform findById(int id) {
		return (Platform) getHibernateTemplate().get(Platform.class, id);

	}

	public java.util.List<Platform> findByNodesName(int id, AdvancedCriteria adv) {
		Criteria createCriteria = getCurrentSession()
				.createCriteria(Platform.class).createCriteria("nodes")
				.add(Restrictions.eq("name", id));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}

		return createCriteria.list();

	}

	public java.util.List<org.remus.marketplace.entities.Node> getChildrenOfNodes(
			Platform base, AdvancedCriteria adv) {

		Criteria createCriteria = getCurrentSession()
				.createCriteria(org.remus.marketplace.entities.Node.class)
				.createCriteria("platforms")
				.add(Restrictions.eq("name", base.getName()));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}
		java.util.List result = createCriteria.list();

		return result;

	}

	public org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Node> searchChildrenOfNodes(
			Platform base, AdvancedCriteria adv, int page, int entitiesPerPage) {
		assert page >= 0;
		assert entitiesPerPage > 0;
		Criteria criteria = getCurrentSession()
				.createCriteria(org.remus.marketplace.entities.Node.class)
				.createCriteria("platforms")
				.add(Restrictions.eq("name", base.getName()));

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
		return new org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Node>(
				count, adv.getFirstResult(), adv.getMaxResults(),
				criteria.list());
	}

	public void lazyAttachNodes(Platform base) {

		java.util.Collection<org.remus.marketplace.entities.Node> result = getChildrenOfNodes(
				base, null);

		base.getNodes().clear();
		for (org.remus.marketplace.entities.Node obj : result) {
			base.getNodes().add(obj);
		}

	}

}
