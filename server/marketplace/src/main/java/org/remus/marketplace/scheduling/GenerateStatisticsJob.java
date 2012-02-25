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
package org.remus.marketplace.scheduling;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.remus.marketplace.dao.AdvancedCriteria;
import org.remus.marketplace.dao.gen.ClickthroughDao;
import org.remus.marketplace.dao.gen.DownloadDao;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.entities.Clickthrough;
import org.remus.marketplace.entities.Download;
import org.remus.marketplace.entities.Node;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class GenerateStatisticsJob {

	private NodeDao nodeDao;

	private ClickthroughDao clickthroughDao;

	private DownloadDao downloadDao;

	public void setNodeDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
	}

	public void setDownloadDao(DownloadDao downloadDao) {
		this.downloadDao = downloadDao;
	}

	public void setClickthroughDao(ClickthroughDao clickthroughDao) {
		this.clickthroughDao = clickthroughDao;
	}

	private Integer month;

	public void setMonth(Integer month) {
		this.month = month;
	}

	private String imageFolder;

	public void setImageFolder(String imageFolder) {
		this.imageFolder = imageFolder;
	}

	public void run() {
		File folder = new File(imageFolder);
		if (!folder.isDirectory() || !folder.exists()) {
			folder.mkdirs();
		}
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		instance.add(Calendar.MONTH, month * -1);

		List<Node> find = nodeDao
				.find(AdvancedCriteria.EMPTY_READONLY_INSTANCE);
		for (Node node : find) {
			Integer id = node.getId();
			createClickStatistics(id, folder, node, instance);
			createDownloadStatistics(id, folder, node, instance);
		}

	}

	private void createClickStatistics(Integer id, File folder, Node node,
			Calendar instance) {
		Map<Date, Integer> map = new HashMap<Date, Integer>();
		for (int i = 0, n = month; i < n; i++) {
			Calendar instance2 = Calendar.getInstance();
			instance2.setTime(new Date());
			instance2.add(Calendar.MONTH, i * -1);
			map.put(instance2.getTime(), 0);
		}
		AdvancedCriteria criteria = new AdvancedCriteria().addRestriction(
				Restrictions.between(Clickthrough.TIME, instance.getTime(),
						new Date())).addRestriction(
				Restrictions.eq(Clickthrough.NODE, node));
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.count(Clickthrough.NODE));

		projectionList.add(Projections.sqlGroupProjection(
				"month({alias}.time) as month, year({alias}.time) as year",
				"month({alias}.time), year({alias}.time)", new String[] {
						"month", "year" }, new Type[] { Hibernate.INTEGER,
						Hibernate.INTEGER }));
		criteria.setProjection(projectionList);

		List<Object> query = clickthroughDao.query(criteria);
		for (Object object : query) {
			Object[] data = (Object[]) object;
			Integer count = (Integer) data[0];
			Integer month = (Integer) data[1];
			Integer year = (Integer) data[2];
			Set<Date> keySet = map.keySet();
			for (Date date : keySet) {
				Calendar instance2 = Calendar.getInstance();
				instance2.setTime(date);
				if (instance2.get(Calendar.YEAR) == year
						&& instance2.get(Calendar.MONTH) == month - 1) {
					map.put(date, count);
				}
			}

		}

		DefaultCategoryDataset data = new DefaultCategoryDataset();
		List<Date> keySet = new ArrayList<Date>(map.keySet());
		Collections.sort(keySet, new Comparator<Date>() {

			@Override
			public int compare(Date o1, Date o2) {
				return o1.compareTo(o2);
			}
		});
		for (Date date : keySet) {
			Integer integer = map.get(date);
			Calendar instance2 = Calendar.getInstance();
			instance2.setTime(date);
			int year = instance2.get(Calendar.YEAR);
			int month = instance2.get(Calendar.MONTH) + 1;
			data.addValue(integer, "Column1", month + "-" + year);
		}

		JFreeChart createBarChart = ChartFactory.createBarChart("Clicks",
				"Month", "", data, PlotOrientation.VERTICAL, false, false,
				false);

		File file = new File(folder, "clicks_" + id + ".png");
		if (file.exists()) {
			file.delete();
		}
		try {
			ChartUtilities.saveChartAsPNG(file, createBarChart, 500, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void createDownloadStatistics(Integer id, File folder, Node node,
			Calendar instance) {
		Map<Date, Integer> map = new HashMap<Date, Integer>();
		for (int i = 0, n = month; i < n; i++) {
			Calendar instance2 = Calendar.getInstance();
			instance2.setTime(new Date());
			instance2.add(Calendar.MONTH, i * -1);
			map.put(instance2.getTime(), 0);
		}
		AdvancedCriteria criteria = new AdvancedCriteria().addRestriction(
				Restrictions.between(Download.TIME, instance.getTime(),
						new Date())).addRestriction(
				Restrictions.eq(Download.NODE, node));
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.count(Download.NODE));

		projectionList.add(Projections.sqlGroupProjection(
				"month({alias}.time) as month, year({alias}.time) as year",
				"month({alias}.time), year({alias}.time)", new String[] {
						"month", "year" }, new Type[] { Hibernate.INTEGER,
						Hibernate.INTEGER }));
		criteria.setProjection(projectionList);

		List<Object> query = downloadDao.query(criteria);
		for (Object object : query) {
			Object[] data = (Object[]) object;
			Integer count = (Integer) data[0];
			Integer month = (Integer) data[1];
			Integer year = (Integer) data[2];
			Set<Date> keySet = map.keySet();
			for (Date date : keySet) {
				Calendar instance2 = Calendar.getInstance();
				instance2.setTime(date);
				if (instance2.get(Calendar.YEAR) == year
						&& instance2.get(Calendar.MONTH) == month - 1) {
					map.put(date, count);
				}
			}

		}

		DefaultCategoryDataset data = new DefaultCategoryDataset();
		List<Date> keySet = new ArrayList<Date>(map.keySet());
		Collections.sort(keySet, new Comparator<Date>() {

			@Override
			public int compare(Date o1, Date o2) {
				return o1.compareTo(o2);
			}
		});
		for (Date date : keySet) {
			Integer integer = map.get(date);
			Calendar instance2 = Calendar.getInstance();
			instance2.setTime(date);
			int year = instance2.get(Calendar.YEAR);
			int month = instance2.get(Calendar.MONTH) + 1;
			data.addValue(integer, "Column1", month + "-" + year);
		}

		JFreeChart createBarChart = ChartFactory.createBarChart("Downloads",
				"Month", "", data, PlotOrientation.VERTICAL, false, false,
				false);

		File file = new File(folder, "download_" + id + ".png");
		if (file.exists()) {
			file.delete();
		}
		try {
			ChartUtilities.saveChartAsPNG(file, createBarChart, 500, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
