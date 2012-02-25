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

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class AdvancedCriteria {

	public static AdvancedCriteria EMPTY_READONLY_INSTANCE = new AdvancedCriteria() {
		@Override
		public Criteria add2Criterion(Criteria criteria) {
			return criteria;
		}

		@Override
		public AdvancedCriteria addOrder(Order order) {
			return this;
		}

		@Override
		public AdvancedCriteria addSubCriteria(AdvancedCriteria adv) {
			return this;
		}

		@Override
		public AdvancedCriteria addRestriction(Criterion res) {
			return this;
		}

		@Override
		public AdvancedCriteria setAssosication(String assosication) {
			return this;
		}

		@Override
		public AdvancedCriteria setFirstResult(int firstResult) {
			return this;
		}

		@Override
		public AdvancedCriteria setMaxResults(int maxResults) {
			return this;
		};

		@Override
		public AdvancedCriteria setJoin(int jOIN_TYPE) {
			return this;
		};

		@Override
		public Criteria add2Criterion(Criteria criteria, boolean enablePaging) {
			return criteria;
		}
	};

	private String assosication;

	private final List<AdvancedCriteria> childCriterias = new ArrayList<AdvancedCriteria>();

	private int firstResult = -1;

	private int join = Criteria.INNER_JOIN;

	private int maxResults = -1;

	private final List<Order> orders = new ArrayList<Order>();

	private final List<Criterion> restrictions = new ArrayList<Criterion>();

	private Projection projection;

	public Criteria add2Criterion(Criteria criteria) {
		return add2Criterion(criteria, true);
	}

	public Criteria add2Criterion(Criteria criteria, boolean enablePaging) {
		for (final Criterion r : restrictions) {
			criteria.add(r);
		}
		for (final Order o : orders) {
			criteria.addOrder(o);
		}
		if (projection != null) {
			criteria.setProjection(projection);
		}
		if (firstResult >= 0 && enablePaging) {
			criteria.setFirstResult(firstResult);
		}
		if (maxResults >= 0 && enablePaging) {
			criteria.setMaxResults(maxResults);
		}
		for (final AdvancedCriteria subCriterias : childCriterias) {
			if (subCriterias.assosication == null) {
				throw new IllegalArgumentException(
						"Assosiation Path must not be null");
			}
			final Criteria createCriteria;
			if (subCriterias.join >= 0) {
				createCriteria = criteria.createCriteria(
						subCriterias.assosication, subCriterias.join);
			} else {
				createCriteria = criteria
						.createCriteria(subCriterias.assosication);
			}
			subCriterias.add2Criterion(createCriteria, enablePaging);
		}

		return criteria;

	}

	public AdvancedCriteria addOrder(Order order) {
		orders.add(order);
		return this;
	}

	public AdvancedCriteria addRestriction(Criterion res) {
		restrictions.add(res);
		return this;
	}

	public AdvancedCriteria addSubCriteria(AdvancedCriteria adv) {
		childCriterias.add(adv);
		return this;
	}

	/**
	 * @return the firstResult
	 */
	public int getFirstResult() {
		return firstResult;
	}

	/**
	 * @return the maxResults
	 */
	public int getMaxResults() {
		return maxResults;
	}

	/**
	 * @param assosication
	 *            the assosication to set
	 */
	public AdvancedCriteria setAssosication(String assosication) {
		this.assosication = assosication;
		return this;
	}

	/**
	 * @param firstResult
	 *            the firstResult to set
	 */
	public AdvancedCriteria setFirstResult(int firstResult) {
		this.firstResult = firstResult;
		return this;
	}

	/**
	 * @param jOIN_TYPE
	 *            the jOIN_TYPE to set
	 */
	public AdvancedCriteria setJoin(int jOIN_TYPE) {
		join = jOIN_TYPE;
		return this;
	}

	/**
	 * @param maxResults
	 *            the maxResults to set
	 */
	public AdvancedCriteria setMaxResults(int maxResults) {
		this.maxResults = maxResults;
		return this;
	}

	/**
	 * @return the projection
	 */
	public Projection getProjection() {
		return projection;
	}

	/**
	 * @param projection
	 *            the projection to set
	 */
	public AdvancedCriteria setProjection(Projection projection) {
		this.projection = projection;
		return this;
	}

}
