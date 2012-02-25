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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.remus.marketplace.services.SearchResult;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public abstract class AbstractCrudDao<T> extends HibernateDaoSupport implements
		CrudDao<T> {

	@Override
	public int count(AdvancedCriteria criteria) {
		assert criteria != null;
		final Criteria createCriteria = getCurrentSession().createCriteria(
				getTypeParameter());
		criteria.add2Criterion(createCriteria, false);
		return count(createCriteria);

	};

	protected int count(Criteria criteria) {
		criteria.setProjection(Projections.projectionList().add(
				Projections.rowCount()));
		return (Integer) criteria.list().get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geneon.asc.dao.CrudDao#create(java.lang.Object)
	 */
	@Override
	public Object create(T obj) {
		return getHibernateTemplate().save(obj);
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see geneon.asc.dao.CrudDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(T obj) {
		getHibernateTemplate().delete(obj);
	};

	@Override
	public List<T> find(AdvancedCriteria criteria) {
		assert criteria != null;
		final Criteria createCriteria = getCurrentSession().createCriteria(
				getTypeParameter());
		criteria.add2Criterion(createCriteria);
		return createCriteria.list();

	}

	@Override
	public List<Object> query(AdvancedCriteria criteria) {
		assert criteria != null;
		final Criteria createCriteria = getCurrentSession().createCriteria(
				getTypeParameter());
		criteria.add2Criterion(createCriteria);
		return createCriteria.list();

	}

	protected Session getCurrentSession() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}

	// public SearchResult<T> search(String[] fields, String searchquery,
	// int page, int resultsPerPage) {
	// FullTextSession fullTextSession = Search
	// .getFullTextSession(getCurrentSession());
	//
	// MultiFieldQueryParser parser = new MultiFieldQueryParser(fields,
	// new WhitespaceAnalyzer());
	// try {
	// org.apache.lucene.search.Query query = parser.parse(searchquery);
	// org.hibernate.Query hibQuery;
	// if (page < 0) {
	// // wrap Lucene query in a org.hibernate.Query
	// hibQuery = fullTextSession.createFullTextQuery(query,
	// getTypeParameter());
	// } else {
	// hibQuery = fullTextSession.createFullTextQuery(query,
	// getTypeParameter()).setCriteriaQuery(
	// getCurrentSession().createCriteria(getTypeParameter())
	// .setMaxResults(resultsPerPage)
	// .setFirstResult(page));
	// }
	//
	// // execute search
	// List result = hibQuery.list();
	// return new SearchResult<T>(result.size(), page, resultsPerPage,
	// result);
	// } catch (ParseException e) {
	// throw new IllegalArgumentException("Query cannot be parsed");
	// }
	//
	// }

	protected Class<T> getTypeParameter() {
		final Type genericSuperclass = getClass().getGenericSuperclass();
		if (genericSuperclass instanceof ParameterizedType) {
			return (Class<T>) ((ParameterizedType) genericSuperclass)
					.getActualTypeArguments()[0];
		}
		return null;
	}

	@Override
	public SearchResult<T> search(AdvancedCriteria criteria) {
		final int count = count(criteria);

		if (criteria.getFirstResult() >= 0
				&& count < criteria.getFirstResult() * criteria.getMaxResults()) {
			throw new IllegalArgumentException(
					"Paging index higher than available records");
		}
		return new SearchResult<T>(count, criteria.getFirstResult(),
				criteria.getMaxResults(), find(criteria));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geneon.asc.dao.CrudDao#update(java.lang.Object)
	 */
	@Override
	public void update(T obj) {
		getHibernateTemplate().update(obj);
	}

	@Override
	public void refresh(T obj) {
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
		getHibernateTemplate().refresh(obj);
	}

	@Override
	public void refreshAndUpdate(T obj) {
		refresh(obj);
		update(obj);
	};
}
