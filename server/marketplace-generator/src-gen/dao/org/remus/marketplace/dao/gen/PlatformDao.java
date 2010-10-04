
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
import org.remus.marketplace.entities.Platform;

/**
 * This is a generated class. DO NOT MODIFY THE CONTENTS!!
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public interface PlatformDao extends CrudDao<Platform> {

	public Platform

	findById(

	java.lang.String

	id);

	public Platform

	findById(

	int

	id);

	java.util.List<Platform>

	findByNodesName(int id, AdvancedCriteria adv);

	org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Node> searchChildrenOfNodes(
			Platform base, AdvancedCriteria adv, int page, int entitiesPerPage);

	java.util.List<org.remus.marketplace.entities.Node>

	getChildrenOfNodes(Platform base

	, AdvancedCriteria adv

	);

	void lazyAttachNodes(Platform base);

}
