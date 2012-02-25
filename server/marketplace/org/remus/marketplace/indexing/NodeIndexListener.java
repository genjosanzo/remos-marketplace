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
package org.remus.marketplace.indexing;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.remus.marketplace.entities.Node;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class NodeIndexListener extends EmptyInterceptor {

	private IndexService indexService;

	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}

	public NodeIndexListener() {
		System.out.println("INIT LISTENER");
	}

	private static ThreadLocal<Set<Node>> insertsNodes = new ThreadLocal<Set<Node>>() {
		@Override
		protected Set<Node> initialValue() {
			return new HashSet<Node>();
		}
	};

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (entity instanceof Node) {
			insertsNodes.get().add((Node) entity);
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if (entity instanceof Node) {
			try {
				indexService.addToIndex((Node) entity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return super.onFlushDirty(entity, id, currentState, previousState,
				propertyNames, types);
	}

	@Override
	public void postFlush(Iterator entities) {
		for (Node entity : insertsNodes.get()) {
			indexService.addToIndex(entity);
		}

	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (entity instanceof Node) {
			try {
				indexService.removeFromIndex((Node) entity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
