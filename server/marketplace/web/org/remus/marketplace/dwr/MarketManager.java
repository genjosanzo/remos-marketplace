/*******************************************************************************
 * Copyright (c) 2010 Tom Seidel, Remus Software
 * All rights reserved.
 *
 *
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/

package org.remus.marketplace.dwr;

import org.remus.marketplace.dao.gen.CategoryDao;
import org.remus.marketplace.dao.gen.InstallableUnitDao;
import org.remus.marketplace.dao.gen.MarketDao;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.dao.gen.PlatformDao;
import org.remus.marketplace.entities.Category;
import org.remus.marketplace.entities.InstallableUnit;
import org.remus.marketplace.entities.Market;
import org.remus.marketplace.entities.Node;
import org.remus.marketplace.entities.Platform;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class MarketManager {

	private MarketDao marketDao;

	private CategoryDao categoryDao;

	private NodeDao nodeDao;

	private PlatformDao platformDao;

	private InstallableUnitDao installableUnitDao;

	public void setMarketDao(MarketDao marketDao) {
		this.marketDao = marketDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setNodeDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
	}

	public void setPlatformDao(PlatformDao platformDao) {
		this.platformDao = platformDao;
	}

	public void setInstallableUnitDao(InstallableUnitDao installableUnitDao) {
		this.installableUnitDao = installableUnitDao;
	}

	public void deleteMarket(String id) {
		marketDao.delete(marketDao.findById(id));
	}

	public void editMarketName(String marketId, String name) {
		Market findById = marketDao.findById(marketId);
		findById.setName(name);
		marketDao.update(findById);
	}

	public Market createMarket(String marketName) {
		if (marketName == null || marketName.trim().length() == 0) {
			throw new IllegalArgumentException("Market name must not be empty");
		}
		Market market = new Market();
		market.setName(marketName);
		marketDao.create(market);
		return market;
	}

	public Category createCategory(String name, String marketId) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException(
					"Category name must not be empty");
		}
		Market findById = marketDao.findById(marketId);
		if (findById != null) {
			Category cat = new Category();
			cat.setName(name);
			cat.setMarket(findById);
			categoryDao.create(cat);
			cat.getMarket();
			return cat;
		}
		throw new IllegalArgumentException("Market not found");
	}

	public void deleteCategory(String categoryId) {
		int parseInt = Integer.parseInt(categoryId);
		Category findById = categoryDao.findById(parseInt);
		categoryDao.delete(findById);
	}

	public Platform createPlatform(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException(
					"Platform name must not be empty");
		}
		Platform platform = new Platform();
		platform.setName(name);
		platformDao.create(platform);
		return platform;
	}

	public void deletePlatform(String id) {
		int parseInt = Integer.parseInt(id);
		Platform findById = platformDao.findById(parseInt);
		if (findById != null) {
			platformDao.delete(findById);
		} else {
			throw new IllegalArgumentException("Selected Platform not valid");
		}
	}

	public Node createSolution(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException(
					"Solution name must not be empty");
		}
		Node node = new Node();
		node.setName(name);
		nodeDao.create(node);
		return node;
	}

	public void deleteSolution(String id) {
		int parseInt = Integer.parseInt(id);
		Node findById = nodeDao.findById(parseInt);
		if (findById != null) {
			nodeDao.delete(findById);
		} else {
			throw new IllegalArgumentException("Selected Node not valid");
		}
	}

	public InstallableUnit createIu(String iuName, String nodeId) {
		if (iuName == null || iuName.trim().length() == 0) {
			throw new IllegalArgumentException("IU name must not be empty");
		}
		Node selectNode = selectNode(nodeId);
		InstallableUnit unit = new InstallableUnit();
		unit.setName(iuName);
		unit.setNode(selectNode);
		installableUnitDao.create(unit);
		return unit;

	}

	public void deleteIu(String iuId) {
		int parseInt = Integer.parseInt(iuId);
		InstallableUnit findById = installableUnitDao.findById(parseInt);
		if (findById != null) {
			installableUnitDao.delete(findById);
		} else {
			throw new IllegalArgumentException("Selected Node not valid");
		}
	}

	public Node selectNode(String nodeId) {
		int parseInt = Integer.parseInt(nodeId);
		Node findById = nodeDao.findById(parseInt);
		if (findById != null) {
			findById.getCategories();
			findById.getPlatforms();
			findById.getInstallableUnits();
			return findById;
		}

		throw new IllegalArgumentException("Node not found");
	}

}
