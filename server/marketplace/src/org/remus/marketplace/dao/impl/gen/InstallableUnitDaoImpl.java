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
import org.remus.marketplace.dao.gen.InstallableUnitDao;
import org.remus.marketplace.dao.AdvancedCriteria;
import org.remus.marketplace.entities.InstallableUnit;
import org.remus.marketplace.dao.AbstractCrudDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * This is a generated class. DO NOT MODIFY THE CONTENTS!!
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class InstallableUnitDaoImpl extends AbstractCrudDao<InstallableUnit>
		implements
			InstallableUnitDao {

	public InstallableUnit findById(int id) {
		return (InstallableUnit) getHibernateTemplate().get(
				InstallableUnit.class, id);

	}

	public java.util.List<InstallableUnit> findByNodeId(int id,
			AdvancedCriteria adv) {
		Criteria createCriteria = getCurrentSession()
				.createCriteria(InstallableUnit.class).createCriteria("node")
				.add(Restrictions.eq("id", id));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}

		return createCriteria.list();

	}

	public org.remus.marketplace.entities.Node getChildrenOfNode(
			InstallableUnit base, AdvancedCriteria adv) {

		Criteria createCriteria = getCurrentSession()
				.createCriteria(org.remus.marketplace.entities.Node.class)
				.createCriteria("installableUnits")
				.add(Restrictions.eq("id", base.getId()));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}
		java.util.List result = createCriteria.list();

		if (result.size() == 1) {
			return (org.remus.marketplace.entities.Node) result.iterator()
					.next();
		} else if (result.size() > 1) {
			throw new IllegalArgumentException(
					"Find returned more than one Result...");
		} else {
			return null;
		}

	}

	public void lazyAttachNode(InstallableUnit base) {

		java.util.Collection<org.remus.marketplace.entities.Node> result = java.util.Collections
				.singletonList(getChildrenOfNode(base, null));

		if (result.size() == 1) {
			base.setNode((org.remus.marketplace.entities.Node) result
					.iterator().next());
		} else if (result.size() > 1) {
			throw new IllegalArgumentException(
					"Find returned more than one Result...");
		}

	}

}
