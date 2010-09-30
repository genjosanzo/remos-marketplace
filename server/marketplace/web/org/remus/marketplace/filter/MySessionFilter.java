package org.remus.marketplace.filter;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

public class MySessionFilter extends OpenSessionInViewFilter {
	/*
	 * The default mode is FlushMode.NEVER
	 * 
	 * @see
	 * org.springframework.orm.hibernate.support.OpenSessionInViewFilter#getSession
	 * (net.sf.hibernate.SessionFactory)
	 */
	@Override
	protected Session getSession(SessionFactory sessionFactory)
			throws DataAccessResourceFailureException {
		Session session = super.getSession(sessionFactory);
		session.setFlushMode(FlushMode.COMMIT);
		return session;
	}

	/**
	 * we do an explicit flush here just in case we do not have an automated
	 * flush
	 */
	@Override
	protected void closeSession(Session session, SessionFactory factory) {
		session.flush();
		super.closeSession(session, factory);
	}
}