
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
import org.remus.marketplace.entities.Node;

/**
 * This is a generated class. DO NOT MODIFY THE CONTENTS!!
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public interface NodeDao extends CrudDao<Node> {

	public Node

	findById(

	int

	id);

	java.util.List<Node>

	findByCategoriesId(int id, AdvancedCriteria adv);

	org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Category> searchChildrenOfCategories(
			Node base, AdvancedCriteria adv, int page, int entitiesPerPage);

	java.util.List<org.remus.marketplace.entities.Category>

	getChildrenOfCategories(Node base

	, AdvancedCriteria adv

	);

	void lazyAttachCategories(Node base);

	java.util.List<Node>

	findByPlatformsId(java.lang.String id, AdvancedCriteria adv);

	org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Platform> searchChildrenOfPlatforms(
			Node base, AdvancedCriteria adv, int page, int entitiesPerPage);

	java.util.List<org.remus.marketplace.entities.Platform>

	getChildrenOfPlatforms(Node base

	, AdvancedCriteria adv

	);

	void lazyAttachPlatforms(Node base);

	Node

	findByInstallableUnitsId(int id, AdvancedCriteria adv);

	org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.InstallableUnit> searchChildrenOfInstallableUnits(
			Node base, AdvancedCriteria adv, int page, int entitiesPerPage);

	java.util.List<org.remus.marketplace.entities.InstallableUnit>

	getChildrenOfInstallableUnits(Node base

	, AdvancedCriteria adv

	);

	void lazyAttachInstallableUnits(Node base);

	Node

	findByClicksId(int id, AdvancedCriteria adv);

	org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Clickthrough> searchChildrenOfClicks(
			Node base, AdvancedCriteria adv, int page, int entitiesPerPage);

	java.util.List<org.remus.marketplace.entities.Clickthrough>

	getChildrenOfClicks(Node base

	, AdvancedCriteria adv

	);

	void lazyAttachClicks(Node base);

	Node

	findByDownloadsId(int id, AdvancedCriteria adv);

	org.remus.marketplace.services.SearchResult<org.remus.marketplace.entities.Download> searchChildrenOfDownloads(
			Node base, AdvancedCriteria adv, int page, int entitiesPerPage);

	java.util.List<org.remus.marketplace.entities.Download>

	getChildrenOfDownloads(Node base

	, AdvancedCriteria adv

	);

	void lazyAttachDownloads(Node base);

}
