/*******************************************************************************
 * Copyright (c) 2010 Tom Seidel, Remus Software
 * All rights reserved.
 *
 *
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/

package org.remus.marketplace.dwr;

import org.remus.marketplace.dao.gen.MarketDao;
import org.remus.marketplace.entities.Market;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class MarketManager {

	private MarketDao marketDao;

	public void setMarketDao(MarketDao marketDao) {
		this.marketDao = marketDao;
	}

	public void deleteMarket(String id) {
		// delete 'market' prefix;
		String realId = id.substring(7);
		marketDao.delete(marketDao.findById(realId));
	}

	public void editMarketName(String marketId, String name) {
		Market findById = marketDao.findById(marketId);
		findById.setName(name);
		marketDao.update(findById);
	}

	public Market createMarket(String marketName) {
		Market market = new Market();
		market.setName(marketName);
		marketDao.create(market);
		return market;
	}

}
