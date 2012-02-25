
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
import org.remus.marketplace.entities.Download;

/**
 * This is a generated class. DO NOT MODIFY THE CONTENTS!!
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public interface DownloadDao extends CrudDao<Download> {

	public Download

	findById(

	int

	id);

	java.util.List<Download>

	findByNodeId(int id, AdvancedCriteria adv);

	org.remus.marketplace.entities.Node

	getChildrenOfNode(Download base

	, AdvancedCriteria adv

	);

	void lazyAttachNode(Download base);

}
