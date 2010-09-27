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
package org.remus.marketplace.indexing.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopFieldDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.remus.marketplace.entities.Category;
import org.remus.marketplace.entities.Node;
import org.remus.marketplace.indexing.IndexService;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class IndexServiceImpl implements IndexService {

	private String indexDir;

	public void setIndexDir(String indexDir) {
		this.indexDir = indexDir;
	}

	@Override
	public void addToIndex(Node node) {
		try {

			Map<Integer, String> listOfCat = new HashMap<Integer, String>();
			Set<Category> categories = node.getCategories();
			for (Category category : categories) {
				if (!listOfCat.containsKey(category.getId())) {
					listOfCat.put(category.getId(), category.getMarket()
							.getId());
				}
			}

			IndexWriter indexWriter = new IndexWriter(getDirectory(),
					new SimpleAnalyzer(), MaxFieldLength.UNLIMITED);
			Set<Integer> keySet = listOfCat.keySet();
			indexWriter.deleteDocuments(new Term(Node.ID, String.valueOf(node
					.getId())));
			for (Integer integer : keySet) {
				final Document document = new Document();

				Field unitField = new Field(Node.ID, String.valueOf(node
						.getId()), Field.Store.YES, Field.Index.NOT_ANALYZED);
				document.add(unitField);

				Field titleField = new Field(Node.NAME, node.getName(),
						Field.Store.YES, Field.Index.ANALYZED);
				document.add(titleField);
				titleField.setBoost(2.0f);

				Field shortDescriptionField = new Field(Node.SHORTDESCRIPTION,
						node.getShortdescription(), Field.Store.YES,
						Field.Index.ANALYZED);
				document.add(shortDescriptionField);
				shortDescriptionField.setBoost(1.5f);

				Field bodyField = new Field(Node.BODY, node.getBody(),
						Field.Store.YES, Field.Index.ANALYZED);
				bodyField.setBoost(1.0f);
				document.add(bodyField);

				Field categoryField = new Field("category",
						String.valueOf(integer), Field.Store.YES,
						Field.Index.NOT_ANALYZED);
				document.add(categoryField);

				Field marketField = new Field("market", listOfCat.get(integer),
						Field.Store.YES, Field.Index.NOT_ANALYZED);
				document.add(marketField);

				indexWriter.addDocument(document);
			}

			indexWriter.commit();
			indexWriter.optimize();
			indexWriter.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void removeFromIndex(Node node) {
		final Term term = new Term(Node.ID, String.valueOf(node.getId()));
		try {
			IndexWriter indexWriter = new IndexWriter(getDirectory(),
					new SimpleAnalyzer(), MaxFieldLength.UNLIMITED);
			indexWriter.deleteDocuments(term);
			indexWriter.commit();
			indexWriter.optimize();
			indexWriter.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Integer> searchNodes(String query, int categoryId,
			String marketId) {
		List<Integer> returnValue = new ArrayList<Integer>();

		List<String> termList = new ArrayList<String>();
		List<String> fieldList = new ArrayList<String>();
		List<Occur> flagList = new ArrayList<Occur>();
		Query searchWordQuery = null;

		try {
			IndexSearcher indexSearcher = new IndexSearcher(getDirectory());

			// search for the query

			if (query != null && query.length() > 0) {
				fieldList.add(Node.NAME);
				termList.add(query);
				flagList.add(Occur.SHOULD);

				fieldList.add(Node.SHORTDESCRIPTION);
				termList.add(query);
				flagList.add(Occur.SHOULD);

				fieldList.add(Node.BODY);
				termList.add(query);
				flagList.add(Occur.SHOULD);
			}

			if (fieldList.size() > 0) {
				searchWordQuery = MultiFieldQueryParser.parse(
						Version.LUCENE_30,
						termList.toArray(new String[termList.size()]),
						fieldList.toArray(new String[fieldList.size()]),
						flagList.toArray(new Occur[flagList.size()]),
						getAnalyser());
			}

			// must part
			// Must part
			termList = new ArrayList<String>();
			fieldList = new ArrayList<String>();
			flagList = new ArrayList<Occur>();

			if (categoryId > 0) {
				fieldList.add("category");
				termList.add(String.valueOf(categoryId));
				flagList.add(Occur.MUST);
			}
			if (categoryId > 0) {
				fieldList.add("category");
				termList.add(String.valueOf(categoryId));
				flagList.add(Occur.MUST);
			}

			Query mustHaveQuery = null;

			if (fieldList.size() > 0) {
				mustHaveQuery = MultiFieldQueryParser.parse(Version.LUCENE_30,
						termList.toArray(new String[termList.size()]),
						fieldList.toArray(new String[fieldList.size()]),
						flagList.toArray(new Occur[flagList.size()]),
						getAnalyser());
			}

			BooleanQuery booleanQuery = new BooleanQuery();
			if (searchWordQuery != null) {
				booleanQuery.add(searchWordQuery, Occur.MUST);
			}
			if (mustHaveQuery != null && termList.size() > 0) {
				booleanQuery.add(mustHaveQuery, Occur.MUST);
			}

			TopFieldDocs search = indexSearcher.search(booleanQuery, null,
					4096, new Sort());
			ScoreDoc[] scoreDocs = search.scoreDocs;
			for (ScoreDoc scoreDoc : scoreDocs) {
				Document doc = indexSearcher.doc(scoreDoc.doc);
				int parseInt = Integer.parseInt(doc.get(Node.ID));
				if (!returnValue.contains(parseInt)) {
					returnValue.add(parseInt);
				}
			}
			indexSearcher.close();

			return returnValue;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private Directory getDirectory() throws IOException {
		File file = new File(indexDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return FSDirectory.open(file);
	}

	private Analyzer getAnalyser() {
		return new WhitespaceAnalyzer();
	}

}
