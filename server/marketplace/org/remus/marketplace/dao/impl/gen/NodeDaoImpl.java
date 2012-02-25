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
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.dao.AdvancedCriteria;
import org.remus.marketplace.entities.Node;
import org.remus.marketplace.dao.AbstractCrudDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * This is a generated class. DO NOT MODIFY THE CONTENTS!!
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class NodeDaoImpl extends AbstractCrudDao<Node> implements NodeDao {

	public Node findById(int id) {
		return (Node) getHibernateTemplate().get(Node.class, id);

	}

	public java.util.List<Node> findByCategoriesId(int id, AdvancedCriteria adv) {
		Criteria createCriteria = getCurrentSession()
				.createCriteria(Node.class).createCriteria("categories")
				.add(Restrictions.eq("id", id));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}

		return createCriteria.list();

	}

	public java.util.List<org.remus.marketplace.entities.Category> getChildrenOfCategories(
			Node base, AdvancedCriteria adv) {

		Criteria createCriteria = getCurrentSession()
				.createCriteria(org.remus.marketplace.entities.Category.class)
				.createCriteria("nodes")
				.add(Restrictions.eq("id", base.getId()));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}
		java.util.List result = createCriteria.list();

		return result;

	}

	public org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Category> searchChildrenOfCategories(
			Node base, AdvancedCriteria adv, int page, int entitiesPerPage) {
		assert page >= 0;
		assert entitiesPerPage > 0;
		Criteria criteria = getCurrentSession()
				.createCriteria(org.remus.marketplace.entities.Category.class)
				.createCriteria("nodes")
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

	public void lazyAttachCategories(Node base) {

		java.util.Collection<org.remus.marketplace.entities.Category> result = getChildrenOfCategories(
				base, null);

		base.getCategories().clear();
		for (org.remus.marketplace.entities.Category obj : result) {
			base.getCategories().add(obj);
		}

	}

	public java.util.List<Node> findByPlatformsId(java.lang.String id,
			AdvancedCriteria adv) {
		Criteria createCriteria = getCurrentSession()
				.createCriteria(Node.class).createCriteria("platforms")
				.add(Restrictions.eq("id", id));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}

		return createCriteria.list();

	}

	public java.util.List<org.remus.marketplace.entities.Platform> getChildrenOfPlatforms(
			Node base, AdvancedCriteria adv) {

		Criteria createCriteria = getCurrentSession()
				.createCriteria(org.remus.marketplace.entities.Platform.class)
				.createCriteria("nodes")
				.add(Restrictions.eq("id", base.getId()));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}
		java.util.List result = createCriteria.list();

		return result;

	}

	public org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Platform> searchChildrenOfPlatforms(
			Node base, AdvancedCriteria adv, int page, int entitiesPerPage) {
		assert page >= 0;
		assert entitiesPerPage > 0;
		Criteria criteria = getCurrentSession()
				.createCriteria(org.remus.marketplace.entities.Platform.class)
				.createCriteria("nodes")
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
		return new org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Platform>(
				count, adv.getFirstResult(), adv.getMaxResults(),
				criteria.list());
	}

	public void lazyAttachPlatforms(Node base) {

		java.util.Collection<org.remus.marketplace.entities.Platform> result = getChildrenOfPlatforms(
				base, null);

		base.getPlatforms().clear();
		for (org.remus.marketplace.entities.Platform obj : result) {
			base.getPlatforms().add(obj);
		}

	}

	public Node findByInstallableUnitsId(int id, AdvancedCriteria adv) {
		Criteria createCriteria = getCurrentSession()
				.createCriteria(Node.class).createCriteria("installableUnits")
				.add(Restrictions.eq("id", id));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}

		java.util.List result = createCriteria.list();
		if (result.size() == 1) {
			return (Node) result.get(0);
		} else if (result.size() > 1) {
			throw new IllegalArgumentException(
					"Find returned more than one Result...");
		} else {
			return null;
		}

	}

	public java.util.List<org.remus.marketplace.entities.InstallableUnit> getChildrenOfInstallableUnits(
			Node base, AdvancedCriteria adv) {

		Criteria createCriteria = getCurrentSession()
				.createCriteria(
						org.remus.marketplace.entities.InstallableUnit.class)
				.createCriteria("node")
				.add(Restrictions.eq("id", base.getId()));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}
		java.util.List result = createCriteria.list();

		return result;

	}

	public org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.InstallableUnit> searchChildrenOfInstallableUnits(
			Node base, AdvancedCriteria adv, int page, int entitiesPerPage) {
		assert page >= 0;
		assert entitiesPerPage > 0;
		Criteria criteria = getCurrentSession()
				.createCriteria(
						org.remus.marketplace.entities.InstallableUnit.class)
				.createCriteria("node")
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
		return new org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.InstallableUnit>(
				count, adv.getFirstResult(), adv.getMaxResults(),
				criteria.list());
	}

	public void lazyAttachInstallableUnits(Node base) {

		java.util.Collection<org.remus.marketplace.entities.InstallableUnit> result = getChildrenOfInstallableUnits(
				base, null);

		base.getInstallableUnits().clear();
		for (org.remus.marketplace.entities.InstallableUnit obj : result) {
			base.getInstallableUnits().add(obj);
		}

	}

	public Node findByClicksId(int id, AdvancedCriteria adv) {
		Criteria createCriteria = getCurrentSession()
				.createCriteria(Node.class).createCriteria("clicks")
				.add(Restrictions.eq("id", id));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}

		java.util.List result = createCriteria.list();
		if (result.size() == 1) {
			return (Node) result.get(0);
		} else if (result.size() > 1) {
			throw new IllegalArgumentException(
					"Find returned more than one Result...");
		} else {
			return null;
		}

	}

	public java.util.List<org.remus.marketplace.entities.Clickthrough> getChildrenOfClicks(
			Node base, AdvancedCriteria adv) {

		Criteria createCriteria = getCurrentSession()
				.createCriteria(
						org.remus.marketplace.entities.Clickthrough.class)
				.createCriteria("node")
				.add(Restrictions.eq("id", base.getId()));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}
		java.util.List result = createCriteria.list();

		return result;

	}

	public org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Clickthrough> searchChildrenOfClicks(
			Node base, AdvancedCriteria adv, int page, int entitiesPerPage) {
		assert page >= 0;
		assert entitiesPerPage > 0;
		Criteria criteria = getCurrentSession()
				.createCriteria(
						org.remus.marketplace.entities.Clickthrough.class)
				.createCriteria("node")
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
		return new org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Clickthrough>(
				count, adv.getFirstResult(), adv.getMaxResults(),
				criteria.list());
	}

	public void lazyAttachClicks(Node base) {

		java.util.Collection<org.remus.marketplace.entities.Clickthrough> result = getChildrenOfClicks(
				base, null);

		base.getClicks().clear();
		for (org.remus.marketplace.entities.Clickthrough obj : result) {
			base.getClicks().add(obj);
		}

	}

	public Node findByDownloadsId(int id, AdvancedCriteria adv) {
		Criteria createCriteria = getCurrentSession()
				.createCriteria(Node.class).createCriteria("downloads")
				.add(Restrictions.eq("id", id));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}

		java.util.List result = createCriteria.list();
		if (result.size() == 1) {
			return (Node) result.get(0);
		} else if (result.size() > 1) {
			throw new IllegalArgumentException(
					"Find returned more than one Result...");
		} else {
			return null;
		}

	}

	public java.util.List<org.remus.marketplace.entities.Download> getChildrenOfDownloads(
			Node base, AdvancedCriteria adv) {

		Criteria createCriteria = getCurrentSession()
				.createCriteria(org.remus.marketplace.entities.Download.class)
				.createCriteria("node")
				.add(Restrictions.eq("id", base.getId()));
		if (adv != null) {
			adv.add2Criterion(createCriteria);
		}
		java.util.List result = createCriteria.list();

		return result;

	}

	public org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Download> searchChildrenOfDownloads(
			Node base, AdvancedCriteria adv, int page, int entitiesPerPage) {
		assert page >= 0;
		assert entitiesPerPage > 0;
		Criteria criteria = getCurrentSession()
				.createCriteria(org.remus.marketplace.entities.Download.class)
				.createCriteria("node")
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
		return new org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Download>(
				count, adv.getFirstResult(), adv.getMaxResults(),
				criteria.list());
	}

	public void lazyAttachDownloads(Node base) {

		java.util.Collection<org.remus.marketplace.entities.Download> result = getChildrenOfDownloads(
				base, null);

		base.getDownloads().clear();
		for (org.remus.marketplace.entities.Download obj : result) {
			base.getDownloads().add(obj);
		}

	}

}
