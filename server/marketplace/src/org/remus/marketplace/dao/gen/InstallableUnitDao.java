
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
import org.remus.marketplace.entities.InstallableUnit;

/**
 * This is a generated class. DO NOT MODIFY THE CONTENTS!!
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public interface InstallableUnitDao extends CrudDao<InstallableUnit> {

	public InstallableUnit

	findById(

	int

	id);

	java.util.List<InstallableUnit>

	findByNodeId(int id, AdvancedCriteria adv);

	org.remus.marketplace.entities.Node

	getChildrenOfNode(InstallableUnit base

	, AdvancedCriteria adv

	);

	void lazyAttachNode(InstallableUnit base);

}
